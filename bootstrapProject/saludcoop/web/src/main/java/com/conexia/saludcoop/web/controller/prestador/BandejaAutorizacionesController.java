package com.conexia.saludcoop.web.controller.prestador;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

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

import com.conexia.saludcoop.common.dto.ProfesionalDto;
import com.conexia.saludcoop.common.dto.ProfesionalEspecialidadDto;
import com.conexia.saludcoop.common.dto.RespuestaCompuesta;
import com.conexia.saludcoop.common.dto.UsuarioEntidadDto;
import com.conexia.saludcoop.common.dto.transaccional.AutorizacionDto;
import com.conexia.saludcoop.common.dto.transaccional.ConsumoDto;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudItemDto;
import com.conexia.saludcoop.common.entity.maestro.EstadoAutorizacion;
import com.conexia.saludcoop.common.entity.security.Role;
import com.conexia.saludcoop.common.entity.security.User;
import com.conexia.saludcoop.common.util.ConverterUtil;
import com.conexia.saludcoop.common.util.RoleUtils;
import com.conexia.saludcoop.common.util.SystemConstants;
import com.conexia.saludcoop.security.SaludCoopUserDetails;
import com.conexia.saludcoop.util.ValidatedPageResponse;
import com.conexia.saludcoop.util.ValidatedResponse;
import com.conexia.saludcoop.web.BaseValidatingController;
import com.conexia.saludcoop.web.form.FiltroBandejasForm;
import com.conexia.saludcoop.web.form.FormularioConsumirForm;
import com.conexia.saludcoop.web.manager.AutorizacionManager;
import com.conexia.saludcoop.web.manager.EpsManager;
import com.conexia.saludcoop.web.manager.EspecialidadManager;
import com.conexia.saludcoop.web.manager.EstadoAutorizacionManager;
import com.conexia.saludcoop.web.manager.RegionalManager;
import com.conexia.saludcoop.web.manager.RoleEstadoManager;
import com.conexia.saludcoop.web.manager.SedeIpsManager;
import com.conexia.saludcoop.web.manager.SolicitudItemManager;
import com.conexia.saludcoop.web.manager.TipoIdentificacionManager;
import com.conexia.saludcoop.web.manager.TipoPPMManager;
import com.conexia.saludcoop.web.manager.UsuarioEntidadManager;
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
@RequestMapping(method = RequestMethod.GET)
@SessionAttributes({"sedeIps", "profesionalEspecialidad", "nombreBandeja", "autorizaciones"})
public class BandejaAutorizacionesController extends BaseValidatingController{

	private static Logger LOGGER = LoggerFactory.getLogger(BandejaAutorizacionesController.class);
	
	@Autowired
	private SedeIpsManager sedeManager;
	
	@Autowired
	private EspecialidadManager especialidadManager;
	
	@Autowired
	private UsuarioEntidadManager usuarioEntidadManager;
	
	@Autowired
    private RoleEstadoManager rolEstadoManager;
	
	@Autowired
    private RegionalManager regionalManager;
	
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
    
	@RequestMapping(value = "/prestador/cargarAutorizaciones", method = RequestMethod.GET)
	@ResponseBody
	public ValidatedPageResponse<List<BandejaAutorizacionItemProjVO>> getAutorizaciones(FiltroBandejasForm filter, ModelMap map) {
		
		ValidatedPageResponse<List<BandejaAutorizacionItemProjVO>> response = new ValidatedPageResponse<>();

		try {

			SaludCoopUserDetails userDetails = (SaludCoopUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        User user = userManager.loadUserByUsername(userDetails.getUsername());
	        UsuarioEntidadDto ue = usuarioEntidadManager.getUsuarioEntidadByUsuarioId(user.getId());
	        
            String nombreBandeja = (String) map.get("nombreBandeja");
            List<Integer> roles = getRoleFiltro(nombreBandeja);

            if (roles == null || roles.isEmpty()) {
                response.addGeneralError("No existen registros para mostrar");
                return response;
            }

			switch (nombreBandeja) {
            case SystemConstants.BANDEJA_REDIRECCION_AUDITOR:
                filter.setEstadoSolicitud(EstadoAutorizacion.PENDIENTE_REDIRECCION_AUDITOR);
                break;
            case SystemConstants.BANDEJA_ANULACION_AUDITOR:
                filter.setEstadoSolicitud(EstadoAutorizacion.PENDIENTE_ANULACION_AUDITOR);
                break;
            default:
                filter.setEstadoSolicitud(EstadoAutorizacion.AUTORIZADO);
                break;
            }

            Long sedeIpsId = null;
            if (SystemConstants.BANDEJA_IPS_MEDICO.equals(nombreBandeja) || SystemConstants.BANDEJA_AUTORIZACIONES.equals(nombreBandeja)) {
                // LDF no tiene sedeips
                if (ue.getSedeIps() != null) {
                    sedeIpsId = ue.getSedeIps().getId();
                }
            }
        
			PageVO<BandejaAutorizacionItemProjVO> paginaBandeja = am.getAutorizacionesProj(filter, roles, sedeIpsId);
			if (paginaBandeja.getContent().size() > 0) {
				response.setContent(paginaBandeja.getContent());
				response.setTotalPages(paginaBandeja.getTotalPages());
				response.setActualPage(paginaBandeja.getPaginaActual());
				response.setTotalItems(paginaBandeja.getTotalElements());
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
            if (SystemConstants.BANDEJA_AUTORIZACIONES.equals(bandeja)) {
                if (SystemConstants.ROLE_RECEPCION_IPS.equals(role.getRole()) || SystemConstants.ROLE_LINEA_FRENTE.equals(role.getRole())
                        || SystemConstants.ROLE_CONTACT_SERVICE_NAC.equals(role.getRole())) {
                    roles.add(role.getId());
                }
            } else if (SystemConstants.BANDEJA_ANULACION.equals(bandeja) || SystemConstants.BANDEJA_REDIRECCION.equals(bandeja)) {
                if (SystemConstants.ROLE_LINEA_FRENTE.equals(role.getRole())) {
                    roles.add(role.getId());
                }
            } else if (SystemConstants.BANDEJA_REDIRECCION_AUDITOR.equals(bandeja)) {
                if (RoleUtils.getRolesAllowedSeeRedireccion().contains(role.getRole())) {
                    roles.add(role.getId());
                }
            } else if (SystemConstants.BANDEJA_ANULACION_AUDITOR.equals(bandeja)) {
                if (RoleUtils.getRolesAllowedSeeAnulacion().contains(role.getRole())) {
                    roles.add(role.getId());
                }
            }else if (SystemConstants.BANDEJA_PROVEEDURIA.equals(bandeja)){
                if (SystemConstants.ROLE_PROVEEDURIA.equals(role.getRole())) {
                    roles.add(role.getId());
                }
            }
        }

        return roles;
    }

	@RequestMapping(value = "/prestador/gestionarItemAutorizado/{itemId}")
	protected String getDetalleAutorizacion(ModelMap map, @PathVariable("itemId") Long id, HttpSession session) {
		try {
			resetSession(map, session);
			SaludCoopUserDetails userDetails = (SaludCoopUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			User user = userManager.loadUserByUsername(userDetails.getUsername());
			
	        BandejasVO vo = im.getInfoItem(id, VOUtils.BANDEJA_IPS_PRESTADOR);
	        if(EstadoAutorizacion.AUTORIZADO.equals(vo.getBandejaIpsPrestadorVO().getEstado())) {
	        	vo.getBandejaIpsPrestadorVO().setAutorizada(true);
	        }
	        cargarElementosDescriptivo("vias", map, viaManager.getAll());
	        cargarElementosTipoIdentificacion("tipoIdentificacion", map, tipoIdentAfiliadoManager.getAll());
	        cargarElementosDescriptivo("especialidades", map, especialidadManager.getAll());
            // TODO: Se deja comentado para saber donde estaba, en cualquier momento los funcionales se arrepienten XD
//	        cargarElementosDescriptivo("estadosAutorizacion", map, rolEstadoManager.getEstadosByRole(((Role) user.getRoles().toArray()[0]).getId()));
            cargarElementosDescriptivo("estadosAutorizacion", map, estadoManager.getAll());
	        map.put("regionales", ConverterUtil.tranformObjects(regionalManager.getAll(), RegionalVO.class));
	        map.put("infoGeneral", vo.getInfoGeneralVO());
	        map.put("bandejaIps", vo.getBandejaIpsPrestadorVO());
	        
			if (vo.getInfoGeneralVO().getSedeIpsEfectoraId() != null) {
				map.put("sedeIps", sedeManager.findOne(vo.getInfoGeneralVO().getSedeIpsEfectoraId()));
			}
			UsuarioEntidadDto ue = usuarioEntidadManager.getUsuarioEntidad(user.getId());
			if (ue.getProfesionalEspecialidad() != null) {
				map.put("profesionalEspecialidad", ue.getProfesionalEspecialidad());
			}
			
	        return "prestador/common/gestionarItemIpsMedico";
	        
		} catch (final Exception e) {
	        LOGGER.error("Error al buscar detalle del item", e);
	        return "/common/error";
	    }
	}
	
	@RequestMapping(value = "/prestador/consumirSolicitud", method = RequestMethod.POST)
	protected @ResponseBody ValidatedResponse<List<AutorizacionDto>> consumirSolicitud(@ModelAttribute FormularioConsumirForm form, ModelMap map) {

		ValidatedResponse<List<AutorizacionDto>> response = new ValidatedResponse<>();
		try{
			ProfesionalEspecialidadDto prof = (ProfesionalEspecialidadDto) map.get("profesionalEspecialidad");
			SolicitudItemDto dto = new SolicitudItemDto();
			dto.setNroItem(form.getSolicitudItemId());
			ConsumoDto consumo = new ConsumoDto();
			consumo.setFechaConsumo(form.getFechaConsumo());
			ProfesionalDto profesional = new ProfesionalDto();
			if (prof != null){
				profesional.setId(prof.getProfesional().getId());
				consumo.setProfesional(profesional);				
			}
			dto.setConsumo(consumo);
			
			RespuestaCompuesta<List<AutorizacionDto>> autorizaciones = validatorServiceManager.consumirSolicitudItem(dto);
			if (autorizaciones.getTransactionData() != null && !autorizaciones.getTransactionData().isEmpty()) {
				response.setContent(autorizaciones.getTransactionData());
				map.put("autorizaciones", autorizaciones.getTransactionData());
			} else {
				response.addGeneralError(autorizaciones.getRespuestaDto().getMensajeRespuesta());
			}
		} catch(Exception e){
			response.addGeneralError("Error al consumir el item.");
		}
		return response;
	}
	
	private void resetSession(ModelMap modelMap, HttpSession session) {	
		modelMap.remove("sedeIps");
		session.removeAttribute("sedeIps");

		modelMap.remove("profesionalEspecialidad");
		session.removeAttribute("profesionalEspecialidad");
	}
}
