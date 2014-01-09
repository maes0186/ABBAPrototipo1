package com.conexia.saludcoop.validador.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conexia.saludcoop.common.dto.AfiliadoDto;
import com.conexia.saludcoop.common.dto.BandejasDto;
import com.conexia.saludcoop.common.dto.EscalamientoDto;
import com.conexia.saludcoop.common.dto.RespuestaCompuesta;
import com.conexia.saludcoop.common.dto.RespuestaDto;
import com.conexia.saludcoop.common.dto.SedeIpsDto;
import com.conexia.saludcoop.common.dto.transaccional.AutorizacionDto;
import com.conexia.saludcoop.common.dto.transaccional.GestionItemRedir_AnulaDto;
import com.conexia.saludcoop.common.dto.transaccional.RoleDTO;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudDiagnosticoDto;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudDto;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudItemDto;
import com.conexia.saludcoop.common.dto.transaccional.TopTenSedeIpsDto;
import com.conexia.saludcoop.common.dto.transaccional.ValidarTopesCantidadDto;
import com.conexia.saludcoop.common.enumerator.TipoTransaccion;
import com.conexia.saludcoop.validador.constantes.ConstantesContexto;
import com.conexia.saludcoop.validador.manager.SolicitudItemRedireccionManager;
import com.conexia.saludcoop.validador.process.SequenceFlowProcessor;

@Component

public class TransaccionController {

	@Autowired
	private SequenceFlowProcessor sfp;
	@Autowired
    private SolicitudItemRedireccionManager solicitudItemRedireccionManager;
    
	private static Logger LOGGER = LoggerFactory.getLogger(TransaccionController.class);
	
	
	public RespuestaDto comprobacionDerechos(Integer tipoIdentificacion, String nroIdentificacion) {
		RespuestaDto respuesta = new RespuestaDto();

		try {
			HashMap<String, Object> context = new HashMap<String, Object>();			
			context.put(ConstantesContexto.TRANSACTION_TYPE, TipoTransaccion.COMPROBACION_DERECHOS.getDescription());
			context.put(ConstantesContexto.TIPO_IDENTIFICACION, tipoIdentificacion);
			context.put(ConstantesContexto.NRO_IDENTIFICACION, nroIdentificacion);
			sfp.execute(context, true);
			respuesta = (RespuestaDto) context.get(ConstantesContexto.RESPUESTA_TRX);
		} catch (Exception e) {
			LOGGER.error("No se pudo ejecutar la transaccion, motivo: "+ e.getMessage());
            respuesta.setCodigoRespuesta(1);
            respuesta.setMensajeRespuesta("No se pudo ejecutar la transaccion, motivo: "+ e.getMessage());
			
		}

		return respuesta;
	}

	@SuppressWarnings("unchecked")
    public RespuestaCompuesta<List<AutorizacionDto>> autorizarSolicitud(SolicitudDto dto) {
		 
		HashMap<String, Object> context = new HashMap<String, Object>();	
		RespuestaCompuesta<List<AutorizacionDto>> respuesta = new RespuestaCompuesta<>();
		
		try {
				
			context.put(ConstantesContexto.TRANSACTION_TYPE, TipoTransaccion.SOLICITUD_AUTORIZACION.getDescription());
			context.put(ConstantesContexto.SOLICITUD, dto);
			
			Integer tipoIdentificacion = dto.getAfiliado().getTipoIdentificacion().getId();
			String nroIdentificacion = dto.getAfiliado().getNumeroIdentificacion();
			context.put(ConstantesContexto.TIPO_IDENTIFICACION, tipoIdentificacion);
			context.put(ConstantesContexto.NRO_IDENTIFICACION, nroIdentificacion);
			
			context.put(ConstantesContexto.USUARIO_ID, dto.getUsuarioCreador());
			
			for (SolicitudDiagnosticoDto dx : dto.getSolDiagnosticos()){
				if (dx.getEsPrincipal())
					context.put(ConstantesContexto.DX_PPAL, dx);
			}
			 
			sfp.execute(context, true);
			List<AutorizacionDto> autorizaciones = new ArrayList<AutorizacionDto>((Collection<AutorizacionDto>)context.get(ConstantesContexto.AUTORIZACIONES_DTO));
			Collections.sort(autorizaciones, new Comparator<AutorizacionDto>() {

				@Override
				public int compare(AutorizacionDto o1, AutorizacionDto o2) {
					// TODO Auto-generated method stub
					if (o1.getEstadoAutorizacion().getDescripcion().equals("Autorizada") && !o2.getEstadoAutorizacion().getDescripcion().equals("Autorizada"))
						return -1;
					else if (!o1.getEstadoAutorizacion().getDescripcion().equals("Autorizada") && o2.getEstadoAutorizacion().getDescripcion().equals("Autorizada"))
						return 1;
					else 
						return 0;
				}
			});
			
			if(autorizaciones != null && !autorizaciones.isEmpty()){
				respuesta.setTransactionData(autorizaciones);
			}else{
				respuesta.setRespuestaDto((RespuestaDto)context.get(ConstantesContexto.RESPUESTA_TRX));
			}
			
		} catch (Exception e) {
			LOGGER.error("No se pudo ejecutar la transaccion, motivo: "+ e.getMessage());
			respuesta.setRespuestaDto((RespuestaDto)context.get(ConstantesContexto.RESPUESTA_TRX));
		}
		return respuesta;
		
	}

    /**
     * Autoriza una solicitud CTC para un item específico por el auditor nacional o regional, tanto para
     * medicamentos y procedimientos
     * @param dto
     *         Información del item que se está autorizando
     * @return
     *         Respuesta indicando si el proceso se realizó correctamento o no
     */
    public RespuestaDto autorizarSolicitudCTC(BandejasDto dto) {
        RespuestaDto respuesta = new RespuestaDto();
        try {
        	HashMap<String, Object> context = new HashMap<String, Object>();			
			context.put(ConstantesContexto.TRANSACTION_TYPE, TipoTransaccion.AUTORIZACION_CTC.getDescription());
			context.put(ConstantesContexto.BANDEJAS_DTO, dto);
			sfp.execute(context, true);
			respuesta = (RespuestaDto) context.get(ConstantesContexto.RESPUESTA_TRX);
        } catch (Exception e) {
            LOGGER.error("No se pudo ejecutar la transaccion, motivo: "+ e.getMessage());
            respuesta.setCodigoRespuesta(1);
            respuesta.setMensajeRespuesta("No se pudo ejecutar la transaccion, motivo: "+ e.getMessage());
        }

        return respuesta;
    }
    
    /**
     * Autoriza una solicitud Alto Costo para un item específico por el auditor nacional o regional, tanto para
     * medicamentos y procedimientos
     * @param dto
     *         Información del item que se está autorizando
     * @return
     *         Respuesta indicando si el proceso se realizó correctamento o no
     */
    public RespuestaDto autorizarSolicitudAC(BandejasDto dto) {
        RespuestaDto respuesta = new RespuestaDto();
        try {
            HashMap<String, Object> context = new HashMap<String, Object>();			
            context.put(ConstantesContexto.TRANSACTION_TYPE, TipoTransaccion.AUTORIZACION_AC.getDescription());
            context.put(ConstantesContexto.BANDEJAS_DTO, dto);
            sfp.execute(context, true);
            respuesta = (RespuestaDto) context.get(ConstantesContexto.RESPUESTA_TRX);
        } catch (Exception e) {
            LOGGER.error("No se pudo ejecutar la transaccion, motivo: "+ e.getMessage());
            respuesta.setCodigoRespuesta(1);
            respuesta.setMensajeRespuesta("No se pudo ejecutar la transaccion, motivo: "+ e.getMessage());
        }
        
        return respuesta;
    }
    
    /**
     * Autoriza una solicitud desde la bandeja de contact service
     * 
     * @param dto
     *         Información del item que se está autorizando
     * @return
     *         Respuesta indicando si el proceso se realizó correctamento o no
     */
    public RespuestaDto autorizarSolicitudCS(BandejasDto dto) {
        RespuestaDto respuesta = new RespuestaDto();
        try {
            HashMap<String, Object> context = new HashMap<String, Object>();			
            context.put(ConstantesContexto.TRANSACTION_TYPE, TipoTransaccion.AUTORIZACION_CS.getDescription());
            context.put(ConstantesContexto.BANDEJAS_DTO, dto);
            sfp.execute(context, true);
            respuesta = (RespuestaDto) context.get(ConstantesContexto.RESPUESTA_TRX);
        } catch (Exception e) {
            LOGGER.error("No se pudo ejecutar la transaccion, motivo: "+ e.getMessage());
            respuesta.setCodigoRespuesta(1);
            respuesta.setMensajeRespuesta("No se pudo ejecutar la transaccion, motivo: "+ e.getMessage());
        }
        
        return respuesta;
    }

	public RespuestaCompuesta<List<AutorizacionDto>> consumirSolicitudItem(SolicitudItemDto dto) {
		RespuestaCompuesta<List<AutorizacionDto>> respuesta = new RespuestaCompuesta<List<AutorizacionDto>>();

		try {
			HashMap<String, Object> context = new HashMap<String, Object>();			
			context.put(ConstantesContexto.TRANSACTION_TYPE, TipoTransaccion.CONSUMIR_SOLICITUD_ITEM.getDescription());
			context.put(ConstantesContexto.NRO_SOLICITUD_ITEM, dto.getNroItem());
			context.put(ConstantesContexto.FECHA_CONSUMO, dto.getConsumo().getFechaConsumo());
			if (dto.getConsumo().getProfesional() != null)
				context.put(ConstantesContexto.PROFESIONAL, dto.getConsumo().getProfesional().getId());
			sfp.execute(context, true);
			List<AutorizacionDto> autorizaciones = new ArrayList<AutorizacionDto>((Collection<AutorizacionDto>)context.get(ConstantesContexto.AUTORIZACIONES_DTO));
			if(autorizaciones != null && !autorizaciones.isEmpty()){
				respuesta.setTransactionData(autorizaciones);
			}else{
				respuesta.setRespuestaDto((RespuestaDto)context.get(ConstantesContexto.RESPUESTA_TRX));
			}
		} catch (Exception e) {
			LOGGER.error("No se pudo ejecutar la transaccion, motivo: "+ e.getMessage());
		}

		return respuesta;
	}

	public RespuestaDto devolverSolicitudItem(SolicitudItemDto dto) {
		RespuestaDto respuesta = new RespuestaDto();

		try {
			HashMap<String, Object> context = new HashMap<String, Object>();			
			context.put(ConstantesContexto.TRANSACTION_TYPE, TipoTransaccion.DEVOLVER_SOLICITUD_ITEM.getDescription());
			context.put(ConstantesContexto.SOLICITUD_ITEM, dto);
			sfp.execute(context, true);
			respuesta = (RespuestaDto) context.get(ConstantesContexto.RESPUESTA_TRX);
		} catch (Exception e) {
			LOGGER.error("No se pudo ejecutar la transaccion, motivo: "+ e.getMessage());
		}

		return respuesta;
	}
	
    public RespuestaCompuesta<RoleDTO> obtenerTargetEscalamiento(EscalamientoDto dto) {

        HashMap<String, Object> context = new HashMap<String, Object>();
        RespuestaCompuesta<RoleDTO> respuesta = new RespuestaCompuesta<>();
        try {
            context.put(ConstantesContexto.TRANSACTION_TYPE, TipoTransaccion.OBTENER_ROLE_ESCALAMIENTO.getDescription());
            context.put(ConstantesContexto.NRO_SOLICITUD_ITEM, dto.getItemId());
            context.put(ConstantesContexto.NOMBRE_BANDEJA, dto.getNombreBandeja());
            
            sfp.execute(context, true);
            RoleDTO roleDto = (RoleDTO) context.get(ConstantesContexto.ESCALAMIENTO_DTO);
            if (roleDto != null) {
                respuesta.setTransactionData(roleDto);
            } else {
                respuesta.setRespuestaDto((RespuestaDto) context.get(ConstantesContexto.RESPUESTA_TRX));
            }
        } catch (Exception e) {
            LOGGER.error("No se pudo ejecutar la transaccion, motivo: " + e.getMessage());
        }

        return respuesta;
    }
	
	/**
	 * Funcionalidad solo para un item
	 * Obtiene la info correspeondiente a la autorizacion
	 * @param dto
	 * @return
	 */
    public RespuestaCompuesta<AutorizacionDto> obtenerAutorizacion(Long idSubitem) {
		HashMap<String, Object> context = new HashMap<String, Object>();	
		RespuestaDto respuestaOut = new RespuestaDto();
		RespuestaCompuesta<AutorizacionDto> respuesta = new RespuestaCompuesta<>();
		SolicitudItemDto itemDto= solicitudItemRedireccionManager.obtenerItemDtoParaRedireccion(idSubitem);
		try {
			context.put(ConstantesContexto.TRANSACTION_TYPE, TipoTransaccion.OBTENER_AUTORIZACION.getDescription());
			context.put(ConstantesContexto.SOLICITUD_ITEM, itemDto);
			sfp.execute(context, true);
			respuestaOut = (RespuestaDto) context.get(ConstantesContexto.RESPUESTA_TRX);
				//Solo un item
			itemDto = (SolicitudItemDto) context.get(ConstantesContexto.SOLICITUD_ITEM);
		    respuesta.setTransactionData(itemDto.getAutorizacion());
		    respuesta.setRespuestaDto(respuestaOut);
		} catch (Exception e) {
			LOGGER.error("No se pudo ejecutar la transaccion, motivo: ",e);
			throw e;
		}
		return respuesta;
		
	}
    /**
     * Servicio para realizar el redireccionamiento de la ips 
     * de un determinidado item
     * @param dto
     * @return
     */
	public RespuestaDto redireccionamientoItemIps(GestionItemRedir_AnulaDto dto) {
		RespuestaDto respuesta = new RespuestaDto();
		try {
			HashMap<String, Object> context = new HashMap<String, Object>();			
			context.put(ConstantesContexto.TRANSACTION_TYPE, TipoTransaccion.REDIRECCION_IPS_ITEM.getDescription());
			context.put(ConstantesContexto.REDIRECCIONAMIENTO_ITEM_IPS, dto);
			sfp.execute(context, true);
			respuesta = (RespuestaDto) context.get(ConstantesContexto.RESPUESTA_TRX);
			return respuesta;
		} catch (Exception e) {
			LOGGER.error("No se pudo ejecutar la transaccion, motivo: ",e);
			throw e;
		}
	}
	
	/**
     * Servicio para realizar el redireccionamiento de la ips 
     * de un determinidado item
     * @param dto
     * @return
     */
	public RespuestaDto anulacionItemIps(GestionItemRedir_AnulaDto dto) {
		RespuestaDto respuesta = new RespuestaDto();
		try {
			HashMap<String, Object> context = new HashMap<String, Object>();			
			context.put(ConstantesContexto.TRANSACTION_TYPE, TipoTransaccion.ANULAR_IPS_ITEM.getDescription());
			context.put(ConstantesContexto.ANULACION_ITEM_IPS, dto);
			sfp.execute(context, true);
			respuesta = (RespuestaDto) context.get(ConstantesContexto.RESPUESTA_TRX);
			return respuesta;
		} catch (Exception e) {
			LOGGER.error("No se pudo ejecutar la transaccion, motivo: ",e);
			throw e;
		}
	}
	/**
	 * Metodo para obtener la lista de sede Ips
	 * @param tenSedeIpsDto
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public RespuestaCompuesta<Set<SedeIpsDto>> consultarTopTenSedeIps(TopTenSedeIpsDto tenSedeIpsDto) {
		HashMap<String, Object> context = new HashMap<String, Object>();	
		RespuestaDto respuestaOut = new RespuestaDto();
		RespuestaCompuesta<Set<SedeIpsDto>> respuesta = new RespuestaCompuesta<>();
		try {
			context.put(ConstantesContexto.TRANSACTION_TYPE, TipoTransaccion.CONSULTAR_TOP_TEN_SEDES_IPS_REDIRECCIONAMIENTO.getDescription());
			context.put(ConstantesContexto.TOP_TEN_DTO, tenSedeIpsDto);
			sfp.execute(context, true);
			respuestaOut = (RespuestaDto) context.get(ConstantesContexto.RESPUESTA_TRX);
				//Solo un item
			Set<SedeIpsDto> listaIps = (Set<SedeIpsDto>) context.get(ConstantesContexto.SEDES_IPS_REDIRECCION);
			if(listaIps!=null&&listaIps.size()!=0)for (SedeIpsDto sedeIpsDto : listaIps) {
				Set<SedeIpsDto> listaIpsOut=new HashSet<SedeIpsDto>();
				listaIpsOut.add(sedeIpsDto);
				respuesta.setTransactionData(listaIpsOut);
				break;
			}
			else{
				listaIps= new HashSet<SedeIpsDto>(); 
				respuesta.setTransactionData(listaIps);
			}
		    
		    respuesta.setRespuestaDto(respuestaOut);
		} catch (Exception e) {
			LOGGER.error("No se pudo ejecutar la transaccion, motivo: ",e);
			throw e;
		}
		return respuesta;
		
	}

    /**
     * Permite ejecutar las operaciones sobre solicitudes para las que aplican tutela
     * 
     * @param dto
     *            Información del item que se está procesando
     * @return Respuesta indicando si el proceso se realizó correctamento o no
     */
    public RespuestaDto gestionarItemTutela(BandejasDto dto) {
        RespuestaDto respuesta = new RespuestaDto();
        try {
            HashMap<String, Object> context = new HashMap<String, Object>();            
            context.put(ConstantesContexto.TRANSACTION_TYPE, TipoTransaccion.GESTIONAR_ITEM_TUTELA.getDescription());
            context.put(ConstantesContexto.BANDEJAS_DTO, dto);
            sfp.execute(context, true);
            respuesta = (RespuestaDto) context.get(ConstantesContexto.RESPUESTA_TRX);
        } catch (Exception e) {
            LOGGER.error("No se pudo ejecutar la transaccion, motivo: "+ e.getMessage());
            respuesta.setCodigoRespuesta(1);
            respuesta.setMensajeRespuesta("No se pudo ejecutar la transaccion, motivo: "+ e.getMessage());
        }
        
        return respuesta;
    }
    
    /**
     * Permite realizar la actualizacion de datos de contacto del afiliado.
     * 
     * @param dto
     *            Información del afiliado a actualizar
     * @return Respuesta indicando si el proceso se realizó correctamento o no
     */
    public RespuestaDto actualizarDatosContactoAfiliado(AfiliadoDto afiliadoDto) {
        RespuestaDto respuesta = new RespuestaDto();
        try {
            HashMap<String, Object> context = new HashMap<String, Object>();            
            context.put(ConstantesContexto.TRANSACTION_TYPE, TipoTransaccion.ACTUALIZAR_DATOS_CONTACTO_AFILIADO.getDescription());
            context.put(ConstantesContexto.AFILIADO_DTO, afiliadoDto);
            sfp.execute(context, true);
            respuesta = (RespuestaDto) context.get(ConstantesContexto.RESPUESTA_TRX);
        } catch (Exception e) {
            LOGGER.error("No se pudo ejecutar la transaccion, motivo: "+ e.getMessage());
            respuesta.setCodigoRespuesta(1);
            respuesta.setMensajeRespuesta("No se pudo ejecutar la transaccion, motivo: "+ e.getMessage());
        }
        return respuesta;
    }

    /**
     * Permite ejecutar las operaciones sobre solicitudes desde las bandejas de auditor especializado
     * 
     * @param dto
     *            Información del item que se está procesando
     * @return Respuesta indicando si el proceso se realizó correctamento o no
     */
    public RespuestaDto autorizacionBandejaEspecializada(BandejasDto dto) {
        RespuestaDto respuesta = new RespuestaDto();
        try {
            HashMap<String, Object> context = new HashMap<String, Object>();            
            context.put(ConstantesContexto.TRANSACTION_TYPE, TipoTransaccion.AUTORIZACION_BANDEJA_ESPECIALIZADA.getDescription());
            context.put(ConstantesContexto.BANDEJAS_DTO, dto);
            sfp.execute(context, true);
            respuesta = (RespuestaDto) context.get(ConstantesContexto.RESPUESTA_TRX);
        } catch (Exception e) {
            LOGGER.error("No se pudo ejecutar la transaccion, motivo: "+ e.getMessage());
            respuesta.setCodigoRespuesta(1);
            respuesta.setMensajeRespuesta("No se pudo ejecutar la transaccion, motivo: "+ e.getMessage());
        }

        return respuesta;
    }
    
    /**
     * Permite validar los topes del elemento dado.
     * 
     * @param dto informacion de elemento a validar
     * @return Respuesta indicando si el proceso se realizó correctamento o no
     */
    public RespuestaDto validarTopesCantidad(ValidarTopesCantidadDto topeDto) {
        RespuestaDto respuesta = new RespuestaDto();
        try {
            HashMap<String, Object> context = new HashMap<String, Object>();            
            context.put(ConstantesContexto.TRANSACTION_TYPE, TipoTransaccion.VALIDAR_TOPE_CANTIDAD.getDescription());
            context.put(ConstantesContexto.TOPE_CANTIDAD_DTO, topeDto);
            sfp.execute(context, true);
            respuesta = (RespuestaDto) context.get(ConstantesContexto.RESPUESTA_TRX);
        } catch (Exception e) {
            LOGGER.error("No se pudo ejecutar la transaccion, motivo: "+ e.getMessage());
            respuesta.setCodigoRespuesta(1);
            respuesta.setMensajeRespuesta("No se pudo ejecutar la transaccion, motivo: "+ e.getMessage());
        }
        return respuesta;
    }
    
}
