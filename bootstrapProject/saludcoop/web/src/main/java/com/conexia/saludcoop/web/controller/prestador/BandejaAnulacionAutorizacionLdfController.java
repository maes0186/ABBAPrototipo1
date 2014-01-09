package com.conexia.saludcoop.web.controller.prestador;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.conexia.saludcoop.common.dto.EscalamientoDto;
import com.conexia.saludcoop.common.dto.RespuestaCompuesta;
import com.conexia.saludcoop.common.dto.RespuestaDto;
import com.conexia.saludcoop.common.dto.transaccional.AutorizacionDto;
import com.conexia.saludcoop.common.dto.transaccional.GestionItemRedir_AnulaDto;
import com.conexia.saludcoop.common.dto.transaccional.RoleDTO;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudItemDto;
import com.conexia.saludcoop.common.entity.maestro.EstadoAutorizacion;
import com.conexia.saludcoop.common.entity.security.Role;
import com.conexia.saludcoop.common.entity.security.User;
import com.conexia.saludcoop.common.util.ConstantesTarget;
import com.conexia.saludcoop.common.util.ConverterUtil;
import com.conexia.saludcoop.common.util.SystemConstants;
import com.conexia.saludcoop.security.SaludCoopUserDetails;
import com.conexia.saludcoop.util.ValidatedPageResponse;
import com.conexia.saludcoop.util.ValidatedResponse;
import com.conexia.saludcoop.web.BaseValidatingController;
import com.conexia.saludcoop.web.controller.util.RedireccionamientoUtils;
import com.conexia.saludcoop.web.form.FiltroBandejasForm;
import com.conexia.saludcoop.web.form.FormularioConsumirForm;
import com.conexia.saludcoop.web.manager.AutorizacionManager;
import com.conexia.saludcoop.web.manager.EpsManager;
import com.conexia.saludcoop.web.manager.EstadoAutorizacionManager;
import com.conexia.saludcoop.web.manager.RegionalManager;
import com.conexia.saludcoop.web.manager.SolicitudItemManager;
import com.conexia.saludcoop.web.manager.TipoIdentificacionManager;
import com.conexia.saludcoop.web.manager.TipoPPMManager;
import com.conexia.saludcoop.web.manager.UsuarioManager;
import com.conexia.saludcoop.web.manager.ValidatorServiceManager;
import com.conexia.saludcoop.web.manager.ViaAdministracionManager;
import com.conexia.saludcoop.web.vo.BandejaAutorizacionItemProjVO;
import com.conexia.saludcoop.web.vo.BandejasVO;
import com.conexia.saludcoop.web.vo.PageVO;
import com.conexia.saludcoop.web.vo.RegionalVO;
import com.conexia.saludcoop.web.vo.VOUtils;

/**
 * @author mcuervo
 * 
 */
@Controller
@SessionAttributes({ "nombreBandeja" })
public class BandejaAnulacionAutorizacionLdfController extends BaseValidatingController{

	private static Logger LOGGER = LoggerFactory.getLogger(BandejaAnulacionAutorizacionLdfController.class);
	
	@Autowired
	private AutorizacionManager am;
	
	@Autowired
    private EpsManager epsManager;
    
	@Autowired
	private TipoIdentificacionManager tipoIdentAfiliadoManager;
	
	@Autowired
    private EstadoAutorizacionManager estadoManager;
    
	@Autowired
	private SolicitudItemManager im;
	
    @Autowired
    private TipoPPMManager tipoPPMManager;
    
    @Autowired
    private ViaAdministracionManager viaManager;
    
    @Autowired
    private ValidatorServiceManager validatorServiceManager;
    
    @Autowired
    private UsuarioManager userManager;
    
	@Autowired
    private RegionalManager regionalManager;
    
	@RequestMapping(value = "/prestador/cargarAutorizacionesAnulacion", method = RequestMethod.GET)
	@ResponseBody
	public ValidatedPageResponse<List<BandejaAutorizacionItemProjVO>> getAutorizaciones(FiltroBandejasForm filter, ModelMap map) {
		
		ValidatedPageResponse<List<BandejaAutorizacionItemProjVO>> response = new ValidatedPageResponse<>();

		try {

            String nombreBandeja = (String) map.get("nombreBandeja");
            List<Integer> roles = getRoleFiltro(nombreBandeja);

            if (roles == null || roles.isEmpty()) {
                response.addGeneralError("No existen registros para mostrar");
                return response;
            }

            // Si el rol es LDF solo permita ver solicitudes Aprovadas para Anular (Issue SALCOOP-160) 
            for (Integer role : roles) {
                if(role == ConstantesTarget.ROLE_LINEA_FRENTE){
                    filter.setEstadoSolicitud(EstadoAutorizacion.AUTORIZADO);
                }                
            }

			PageVO<BandejaAutorizacionItemProjVO> paginaBandeja = am.getAutorizacionesProj(filter, roles, null);
			if (paginaBandeja.getContent().size() > 0) {
				response.setContent(paginaBandeja.getContent());
				response.setTotalPages(paginaBandeja.getTotalPages());
				response.setActualPage(paginaBandeja.getPaginaActual());
			} else
				response.addGeneralError("No existen registros para mostrar");

			return response;
		} catch (final Exception e) {
	        LOGGER.error("Error buscando las autorizaciones", e);
	        response.addGeneralError("Error buscando las autorizaciones");
	        return response;
	    }
	}

    /**
     * Consulta el rol correspondiente para realizar los filtros de acuerdo a la bandeja en la que se encuentra el usuario 
     * @param bandeja
     *          Nombre de la bandeja en la que se encuentra el usuario
     * @return
     *          Lista con los ids de los roles correspondientes a la bandeja
     */
    private List<Integer> getRoleFiltro(String bandeja) {

        SaludCoopUserDetails userDetails = (SaludCoopUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userManager.loadUserByUsername(userDetails.getUsername());

        List<Integer> roles = new ArrayList<>();

        for (Role role : user.getRoles()) {
           if (SystemConstants.BANDEJA_ANULACION.equals(bandeja)) {
                if (SystemConstants.ROLE_LINEA_FRENTE.equals(role.getRole())) {
                    roles.add(role.getId());
                }
            }
        }

        return roles;
    }

	@RequestMapping(value = "/prestador/consumirSolicitudAnulacion", method = RequestMethod.POST)
	protected @ResponseBody ValidatedResponse<List<AutorizacionDto>> consumirSolicitudAnulacion(@ModelAttribute FormularioConsumirForm form, ModelMap map) {

		ValidatedResponse<List<AutorizacionDto>> response = new ValidatedResponse<>();
		SolicitudItemDto dto = new SolicitudItemDto();
		dto.setNroItem(form.getSolicitudItemId());
		dto.setFechaConsumo(form.getFechaConsumo());
		RespuestaCompuesta<List<AutorizacionDto>> autorizaciones = validatorServiceManager.consumirSolicitudItem(dto);
		if (autorizaciones.getTransactionData() != null && !autorizaciones.getTransactionData().isEmpty()) {
			response.setContent(autorizaciones.getTransactionData());
			map.put("autorizaciones", autorizaciones.getTransactionData());
		} else {
			response.addGeneralError(autorizaciones.getRespuestaDto().getMensajeRespuesta());
		}
		return response;

	}
	
    @RequestMapping(value = "/prestador/funcionalidadEscalamientoAnu/{itemId}", method = RequestMethod.POST)
    @ResponseBody
    public String funcionalidadEscalamiento(String idJustificacion, @PathVariable("itemId") Long itemId, ModelMap map) {
        try {
            GestionItemRedir_AnulaDto dto = new GestionItemRedir_AnulaDto();
            dto.setJustificacion(idJustificacion);
            dto.setIdSolicitudItem(itemId);
            dto.setEsGrupo(false);
            dto.setEsParaAuditor(true);
            
            EscalamientoDto escalaDto = new EscalamientoDto();
            escalaDto.setItemId(itemId);
            escalaDto.setNombreBandeja(map.get("nombreBandeja").toString());
            RespuestaCompuesta<RoleDTO> rtaCmp = validatorServiceManager.getRoleEscalamiento(escalaDto);
            dto.setRoleId(ConverterUtil.getDataFromTransactionData((Object)rtaCmp.getTransactionData(), "id", new Integer(0)));
            
            RespuestaDto respuesta = validatorServiceManager.anularItem(dto);
            LOGGER.warn(respuesta.getMensajeRespuesta());
            return String.valueOf(idJustificacion) + itemId;
        } catch (Throwable e) {
            LOGGER.error("Error con funcionalidadEscalamiento" + e);
            throw e;
        }

    }
	
	@RequestMapping(value = "/prestador/funcionalidadAnulacionGrupo/{idAutorizacion}",method = RequestMethod.POST)
    @ResponseBody
    public String funcionalidadAnulacionGrupo(String idJustificacion,@PathVariable("idAutorizacion") Long idAutorizacion, ModelMap map) {
        try {
        	GestionItemRedir_AnulaDto dto = new GestionItemRedir_AnulaDto();
        	dto.setJustificacion(idJustificacion);
        	dto.setIdAutorizacion(idAutorizacion);
        	dto.setEsGrupo(true);
        	dto.setEsParaAuditor(false);
        	RespuestaDto respuesta =validatorServiceManager.anularItem(dto);
        	LOGGER.warn(respuesta.getMensajeRespuesta());
            return String.valueOf(idJustificacion)+idAutorizacion;
        } catch (Throwable e) {
            LOGGER.error("Error con funcionalidadEscalamiento" + e);
            throw e;
        }
		
    }
	
	@RequestMapping(value = "/prestador/gestionarItemAutorizadoAnulacion/{itemId}")
	protected String getDetalleAutorizacion(ModelMap map, @PathVariable("itemId") Long id) {
		try{
        BandejasVO vo = im.getInfoItem(id, VOUtils.BANDEJA_IPS_PRESTADOR);
        if(EstadoAutorizacion.AUTORIZADO.equals(vo.getBandejaIpsPrestadorVO().getEstado())) {
        	vo.getBandejaIpsPrestadorVO().setAutorizada(true);
        }
        cargarElementosDescriptivo("vias", map, viaManager.getAll());
        map.put("regionales", ConverterUtil.tranformObjects(regionalManager.getAll(), RegionalVO.class));
        map.put("infoGeneral", vo.getInfoGeneralVO());
        map.put("bandejaIps", vo.getBandejaIpsPrestadorVO());
        map.put("redirec", false);
        map.put("anul", true);
        cargarElementosDescriptivo("estadosAutorizacion", map, estadoManager.getAll());
        
        return "prestador/common/gestionarItemIpsMedico";
		}
		catch(Throwable e){
			LOGGER.error("Error Consultando Item",e);
			throw e;
		}
	}
	
	
	@RequestMapping(value = "/prestador/funcionalidadAnulacion/{itemId}",method = RequestMethod.POST)
    @ResponseBody
    public String funcionalidadAnulacion(String idJustificacion,@PathVariable("itemId") Long itemId, ModelMap map) {
        try {
        	GestionItemRedir_AnulaDto dto = new GestionItemRedir_AnulaDto();
        	dto.setJustificacion(idJustificacion);
        	dto.setIdSolicitudItem(itemId);
        	dto.setEsGrupo(false);
        	dto.setEsParaAuditor(false);
        	RoleDTO roleDTO=obtenerRolAuditor(itemId);
        	if(roleDTO==null||roleDTO.getId()==null){
        		dto.setEsParaAuditor(false);
        	}
        	else{
        		dto.setEsParaAuditor(true);
        		dto.setRoleId(dto.getRoleId());
        	}
        	
            User user = getUsuario();
            // Se rol para determinar el flujo de la anulación
            for (Role role : user.getRoles()) {
                if (SystemConstants.ROLE_LINEA_FRENTE.equals(role.getRole())) {
                    dto.setRoleId(SystemConstants.LINEA_FRENTE);
                } 
            }  
        	
        	validatorServiceManager.anularItem(dto);
            return String.valueOf(idJustificacion)+itemId;
        } catch (Throwable e) {
            LOGGER.error("Error con funcionalidadAnulacion" + e);
            throw e;
        }
		
    }
	
	@SuppressWarnings("unchecked")
	private RoleDTO obtenerRolAuditor(Long subitem){
		try {
			RespuestaCompuesta<AutorizacionDto> rta=validatorServiceManager.calcularNivelAutorizacion(subitem);
			Object datosSerializados=rta.getTransactionData();
			LinkedHashMap<String,Object> mapa=(LinkedHashMap<String,Object>) datosSerializados;
			RoleDTO roleDTO=RedireccionamientoUtils.obtenerRol(mapa);
			return roleDTO;
		}
		catch(Exception e){
			LOGGER.error("Error calculando el nivel de autorizacion",e);
			throw e;
		}
	}
	
	private User getUsuario() {
        // Se obtiene el usuario en sesión
        SaludCoopUserDetails userDetails = (SaludCoopUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userManager.loadUserByUsername(userDetails.getUsername());
    }
	
	
}
