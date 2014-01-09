package com.conexia.saludcoop.validador.businessRules;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.conexia.rules.Rule;

import com.conexia.saludcoop.common.dto.RespuestaDto;
import com.conexia.saludcoop.common.entity.maestro.Afiliado;
import com.conexia.saludcoop.common.entity.maestro.EstadoAutorizacion;
import com.conexia.saludcoop.common.entity.maestro.Insumo;
import com.conexia.saludcoop.common.entity.maestro.Medicamento;
import com.conexia.saludcoop.common.entity.maestro.NivelAutorizacion;
import com.conexia.saludcoop.common.entity.maestro.Procedimiento;
import com.conexia.saludcoop.common.entity.maestro.Vigencia;
import com.conexia.saludcoop.common.entity.ticket.TicketCabecera;
import com.conexia.saludcoop.common.entity.ticket.TicketItem;
import com.conexia.saludcoop.common.entity.transaccional.Autorizacion;
import com.conexia.saludcoop.common.entity.transaccional.Entrega;
import com.conexia.saludcoop.common.entity.transaccional.SolicitudDiagnostico;
import com.conexia.saludcoop.common.entity.transaccional.SolicitudItem;
import com.conexia.saludcoop.common.enumerator.OrigenSolicitud;
import com.conexia.saludcoop.common.enumerator.RegimenAfiliacion;
import com.conexia.saludcoop.common.enumerator.TipoTransaccion;
import com.conexia.saludcoop.common.repository.AutorizacionRepository;
import com.conexia.saludcoop.common.repository.CausaExternaRepository;
import com.conexia.saludcoop.common.repository.FinalidadRepository;
import com.conexia.saludcoop.common.repository.TicketCabeceraRepository;
import com.conexia.saludcoop.common.repository.TipoCatastroficoRepository;
import com.conexia.saludcoop.common.repository.VigenciaRepository;
import com.conexia.saludcoop.common.util.I18NUtils;
import com.conexia.saludcoop.common.util.SystemConstants;
import com.conexia.saludcoop.security.dao.UserRepository;
import com.conexia.saludcoop.validador.constantes.ConstantesContexto;
import com.conexia.saludcoop.validador.util.DateUtilities;
import com.conexia.saludcoop.validador.util.StringUtilities;

@Component
@Rule(description = "Regla de grabado de tickets. ")
public class RNSaveTickets extends RNProcess {

	private static Logger LOGGER = LoggerFactory.getLogger(RNSaveTickets.class);
	
	@Autowired
	private AutorizacionRepository autoRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CausaExternaRepository ceRepo;
	@Autowired
	private FinalidadRepository finalidadRepo;
	@Autowired
	private TipoCatastroficoRepository tipoCatasRepo;
	@Autowired
	private TicketCabeceraRepository ticketRepo;
	@Autowired
	private VigenciaRepository vigenciaRepository;
	
	
	
	@SuppressWarnings("unchecked")
    private boolean validarRegla(HashMap<String, Object> context){
		
		TipoTransaccion tt = TipoTransaccion.valueOf((String)context.get(ConstantesContexto.TRANSACTION_TYPE));
		Afiliado a = (Afiliado)context.get(ConstantesContexto.AFILIADO);
		List<Autorizacion> autorizaciones = null;
		try{
			switch(tt){
			case SOLICITUD_AUTORIZACION:
				if (context.get(ConstantesContexto.AUTORIZACIONES) != null){
					autorizaciones = new ArrayList<Autorizacion>((Collection<Autorizacion>)context.get(ConstantesContexto.AUTORIZACIONES)); 
					for (Autorizacion autorizacion : autorizaciones){
						//TODO No debería ser AUTORIZADO_SISTEMA?
						if (EstadoAutorizacion.AUTORIZADO.equals(autorizacion.getEstadoAutorizacion().getId())){
							createTicket(autorizacion, a, context, 1, null);
						}
					}
				}
				break;
			case AUTORIZACION_AC:
			case AUTORIZACION_CS:
			case AUTORIZACION_CTC:
			case AUTORIZACION_BANDEJA_ESPECIALIZADA:
				//Es medicamento??
				if (context.get(ConstantesContexto.ENTREGAS) != null){
					List<Entrega> entregas = (List<Entrega>)context.get(ConstantesContexto.ENTREGAS);
					SolicitudItem primerItem = entregas.get(0).getSolicitudMedicamento().getSolicitudItem();
					if (EstadoAutorizacion.AUTORIZADO.equals(primerItem.getAutorizacion().getEstadoAutorizacion().getId())){
						a = primerItem.getSolicitud().getAfiliado();
						
						for (Entrega entrega : entregas){
							createTicket(primerItem.getAutorizacion(), a, context, entregas.size(), entrega);
						}
					}

				}else if (context.get(ConstantesContexto.AUTORIZACIONES) != null){
					autorizaciones = (List<Autorizacion>)context.get(ConstantesContexto.AUTORIZACIONES); 
					for (Autorizacion autorizacion : autorizaciones){
						//TODO No debería ser AUTORIZADO_SISTEMA?
						if (EstadoAutorizacion.AUTORIZADO.equals(autorizacion.getEstadoAutorizacion().getId())){
							a = autorizacion.getSolicitudItem().getSolicitud().getAfiliado();
							createTicket(autorizacion, a, context, 1, null);
						}
					}
				}
				break;
			default:
				break;
					
			}
		}
		catch(Exception e){
			RespuestaDto rta = new RespuestaDto();
	        Integer codRespuesta = new Integer(I18NUtils.getInstance().getText("respuestaTrx.codigoNoOk"));
	        rta.setCodigoRespuesta(codRespuesta);
	        rta.setMensajeRespuesta("Error al grabar ticket");
	
	        context.put(ConstantesContexto.RESPUESTA_TRX, rta);
	        LOGGER.info("Error al procesar la transacción", e);
	
	        return false;
		}
		
		return true;
	}
	
	/**
	 * 
	 * @param Autorizacion autorizacion La autorizacion para la que se imprime el ticket
	 * @param Afiliado a El Afiliado asociado a la trx
	 * @param HashMap context El contexto de la app
	 * @param Integer cantEntregas Cantidad de entregas (se usa en caso de medicamentos)
	 * @param Entrega e La entrega para la que se está creando el ticket (se usa en caso de medicamentos)
	 */
	private void createTicket(Autorizacion autorizacion, Afiliado a, HashMap<String, Object> context, Integer cantEntregas, Entrega e){
		boolean esEntrega = e!=null;
		Integer tipoServicio = null;
		SolicitudItem primerItem = null;
		primerItem = autorizacion.getSolicitudItem();
		
		if(primerItem.isProcedimiento()){
			tipoServicio = SystemConstants.ITEM_PROCEDIMIENTO;
		}else if (primerItem.isMedicamento()) {
			tipoServicio = SystemConstants.ITEM_MEDICAMENTO;
		}else if (primerItem.isInsumo()){
			tipoServicio = SystemConstants.ITEM_INSUMO;
		}
		TicketCabecera tc = new TicketCabecera();
		tc.setTipoServicio(tipoServicio);
		tc.setAutorizacion(autorizacion);
		tc.setNumeroAutorizacion(autorizacion.getNumeroAutorizacion().intValue());
		tc.setCantidadDeEntregas(cantEntregas);
		tc.setNumeroDeEntrega(esEntrega ? e.getNumero() : 1);
		tc.setEps(a.getEps().getRazonSocial());
		tc.setNombresDelPaciente(a.getNombreCompleto());
		tc.setTipoAfiliado(a.getTipoAfiliado().name());
		tc.setTipoDeIdentificacion(a.getTipoIdentificacion().getDescripcion());
		tc.setNumeroIdentificacion(a.getNumeroIdentificacion());
		tc.setEdad(DateUtilities.getAge(a.getFechaNacimiento()));
		if (a.getRegimenAfiliacion() == RegimenAfiliacion.CONTRIBUTIVO) {
			tc.setNivel(a.getNivelIbc().getCodigo());
		} else {
			tc.setNivel(a.getNivelSisBen().getCodigo());
		}
		tc.setPlanAfiliado(primerItem.getTipoPPM().getDescripcion());
		tc.setIpsPrimaria(a.getSedeIpsAfiliacion() != null ? a.getSedeIpsAfiliacion().getNombre() : "");
		tc.setEntidadSolicitante(StringUtilities.notNull(primerItem.getSolicitud().getSedeIps().getNombre(), primerItem.getSolicitud().getSedeIps().getNombre()));
		tc.setFecha(esEntrega ? e.getFechaInicioVigencia() : autorizacion.getFechaAutorizacion());
		tc.setUsuarioTranscriptor(primerItem.getSolicitud().getUsuarioCreador().getName());
		tc.setCausaExterna(tipoServicio == SystemConstants.ITEM_PROCEDIMIENTO ? primerItem.getSolProcedimiento().getFormulaProcedimiento().getCausaExterna().getDescripcion() : null);
		tc.setOrigen(getOrigen(primerItem));
		String dxSecundarios = "";
		for (SolicitudDiagnostico dx : primerItem.getSolicitud().getSolDiagnosticos()){
			if (!dx.isEsPrincipal())
				dxSecundarios+=dx.getDiagnostico().getCodigo()+" ";
			else{
				tc.setDiagnosticoPrincipal(dx.getDiagnostico().getDescripcion());
			}
		} 
		tc.setDiagnosticosSecundarios(dxSecundarios);
		
		//Seteo todos los items
		for (SolicitudItem item : autorizacion.getSolicitudItems()){
			createTicketItem(tc, item, !esEntrega ? item.getCantidad() : e.getCantidadEntrega(), tipoServicio);
		}
		//TODO
		tc.setPagoCompartidoEps("");
		//TODO
		tc.setPagoCompartidoUsuario("");
		//TODO
		tc.setCopagoPorcentaje("");
		//TODO
		tc.setCopagoValor(primerItem.getCopagoEstimado()!=null?primerItem.getCopagoEstimado().toString():"");
		//TODO
		tc.setCuotaModeradora(autorizacion.getGrupoAutorizacion().getCuotaModeradoraEstimada()!=null?autorizacion.getGrupoAutorizacion().getCuotaModeradoraEstimada().toString():"");
		tc.setDescuentoCapitacionIps("");
		tc.setInstitucionRemitidaNombre(StringUtilities.notNull(autorizacion.getSedeIpsEfectora().getNombre(), autorizacion.getSedeIpsEfectora().getNombre()));
		tc.setInstitucionRemitidaDireccion(autorizacion.getSedeIpsEfectora().getDireccion());
		tc.setInstitucionRemitidaTelefono(StringUtilities.notNull(autorizacion.getSedeIpsEfectora().getTelefono1(), autorizacion.getSedeIpsEfectora().getTelefono2()));
		//TODO
		tc.setFirmaMedico("firma"+primerItem.getSolicitud().getProfesionalSolicitante().getRegistroMedico()+".png");
		tc.setNombreCompletoMedico(primerItem.getSolicitud().getProfesionalSolicitante().toDto().getNombreCompleto());
		tc.setRegistroMedico(primerItem.getSolicitud().getProfesionalSolicitante().getRegistroMedico());
		tc.setNumeroSolicitud(primerItem.getSolicitud().getNroSolicitud());
		tc.setTipoPpm(primerItem.getTipoPPM().getDescripcion());
		tc.setVigencia(!esEntrega?getVigencia(primerItem):getVigencia(e));
		tc.setCantidadCopias(0);
		ticketRepo.save(tc);
	}
	
	private void createTicketItem(TicketCabecera tc, SolicitudItem item, Integer cantAEntregar, Integer tipoServicio){
		TicketItem i = new TicketItem();
		switch (tipoServicio) {
		case SystemConstants.ITEM_PROCEDIMIENTO:
			i.setCodigo(item.getSolProcedimiento().getProcedimiento().getCodigo());
			i.setDescripcion(item.getSolProcedimiento().getProcedimiento().getDescripcion());
			i.setCantidad(cantAEntregar);
			i.setFinalidad(item.getSolProcedimiento().getFormulaProcedimiento().getFinalidad().getDescripcion());
			i.setLateralidad(item.getSolProcedimiento().getFormulaProcedimiento().getLateralidad().getDescripcion());
			i.setObservaciones(item.getSolProcedimiento().getFormulaProcedimiento().getPosologia());
			break;
		case SystemConstants.ITEM_MEDICAMENTO:
			i.setCodigo(item.getSolMedicamento().getMedicamento().getCodigo());
			i.setDescripcion(item.getSolMedicamento().getMedicamento().getDescripcion());
			i.setCantidad(cantAEntregar);
			i.setFinalidad(item.getSolMedicamento().getFormulaMedicamento().getFinalidad().getDescripcion());
			i.setCausaExterna(item.getSolMedicamento().getFormulaMedicamento().getCausaExterna().getDescripcion());
			i.setTipoCatastrofico(item.getSolMedicamento().getFormulaMedicamento().getTipoCatastrofico().getDescripcion());
			i.setObservaciones(item.getSolMedicamento().getFormulaMedicamento().getPosologia());
			break;
		case SystemConstants.ITEM_INSUMO:
			i.setCodigo(item.getSolInsumo().getInsumo().getCodigo());
			i.setDescripcion(item.getSolInsumo().getInsumo().getDescripcion());
			i.setCantidad(cantAEntregar);
			i.setFinalidad(item.getSolInsumo().getFormulaInsumo().getFinalidad().getDescripcion());
			i.setCausaExterna(item.getSolInsumo().getFormulaInsumo().getCausaExterna().getDescripcion());
			i.setTipoCatastrofico(item.getSolInsumo().getFormulaInsumo().getTipoCatastrofico().getDescripcion());
			i.setObservaciones("");
			break;
		default:
			break;
		}
		
		tc.addItem(i);
	
	}
	
	public String getVigencia(SolicitudItem item){
		Vigencia vig;
		String diasHoras = "";

		vig = vigenciaRepository.findVigenciaByTipoTecnologiaAndTipoPPM(item.getTipoTecnologia(), item.getTipoPPM());

		if (vig.getDiasVigencia() != null) {
			diasHoras = vig.getDiasVigencia() + " DÍAS";
		} else {
			diasHoras = vig.getHorasVigencia() + " HORAS";
		}
		return diasHoras;
	}
	
	public String getVigencia(Entrega e){
		String diasHoras = "";

		diasHoras = DateUtilities.getDaysBetween(e.getFechaInicioVigencia(), e.getFechaFinVigencia()) + " DÍAS";
		
		return diasHoras;
	}
	
	private String getOrigen(SolicitudItem solicitudItem){
	    String origen = "";
	    Boolean tutela = (solicitudItem.getAplicaTutela() != null) ? solicitudItem.getAplicaTutela() : Boolean.FALSE;
	    if(tutela){
	        origen = OrigenSolicitud.TUTELA.getCode();
	    }else{
	        if(solicitudItem.isMedicamento()){
	            Medicamento m = solicitudItem.getSolMedicamento().getMedicamento();
	            if(!m.getVisibleCtc() && !m.getAltoCosto()){
	                origen = OrigenSolicitud.POS.getCode();
	            }else if(m.getVisibleCtc() && !m.getAltoCosto()){
	                origen = OrigenSolicitud.CTC.getCode();
	            }else if(m.getAltoCosto() && SystemConstants.SHORT_TRUE.equals(m.getInsumo())){
	                origen = OrigenSolicitud.INSUMOS_ALTO_COSTO.getCode();
	            }
	        }else if(solicitudItem.isProcedimiento()){
	            Procedimiento p = solicitudItem.getSolProcedimiento().getProcedimiento();
	            Integer nivelAut = p.getNivelAutorizacion();
	            if(nivelAut == NivelAutorizacion.NIVEL_1 || nivelAut == NivelAutorizacion.NIVEL_2){
	                origen = OrigenSolicitud.POS.getCode();
	            }else if(nivelAut == 5){
	                origen = OrigenSolicitud.CTC.getCode();
	            }
	                    
	        }else if(solicitudItem.isInsumo()){
	            Insumo i = solicitudItem.getSolInsumo().getInsumo();
	            if(!i.getVisibleCtc() && !i.getAltoCosto()){
	                origen = OrigenSolicitud.POS.getCode();
	            }else if(i.getVisibleCtc() && !i.getAltoCosto()){
	                origen = OrigenSolicitud.CTC.getCode();
	            }else if(i.getAltoCosto()){
	                origen = OrigenSolicitud.INSUMOS_ALTO_COSTO.getCode();
	            }
	        }
	    }

	    return origen;
	} 
	
	
	@Override
	protected int executeRegla(HashMap<String, Object> aContext)
			throws Exception {
		// TODO Auto-generated method stub
		int execResult = RESULT_NOK;

        if (validarRegla(aContext)) {
            execResult = RESULT_OK;
            LOGGER.info("Se ejecuto con exito la regla " + this.getClass().getName());
        } else {
            execResult = RESULT_NOK;
            LOGGER.info("Se ejecuto con errores la regla " + this.getClass().getName());
        }
        return execResult;
	}

}
