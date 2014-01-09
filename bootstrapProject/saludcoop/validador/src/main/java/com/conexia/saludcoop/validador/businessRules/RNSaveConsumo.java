package com.conexia.saludcoop.validador.businessRules;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.conexia.rules.Rule;

import com.conexia.saludcoop.common.dto.MontosCopagoDto;
import com.conexia.saludcoop.common.dto.RespuestaDto;
import com.conexia.saludcoop.common.dto.TotalizacionCopagoAfiliadoDiagnosticoDto;
import com.conexia.saludcoop.common.dto.TotalizacionCopagoAfiliadoDto;
import com.conexia.saludcoop.common.dto.transaccional.AutorizacionDto;
import com.conexia.saludcoop.common.entity.maestro.Afiliado;
import com.conexia.saludcoop.common.entity.maestro.Profesional;
import com.conexia.saludcoop.common.entity.transaccional.Consumo;
import com.conexia.saludcoop.common.entity.transaccional.GrupoAutorizacion;
import com.conexia.saludcoop.common.entity.transaccional.SolicitudItem;
import com.conexia.saludcoop.common.enumerator.RegimenAfiliacion;
import com.conexia.saludcoop.common.repository.ProfesionalRepository;
import com.conexia.saludcoop.common.repository.SolicitudItemRepository;
import com.conexia.saludcoop.common.repository.SolicitudRepository;
import com.conexia.saludcoop.common.util.I18NUtils;
import com.conexia.saludcoop.validador.constantes.ConstantesContexto;
import com.conexia.saludcoop.validador.determinador.dto.ItemSolicitado;
import com.conexia.saludcoop.validador.determinador.dto.ResultadoCalculoCopago;
import com.conexia.saludcoop.validador.determinador.logica.DeterminadorTarifaCopago;
import com.conexia.saludcoop.validador.manager.TotalizacionCopagosManager;
import com.conexia.saludcoop.validador.provider.MontosCopagoProvider;

@Component
@Rule(description = "Graba el consumo de un item.")
public class RNSaveConsumo extends RNProcess {

	/**
	 * Repositorio de solicitud.
	 */
	@Autowired
	private SolicitudRepository solicitudRepository;

	@Autowired
	private SolicitudItemRepository sr;
	
	@Autowired
	private ProfesionalRepository pr;
	
	@Autowired
	private TotalizacionCopagosManager tcm;
	
	/**
	 * Cien porciento.
	 */
	private static final int CIEN_PORCIENTO = 100;
	
	/**
	 * Proveedor de montos de copago.
	 */
	@Autowired
	private MontosCopagoProvider montosCopagoProvider;
	
	/**
	 * Determinador de tarifas de copago.
	 */
	@Autowired
	private DeterminadorTarifaCopago determinadorTarifaCopago;
	
	private boolean validarRegla(HashMap<String, Object> aContext) throws Exception {

		BigDecimal montoPagoConsumoDeterminado = new BigDecimal(0);
		
		boolean result = false;
		
		try{
			Long numeroItem = (Long) aContext.get(ConstantesContexto.NRO_SOLICITUD_ITEM);
			Date fechaConsumo = (Date) aContext.get(ConstantesContexto.FECHA_CONSUMO);
			Long profesional = (Long) aContext.get(ConstantesContexto.PROFESIONAL);
			SolicitudItem item = sr.findOne(numeroItem);
			
			final Integer anioSolicitud = this.getAnioSolicitud(fechaConsumo);
			
			final MontosCopagoDto montosAbonar = this.getMontosAbonar(item.getSolicitud().getAfiliado(), anioSolicitud);
			
			BigDecimal porcentajeValorServicioCobro = (montosAbonar == null) ?
					new BigDecimal(RNSaveConsumo.CIEN_PORCIENTO) : montosAbonar.getCopagoPorcentajeValorServicio();
			
			porcentajeValorServicioCobro = porcentajeValorServicioCobro.divide(new BigDecimal(RNSaveConsumo.CIEN_PORCIENTO));
			
			final GrupoAutorizacion grupoAutorizacion = item.getAutorizacion().getGrupoAutorizacion();
			
			switch (grupoAutorizacion.getTipoPago()) {
			
				case COPAGO:
					
					/* Primero se determina si ya lo consumido anualmente es mayor a los topes previstos;
					 * en ese caso, no tiene sentido calcular monto de copago alguno */
					
					TotalizacionCopagoAfiliadoDiagnosticoDto tcad = 
							tcm.getTotalizacionAnualAfiliadoPorDiagnostico(
								anioSolicitud, item.getSolicitud().getAfiliado().getId(), item.getDiagnostico().getId());
					
					TotalizacionCopagoAfiliadoDto tca = 
							tcm.getTotalizacionAnualAfiliado(
								anioSolicitud, item.getSolicitud().getAfiliado().getId());
					
					if (tcad == null){
						tcad = new TotalizacionCopagoAfiliadoDiagnosticoDto();
						tcad.setAfiliadoId(item.getSolicitud().getAfiliado().getId());
						tcad.setAnio(anioSolicitud);
						tcad.setTotalCopagos(new BigDecimal(0));
						tcad.setDiagnosticoId(item.getDiagnostico().getDiagnostico().getId());
					}
					
					if (tca == null){
						tca = new TotalizacionCopagoAfiliadoDto();
						tca.setAfiliadoId(item.getSolicitud().getAfiliado().getId());
						tca.setAnio(anioSolicitud);
						tca.setTotalCopagos(new BigDecimal(0));
					}
					
					if (montosAbonar != null 
							&& (montosAbonar.getCopagoTopeAnualMismaPatologia().doubleValue() < tcad.getTotalCopagos().doubleValue()
									|| montosAbonar.getCopagoTopeAnualCualquierPatologia().doubleValue() 
										< tca.getTotalCopagos().doubleValue())) {
						
						montoPagoConsumoDeterminado = new BigDecimal(0);

					} else {
						/* Se debe determinar el valor que debe abonar por no haber sido superados aún los topes */
						
						/* En primer lugar, debe determinar el monto del copago a abonar; siempre se toma como
						 * criterio lo que originalmente se estimó respecto al tipo de pago, conforme a
						 * definiciones brindadas por Darío Camarro.
						 */
						final Long sedeIpsId = item.getAutorizacion().getSedeIpsEfectora().getId();
						
						final ResultadoCalculoCopago resultado = this.determinadorTarifaCopago
															.getTarifaCopago(new ItemSolicitado(item), sedeIpsId, fechaConsumo);
						
						/* Se toma el porcentaje del monto del copago que corresponde conforme al nivel de Sisben/Ibc 
						 * actual del afiliado.
						 */
						if (resultado != null) {
							montoPagoConsumoDeterminado = resultado.getMontoCopago().multiply(porcentajeValorServicioCobro);
							
							/* Se multiplica el monto por la cantidad consumida */
							montoPagoConsumoDeterminado = montoPagoConsumoDeterminado
																.multiply(new BigDecimal(item.getCantidad()));
							
							/* Se agrega el monto calculado a los totales anuales del afiliado */
							final BigDecimal nuevoMontoTcad = tcad.getTotalCopagos().add(montoPagoConsumoDeterminado);
							
							/* Se verifica si el monto determinado supera el tope previsto por diagnóstico y general */
							if (montosAbonar != null 
									&& montosAbonar.getCopagoTopeAnualMismaPatologia().doubleValue() < nuevoMontoTcad.doubleValue()) {
								montoPagoConsumoDeterminado = 
										nuevoMontoTcad.subtract(montosAbonar.getCopagoTopeAnualMismaPatologia());
							}

							final BigDecimal nuevoMontoTca = tca.getTotalCopagos().add(montoPagoConsumoDeterminado);

							if (montosAbonar != null 
									&& montosAbonar.getCopagoTopeAnualCualquierPatologia().doubleValue() < nuevoMontoTca.doubleValue()) {
								montoPagoConsumoDeterminado = 
										nuevoMontoTca.subtract(montosAbonar.getCopagoTopeAnualCualquierPatologia());
							}
							
							/* Actualizo los acumulados */
							tcad.setTotalCopagos(tcad.getTotalCopagos().add(montoPagoConsumoDeterminado));
							tca.setTotalCopagos(tca.getTotalCopagos().add(montoPagoConsumoDeterminado));
							
						} else {
							montoPagoConsumoDeterminado = new BigDecimal(0);
						}
					}
					
					this.tcm.saveTotalizacionAnualAfiliadoPorDiagnostico(tcad);
					this.tcm.saveTotalizacionAnualAfiliado(tca);
					
					break;
					
				case CUOTA_MODERADORA:
					if (!grupoAutorizacion.isCuotaModeradoraPagada()) {
						/* Se cobra la cuota moderadora estimada originalmente; no se reestima,
						 * conforme a definiciones brindadas por Darío Camarro.
						 */
						grupoAutorizacion.setCuotaModeradoraPagada(true);
						
						montoPagoConsumoDeterminado = grupoAutorizacion.getCuotaModeradoraEstimada();
					}
					
					break;
					
				default:
					/* El ítem se estimó originalmente exento de pago; no realiza cobro alguno */
					break;
			}
			
			Consumo c = new Consumo();
			c.setCantidadConsumida(item.getCantidad());
			c.setFechaConsumo(fechaConsumo);
			
			switch (grupoAutorizacion.getTipoPago()) {
			
				case COPAGO:
					c.setCopago(montoPagoConsumoDeterminado);
					c.setCuotaModeradora(new BigDecimal(0));
					break;
					
				case CUOTA_MODERADORA:
					c.setCopago(new BigDecimal(0));
					c.setCuotaModeradora(montoPagoConsumoDeterminado);
					break;
					
				default:
					c.setCopago(new BigDecimal(0));
					c.setCuotaModeradora(new BigDecimal(0));
					break;
			}
			
			if (profesional != null){
				Profesional p = pr.findOne(profesional);
				c.setProfesional(p);
			}
			item.addConsumo(c);
			
			this.solicitudRepository.save(item.getSolicitud());
			
			List<AutorizacionDto> autorizacion = new ArrayList<AutorizacionDto>();
			autorizacion.add(item.getAutorizacion().toDto());
			aContext.put(ConstantesContexto.AUTORIZACIONES_DTO, autorizacion);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		
		return result;
	}

	/**
	 * Ejecuta la regla pasando los datos necesarios para su ejecucion.
	 */
	public int executeRegla(HashMap<String, Object> aContext) throws Exception {

		int execResult = RESULT_OK;
		RespuestaDto rta = new RespuestaDto();
		if (validarRegla(aContext)) {			
			Integer codRespuesta = new Integer(I18NUtils.getInstance().getText("respuestaTrx.codigoOk"));
			rta.setCodigoRespuesta(codRespuesta);
			rta.setMensajeRespuesta("Se realizó el consumo"); 
			aContext.put(ConstantesContexto.RESPUESTA_TRX, rta);
		} else {
			Integer codRespuesta = new Integer(I18NUtils.getInstance().getText("respuestaTrx.codigoNoOk"));
			rta.setCodigoRespuesta(codRespuesta);
			rta.setMensajeRespuesta("Error al realizar el consumo"); 
			aContext.put(ConstantesContexto.RESPUESTA_TRX, rta);
		}

		return execResult;
	}
	
	/**
	 * Obtiene el nivel del afiliado.
	 * 
	 * @param afiliado Afiliado cuyo nivel obtener.
	 * @return Nivel obtenido.
	 * @throws Exception Si el afiliado no tiene un régimen de afiliación válido.
	 */
	private Integer getNivelAfiliado(final Afiliado afiliado) throws Exception {
		
		if (afiliado.getRegimenAfiliacion() == RegimenAfiliacion.CONTRIBUTIVO
				&& afiliado.getNivelIbc() != null && afiliado.getNivelIbc().getCodigo() != null) {
			return (new Integer(afiliado.getNivelIbc().getCodigo()));
		} else if (afiliado.getRegimenAfiliacion() == RegimenAfiliacion.SUBSIDIADO
				&& afiliado.getNivelSisBen() != null && afiliado.getNivelSisBen().getCodigo() != null) {
			return (new Integer(afiliado.getNivelSisBen().getCodigo()));
		}

		throw new Exception("El régimen de afiliación es inválido, o no fue especificado.");
	}
	
	/**
	 * Obtiene el año de una fecha.
	 * 
	 * @param fecha Fecha sobre la cual operar.
	 * @return Año obtenido.
	 */
	private Integer getAnioSolicitud(final Date fecha) {
		
		final Calendar fechaConsumoAsCalendar = Calendar.getInstance();
		fechaConsumoAsCalendar.setTime(fecha);
		
		return (fechaConsumoAsCalendar.get(Calendar.YEAR));
	}
	
	/**
	 * Obtiene los montos de copago que corresponden al afiliado y a la fecha del consumo.
	 * 
	 * @param afiliado Afiliado.
	 * @param anio Año del consumo.
	 * @return Montos de copago correspondientes.
	 * @throws Exception Si el monto de copago no existe, o si la fecha de solicitud,
	 * 						o bien el nivel del afiliado, no es válido.
	 */
	private MontosCopagoDto getMontosAbonar(final Afiliado afiliado, final Integer anio) throws Exception {
		
		final Integer nivel = this.getNivelAfiliado(afiliado);
		final RegimenAfiliacion regimen = afiliado.getRegimenAfiliacion();
		
		return (this.montosCopagoProvider.getMontosCopagoPorAnio(anio, nivel, regimen));
	}
}