package com.conexia.saludcoop.validador.controller.web;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.conexia.saludcoop.common.dto.AfiliadoDto;
import com.conexia.saludcoop.common.dto.BandejasDto;
import com.conexia.saludcoop.common.dto.EscalamientoDto;
import com.conexia.saludcoop.common.dto.RespuestaCompuesta;
import com.conexia.saludcoop.common.dto.RespuestaDto;
import com.conexia.saludcoop.common.dto.SedeIpsDto;
import com.conexia.saludcoop.common.dto.transaccional.AutorizacionDto;
import com.conexia.saludcoop.common.dto.transaccional.GestionItemRedir_AnulaDto;
import com.conexia.saludcoop.common.dto.transaccional.RoleDTO;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudDto;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudItemDto;
import com.conexia.saludcoop.common.dto.transaccional.TopTenSedeIpsDto;
import com.conexia.saludcoop.common.dto.transaccional.ValidarTopesCantidadDto;
import com.conexia.saludcoop.validador.controller.TransaccionController;

@Controller
public class TransaccionControllerWeb {
	private static Logger LOGGER = LoggerFactory.getLogger(TransaccionControllerWeb.class);
	@Autowired
	TransaccionController tc;

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT }, value = "COMPROBACION_DERECHOS")
	public @ResponseBody
	RespuestaDto comprobacionDerechos(@RequestBody AfiliadoDto dto) throws Exception {
		Integer tipo = dto.getTipoIdentificacion().getId();
		String numero = dto.getNumeroIdentificacion();

		RespuestaDto respuesta = tc.comprobacionDerechos(tipo, numero);

		return respuesta;
	}

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT }, value = "SOLICITUD_AUTORIZACION")
	public @ResponseBody
	RespuestaCompuesta<List<AutorizacionDto>> autorizarSolicitud(@RequestBody SolicitudDto dto) throws Exception {
		RespuestaCompuesta<List<AutorizacionDto>> respuesta = null;
		try {

			respuesta = tc.autorizarSolicitud(dto);
			return respuesta;
		} catch (Exception e) {
			return respuesta;
		}

	}

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT }, value = "CONSUMIR_SOLICITUD_ITEM")
	public @ResponseBody
	RespuestaCompuesta<List<AutorizacionDto>> consumirSolicitudItem(@RequestBody SolicitudItemDto dto) throws Exception {

		RespuestaCompuesta<List<AutorizacionDto>> respuesta = tc.consumirSolicitudItem(dto);

		return respuesta;
	}

	/**
	 * Autoriza una solicitud CTC para un item específico por el auditor
	 * nacional o regional, tanto para medicamentos y procedimientos
	 * 
	 * @param dto
	 *            Información del item que se está autorizando
	 * @return Respuesta indicando si el proceso se realizó correctamento o no
	 */
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT }, value = "AUTORIZACION_CTC")
	@ResponseBody
	public RespuestaDto autorizarSolicitudCTC(@RequestBody BandejasDto dto) {
		return tc.autorizarSolicitudCTC(dto);
	}
	
	/**
	 * Autoriza una solicitud Alto Costo para un item específico por el auditor
	 * nacional o regional, tanto para medicamentos y procedimientos
	 * 
	 * @param dto
	 *            Información del item que se está autorizando
	 * @return Respuesta indicando si el proceso se realizó correctamento o no
	 */
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT }, value = "AUTORIZACION_AC")
	@ResponseBody
	public RespuestaDto autorizarSolicitudAC(@RequestBody BandejasDto dto) {
	    return tc.autorizarSolicitudAC(dto);
	}

    /**
     * Autoriza una solicitud desde la bandeja de contact service
     * 
     * @param dto
     *         Información del item que se está autorizando
     * @return
     *         Respuesta indicando si el proceso se realizó correctamento o no
     */
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT }, value = "AUTORIZACION_CS")
	@ResponseBody
    public RespuestaDto autorizarSolicitudCS(@RequestBody BandejasDto dto) {
	    return tc.autorizarSolicitudCS(dto);
	}
	/**
	 * Devuelve una solicitud CTC o AC al auditor
	 * nacional o regional, tanto para medicamentos y procedimientos
	 * 
	 * @param dto Información del item que se está autorizando
	 * @return Respuesta indicando si el proceso se realizó correctamento o no
	 */
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT }, value = "DEVOLVER_SOLICITUD_ITEM")
	@ResponseBody
	public RespuestaDto devolverSolicitudItem(@RequestBody SolicitudItemDto dto) {
		return tc.devolverSolicitudItem(dto);
	}
	
	/**
     * Devuelve el Target corresponde a donde debe escalar la autorización dependiendo si es Anulación o Redirección
     * 
     * @param dto
     *  Recibe el nombreBandeja y el itemId
     * @return 
     */
    @RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT }, value = "OBTENER_ROLE_ESCALAMIENTO")
    @ResponseBody
    public RespuestaCompuesta<RoleDTO> obtenerTargetEscalamiento(@RequestBody EscalamientoDto dto) {
        return tc.obtenerTargetEscalamiento(dto);
    }
	
	
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT }, value = "OBTENER_AUTORIZACION")
	public @ResponseBody
	RespuestaCompuesta<AutorizacionDto> obtenerAutorizacion(@RequestBody Long subItem) throws Exception {
		RespuestaCompuesta<AutorizacionDto> respuesta = null;
		try {
			respuesta = tc.obtenerAutorizacion(subItem);
			return respuesta;
		} catch (Exception e) {
			LOGGER.error("Error en obtencion de autorizacion",e);
			throw e;
		}

	}
	/**
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT }, value = "REDIRECCION_IPS_ITEM")
	@ResponseBody
	public RespuestaDto redireccionamientoItemIps(@RequestBody GestionItemRedir_AnulaDto dto) {
		return tc.redireccionamientoItemIps(dto);
	}
	
	/**
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT }, value = "ANULAR_IPS_ITEM")
	@ResponseBody
	public RespuestaDto anulacionItemIps(@RequestBody GestionItemRedir_AnulaDto dto) {
		return tc.anulacionItemIps(dto);
	}
	/**
	 * 
	 * @param tenSedeIpsDto
	 * @return
	 */
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT }, value = "CONSULTAR_TOP_TEN_SEDES_IPS_REDIRECCIONAMIENTO")
	@ResponseBody
	public RespuestaCompuesta<Set<SedeIpsDto>> consultarTopTenSedeIps(@RequestBody TopTenSedeIpsDto tenSedeIpsDto){
		return tc.consultarTopTenSedeIps(tenSedeIpsDto);
	}

    /**
     * Permite ejecutar las operaciones sobre solicitudes para las que aplican tutela
     * 
     * @param dto
     *            Información del item que se está procesando
     * @return Respuesta indicando si el proceso se realizó correctamento o no
     */
    @RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT }, value = "GESTIONAR_ITEM_TUTELA")
    @ResponseBody
    public RespuestaDto gestionarItemTutela(@RequestBody BandejasDto dto) {
        return tc.gestionarItemTutela(dto);
    }

    /**
     * Permite realizar la actualizacion de datos de contacto del afiliado
     * 
     * @param dto
     *            Información del afiliado a actualizar
     * @return Respuesta indicando si el proceso se realizó correctamento o no
     */
    @RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT }, value = "ACTUALIZAR_DATOS_CONTACTO_AFILIADO")
    @ResponseBody
    public RespuestaDto actualizarDatosContactoAfiliado(@RequestBody AfiliadoDto dto) {
        return tc.actualizarDatosContactoAfiliado(dto);
    }

    /**
     * Permite ejecutar las operaciones sobre solicitudes desde las bandejas de auditor especializado
     * 
     * @param dto
     *            Información del item que se está procesando
     * @return Respuesta indicando si el proceso se realizó correctamento o no
     */
    @RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT }, value = "AUTORIZACION_BANDEJA_ESPECIALIZADA")
    @ResponseBody
    public RespuestaDto autorizacionBandejaEspecializada(@RequestBody BandejasDto dto) {
        return tc.autorizacionBandejaEspecializada(dto);
    }
    
    /**
     *  Permite validar los topes del elemento dado
     * 
     * @param dto informacion de elemento a validar
     * @return Respuesta indicando si el proceso se realizó correctamento o no
     */
    @RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT }, value = "VALIDAR_TOPE_CANTIDAD")
    @ResponseBody
    public RespuestaDto validarTopesCantidad(@RequestBody ValidarTopesCantidadDto dto) {
        return tc.validarTopesCantidad(dto);
    }

}