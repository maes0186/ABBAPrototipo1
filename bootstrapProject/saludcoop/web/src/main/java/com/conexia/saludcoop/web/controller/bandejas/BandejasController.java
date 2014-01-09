package com.conexia.saludcoop.web.controller.bandejas;

import static com.conexia.saludcoop.common.util.SystemConstants.PPM_ACO;
import static com.conexia.saludcoop.common.util.SystemConstants.PPM_NOP;
import static com.conexia.saludcoop.common.util.SystemConstants.ROLE_AUDITOR_AC_NAC;
import static com.conexia.saludcoop.common.util.SystemConstants.ROLE_AUDITOR_AC_REG;
import static com.conexia.saludcoop.common.util.SystemConstants.ROLE_AUDITOR_CTC_NAC;
import static com.conexia.saludcoop.common.util.SystemConstants.ROLE_AUDITOR_CTC_REG;
import static com.conexia.saludcoop.common.util.SystemConstants.ROLE_CONTACT_SERVICE_NAC;
import static com.conexia.saludcoop.common.util.SystemConstants.ROLE_CONTACT_SERVICE_REG;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.conexia.saludcoop.common.dto.DiagnosticoDto;
import com.conexia.saludcoop.common.dto.InsumoDto;
import com.conexia.saludcoop.common.dto.MedicamentoDto;
import com.conexia.saludcoop.common.dto.ProcedimientoDto;
import com.conexia.saludcoop.common.dto.RespuestaCompuesta;
import com.conexia.saludcoop.common.dto.SedeIpsDto;
import com.conexia.saludcoop.common.dto.UsuarioEntidadDto;
import com.conexia.saludcoop.common.dto.transaccional.TopTenSedeIpsDto;
import com.conexia.saludcoop.common.entity.maestro.EstadoAutorizacion;
import com.conexia.saludcoop.common.entity.security.Role;
import com.conexia.saludcoop.common.entity.security.User;
import com.conexia.saludcoop.common.repository.SolicitudItemDao;
import com.conexia.saludcoop.common.repository.SolicitudItemVODao;
import com.conexia.saludcoop.common.util.ConverterUtil;
import com.conexia.saludcoop.common.util.Pagination;
import com.conexia.saludcoop.common.util.RoleUtils;
import com.conexia.saludcoop.common.util.SystemConstants;
import com.conexia.saludcoop.security.SaludCoopUserDetails;
import com.conexia.saludcoop.util.DateUtilities;
import com.conexia.saludcoop.util.ValidatedPageResponse;
import com.conexia.saludcoop.web.BaseValidatingController;
import com.conexia.saludcoop.web.controller.util.RedireccionamientoUtils;
import com.conexia.saludcoop.web.form.BandejasForm;
import com.conexia.saludcoop.web.form.FiltroBandejasForm;
import com.conexia.saludcoop.web.form.FiltroBandejasHistorialForm;
import com.conexia.saludcoop.web.manager.DiagnosticoManager;
import com.conexia.saludcoop.web.manager.EpsManager;
import com.conexia.saludcoop.web.manager.EstadoAutorizacionManager;
import com.conexia.saludcoop.web.manager.InsumosManager;
import com.conexia.saludcoop.web.manager.MedicamentoManager;
import com.conexia.saludcoop.web.manager.ProcedimientoManager;
import com.conexia.saludcoop.web.manager.RegionalManager;
import com.conexia.saludcoop.web.manager.RoleEstadoManager;
import com.conexia.saludcoop.web.manager.SedeIpsManager;
import com.conexia.saludcoop.web.manager.SolicitudHistorialManager;
import com.conexia.saludcoop.web.manager.SolicitudItemManager;
import com.conexia.saludcoop.web.manager.SolicitudManager;
import com.conexia.saludcoop.web.manager.TipoIdentificacionManager;
import com.conexia.saludcoop.web.manager.TipoPPMManager;
import com.conexia.saludcoop.web.manager.UsuarioEntidadManager;
import com.conexia.saludcoop.web.manager.UsuarioManager;
import com.conexia.saludcoop.web.manager.ValidatorServiceManager;
import com.conexia.saludcoop.web.vo.BandejaItemProjVO;
import com.conexia.saludcoop.web.vo.BandejaSubItemProjVO;
import com.conexia.saludcoop.web.vo.BandejasParamVO;
import com.conexia.saludcoop.web.vo.DescriptivoVO;
import com.conexia.saludcoop.web.vo.DiagnosticoVO;
import com.conexia.saludcoop.web.vo.MedicamentoVO;
import com.conexia.saludcoop.web.vo.PageVO;
import com.conexia.saludcoop.web.vo.ProcedimientoMedicamentoVO;
import com.conexia.saludcoop.web.vo.RegionalVO;
import com.conexia.saludcoop.web.vo.SolicitudHistorialVO;
import com.conexia.saludcoop.web.vo.VOUtils;
import com.conexia.saludcoop.web.xls.XLSGenerator;

/**
 * <b>Bandeja auditor alto costo</b> Clase encargada de controlar las peticiones desde la bandeja para auditores de operaciones de alto
 * costo
 * 
 * @author <a href="mailto:prodas@conexia.com">Pablo Rodas</a>
 * @date 07/10/2013
 * 
 */
@Controller
@SessionAttributes({ "bandejasFilter", "idSolicitudItem", "nombreBandeja", "msgRespuesta", "permiteConsumo","itemsAutorizacion" })
@RequestMapping(value = "/bandejas/")
public class BandejasController extends BaseValidatingController {

    private static Logger LOGGER = LoggerFactory.getLogger(BandejasController.class);

    @Autowired
    private TipoIdentificacionManager tipoIdentAfiliadoManager;

    @Autowired
    private EpsManager epsManager;

    @Autowired
    private RegionalManager regionalManager;

    @Autowired
    private EstadoAutorizacionManager estadoManager;

    @Autowired
    private TipoPPMManager tipoPPMManager;

    @Autowired
    private UsuarioManager userManager;

    @Autowired
    private SedeIpsManager sedeIpsManager;

    @Autowired
    private DiagnosticoManager diagnosticoManager;

    @Autowired
    private SolicitudItemManager solicitudItemManager;

    @Autowired
    private SolicitudManager solicitudManager;

    @Autowired
    private MedicamentoManager medicamentoManager;

    @Autowired
    private InsumosManager insumoManager;

    @Autowired
    private ProcedimientoManager procedimientoManager;

    @Autowired
    private UsuarioEntidadManager usuarioEntidadManager;

    @Autowired
    private SolicitudHistorialManager solicitudHistorialManager;

    @Autowired
    private RoleEstadoManager rolEstadoManager;

    @Autowired
    private ValidatorServiceManager validatorServiceManager;

    @Autowired
    private SolicitudItemDao sd;
    
    @Autowired
    private SolicitudItemVODao solItemVoDao;

    /**
     * 
     * @param map
     * @return
     */
    @RequestMapping(value = "{bandeja}")
    protected String getBandeja(ModelMap map, @PathVariable("bandeja") String bandeja) {

    	try {
    		SaludCoopUserDetails userDetails = (SaludCoopUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userManager.loadUserByUsername(userDetails.getUsername());
            
    		String nombreBandejaAnterior = (String) map.get("nombreBandeja");
    		
    		if (bandeja != null && !bandeja.equals(nombreBandejaAnterior)) {
    			map.put("bandejasFilter", new FiltroBandejasForm());
    		}
    		
    		cargarElementosTipoIdentificacion("tiposDeDocumento", map, tipoIdentAfiliadoManager.getAll());
    		cargarElementosDescriptivo("tiposSolicitud", map, tipoPPMManager.getAll());
    		map.put("eps", VOUtils.toEpsVO(epsManager.findAll()));
    		map.put("regionales", ConverterUtil.tranformObjects(regionalManager.getAll(), RegionalVO.class));
    		
//    		if (SystemConstants.BANDEJA_IPS_MEDICO.equals(bandeja)) {
//    			SaludCoopUserDetails userDetails = (SaludCoopUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    			User user = userManager.loadUserByUsername(userDetails.getUsername());
//    			List<Integer> roles = getRoleFiltro(bandeja);
//    			Integer roleId = ((Role) user.getRoles().toArray()[0]).getId();
//    			
//    			if (roles != null && !roles.isEmpty()) {
//    				roleId = roles.get(0);
//    			}
//    			
//    			cargarElementosDescriptivo("estadosAutorizacion", map, rolEstadoManager.getEstadosByRole(roleId));
//    		} else {
    			cargarElementosDescriptivo("estadosAutorizacion", map, estadoManager.getAll());
//    		}
    		
    		map.put("nombreBandeja", bandeja);
    		
    		if (SystemConstants.BANDEJA_ANULACION.equals(bandeja)) {
    			return "prestador/common/bandejaAutorizacionesAnulacion";
    		} else if (SystemConstants.BANDEJA_REDIRECCION.equals(bandeja)) {
    			map.put("importar", true);
    			return "prestador/common/bandejaAutorizacionesRedireccion";
    		} else if (SystemConstants.BANDEJA_AUTORIZACIONES.endsWith(bandeja)){
    			map.put("permiteConsumo", user.containsAuthority(SystemConstants.AUTHORITY_CONSUMO));
    		}
    		
    		return "auditor/bandeja";
      	} catch (final Exception e) {
	        LOGGER.error("Error al buscar detalle del item", e);
	        return "/common/error";
	    }
    }

    /**
     * 
     * @param map
     * @return
     */
    @RequestMapping(value = "gestionarItem/{itemId}")
    protected String gestionarItem(@PathVariable("itemId") Long id, ModelMap map) {

        String bandeja = (String) map.get("nombreBandeja");
        String bandejaRedireccion = null;
        if (SystemConstants.BANDEJA_AC_NAC.equals(bandeja) || SystemConstants.BANDEJA_AC_REG.equals(bandeja)) {
            bandejaRedireccion = "redirect:/auditorAltoCosto/gestionarItem/" + id;
        } else if (SystemConstants.BANDEJA_CS_NAC.equals(bandeja) || SystemConstants.BANDEJA_CS_REG.equals(bandeja)) {
            bandejaRedireccion = "redirect:/contactService/gestionarItem/" + id;
        } else if (SystemConstants.BANDEJA_CTC_NAC.equals(bandeja) || SystemConstants.BANDEJA_CTC_REG.equals(bandeja)) {
            bandejaRedireccion = "redirect:/auditorCTC/gestionarItem/" + id;
        } else if (SystemConstants.BANDEJA_TUTELAS.equals(bandeja)) {
            bandejaRedireccion = "redirect:/bandejaTutelas/gestionarItem/" + id;
        } else if (SystemConstants.BANDEJA_CONTACT_CENTER.equals(bandeja)) {
            bandejaRedireccion = "redirect:/contactCenter/detalleItem/" + id;
        } else if (SystemConstants.BANDEJA_ANULACION_AUDITOR.equals(bandeja)) {
            bandejaRedireccion = "redirect:/anularAutorizacion/gestionarItem/" + id;
        } else if (SystemConstants.BANDEJA_AUTORIZACIONES.equals(bandeja)) {
            bandejaRedireccion = "redirect:/prestador/gestionarItemAutorizado/" + id;
        } else if (SystemConstants.BANDEJA_IPS_MEDICO.equals(bandeja)) {
            bandejaRedireccion = "redirect:/prestador/gestionarItem/" + id;
        } else if (SystemConstants.BANDEJA_REDIRECCION_AUDITOR.equals(bandeja)) {
            bandejaRedireccion = "redirect:/redireccionarAutorizacion/gestionarItem/" + id;
        } else if (SystemConstants.BANDEJA_ESPECIALIZADA.equals(bandeja)) {
            bandejaRedireccion = "redirect:/auditorCTC/gestionarItem/" + id;
        } else if (SystemConstants.BANDEJA_PROVEEDURIA.equals(bandeja)) {
            //TODO: FACU AQUI DEBE IR EL REQUEST DEL CONTROLADOR PARA GESTIONAR
        }

        return bandejaRedireccion;
    }

    /**
     * Obtiene las solicitudes correspondientes a la bandeja que invoca el método, la bandeja se identifica dependiendo del perfil asociado
     * al usuario.
     * 
     * @param filter
     *            Campos de filtro a aplicar a la bandeja
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "cargarSolicitudes", method = RequestMethod.GET)
    public ValidatedPageResponse<List<BandejaItemProjVO>> getSolicitudes(FiltroBandejasForm filter, ModelMap map) {
        ValidatedPageResponse<List<BandejaItemProjVO>> response = new ValidatedPageResponse<>();

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

            setDatosAdicionalesFiltro(filter);

            Long sedeIpsId = null;
            if (SystemConstants.BANDEJA_IPS_MEDICO.equals(nombreBandeja) || SystemConstants.BANDEJA_AUTORIZACIONES.equals(nombreBandeja)) {
//            	LDF no tiene sedeips
            	if (ue.getSedeIps() != null){
	            	sedeIpsId = ue.getSedeIps().getId();
            	}
            }

            PageVO<BandejaItemProjVO> bandeja = solicitudManager.getSolicitudesIpsMedico(filter, roles, sedeIpsId);

            if (bandeja.getContent().size() <= 0) {
                response.addGeneralError("No existen registros para mostrar");
            } else {
                response.setContent(bandeja.getContent());
                response.setTotalPages(bandeja.getTotalPages());
                response.setActualPage(bandeja.getPaginaActual());
                response.setTotalItems(bandeja.getTotalElements());
            }

            map.put("bandejasFilter", filter);

        } catch (Exception e) {
            LOGGER.error("Error al consultar las solicitudes", e);
            response.addGeneralError("No existen registros para mostrar");
        }
        return response;

    }

    /**
     * Consulta el rol correspondiente para realizar los filtros de acuerdo a la bandeja en la que se encuentra el usuario
     * 
     * @param bandeja
     *            Nombre de la bandeja en la que se encuentra el usuario
     * @return Lista con los ids de los roles correspondientes a la bandeja
     */
    private List<Integer> getRoleFiltro(String bandeja) {

        SaludCoopUserDetails userDetails = (SaludCoopUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userManager.loadUserByUsername(userDetails.getUsername());

        List<Integer> roles = new ArrayList<>();

        for (Role role : user.getRoles()) {
            if (SystemConstants.BANDEJA_AC_NAC.equals(bandeja) || SystemConstants.BANDEJA_AC_REG.equals(bandeja)) {
                if (SystemConstants.ROLE_AUDITOR_AC_NAC.equals(role.getRole())
                        || SystemConstants.ROLE_AUDITOR_AC_REG.equals(role.getRole())) {
                    roles.add(role.getId());
                }
            } else if (SystemConstants.BANDEJA_CS_NAC.equals(bandeja) || SystemConstants.BANDEJA_CS_REG.equals(bandeja)) {
                if (SystemConstants.ROLE_CONTACT_SERVICE_NAC.equals(role.getRole())
                        || SystemConstants.ROLE_CONTACT_SERVICE_REG.equals(role.getRole())) {
                    roles.add(role.getId());
                }
            } else if (SystemConstants.BANDEJA_CTC_NAC.equals(bandeja) || SystemConstants.BANDEJA_CTC_REG.equals(bandeja)) {
                if (SystemConstants.ROLE_AUDITOR_CTC_NAC.equals(role.getRole())
                        || SystemConstants.ROLE_AUDITOR_CTC_REG.equals(role.getRole())) {
                    roles.add(role.getId());
                }
            } else if (SystemConstants.BANDEJA_TUTELAS.equals(bandeja)) {
                if (SystemConstants.ROLE_AUDITOR_TUTELAS.equals(role.getRole()) || SystemConstants.ROLE_LINEA_FRENTE.equals(role.getRole())) {
                    roles.add(role.getId());
                }
            } else if (SystemConstants.BANDEJA_CONTACT_CENTER.equals(bandeja)) {
                if (SystemConstants.ROLE_CONTACT_CENTER.equals(role.getRole())) {
                    roles.add(role.getId());
                }
            } else if (SystemConstants.BANDEJA_IPS_MEDICO.equals(bandeja)) {
                if (SystemConstants.ROLE_MEDICO.equals(role.getRole()) || SystemConstants.ROLE_IPS.equals(role.getRole())
                        || SystemConstants.ROLE_RECEPCION_IPS.equals(role.getRole())
                        || SystemConstants.ROLE_LINEA_FRENTE.equals(role.getRole())
                        || SystemConstants.ROLE_BACKOFFICE_LDF.equals(role.getRole())) {
                    roles.add(role.getId());
                }
            } else if (SystemConstants.BANDEJA_AUTORIZACIONES.equals(bandeja)) {
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
            } else if (SystemConstants.BANDEJA_REDIRECCION.equals(bandeja)) {
                if (SystemConstants.ROLE_LINEA_FRENTE.equals(role.getRole())) {
                    roles.add(role.getId());
                }
            } else if (SystemConstants.BANDEJA_ANULACION.equals(bandeja)) {
                if (SystemConstants.ROLE_LINEA_FRENTE.equals(role.getRole())) {
                    roles.add(role.getId());
                }
            } else if (SystemConstants.BANDEJA_ESPECIALIZADA.equals(bandeja)) {
                if (RoleUtils.getListStr_Roles_EspecialidadesNivel5().contains(role.getRole())) {
                    roles.add(role.getId());
                }
            }
        }

        return roles;
    }

    /**
     * Obtiene los items de una solicitud dado su número.
     * 
     * @param numeroSolicitud
     *            Número de la solicitud cuyos items se desean buscar.
     * @author Julio Sejtman
     */
    @RequestMapping(value = "cargarItemsSolicitud", method = RequestMethod.GET)
    @ResponseBody
    public ValidatedPageResponse<List<BandejaSubItemProjVO>> getItemsSolicitudes(FiltroBandejasForm filter, ModelMap map) {
        return getFunSolicitudes(filter, null, map);
    }

    @RequestMapping(value = "cargarItemsSolicitud/{tipoBandeja}", method = RequestMethod.GET)
    @ResponseBody
    public ValidatedPageResponse<List<BandejaSubItemProjVO>> getSolicitudesRedireccion(FiltroBandejasForm filter,
            @PathVariable("tipoBandeja") String tipoBandeja, ModelMap map) {
        return getFunSolicitudes(filter, tipoBandeja, map);
    }

    private ValidatedPageResponse<List<BandejaSubItemProjVO>> getFunSolicitudes(FiltroBandejasForm filter, String tipoBandeja, ModelMap map) {
        ValidatedPageResponse<List<BandejaSubItemProjVO>> response = new ValidatedPageResponse<>();
        try {
        	
            String nombreBandeja = (String) map.get("nombreBandeja");
            List<Integer> roles = getRoleFiltro(nombreBandeja);

            if (roles == null || roles.isEmpty()) {
                response.addGeneralError("No existen registros para mostrar");
                return response;
            }

            setDatosAdicionalesFiltro(filter);

            filter.setBandeja(nombreBandeja);

            List<BandejaSubItemProjVO> items = solicitudManager.getItemsSolicitudesUpsMedico(filter, roles);
            for (BandejaSubItemProjVO bandejaSubItemProjVO : items) {
                if (tipoBandeja != null)
                    putNivelAutorizacionAuditor(bandejaSubItemProjVO, tipoBandeja);
            }
            if (items.size() > 0) {
                response.setContent(items);
            } else {
                response.addGeneralError("No existen registros para mostrar");
            }

            return response;
        } catch (final Exception e) {
            LOGGER.error("Error buscando los items de las solicitudes", e);
            response.addGeneralError("Error buscando los items de las solicitudes");
            return response;
        }
    }

    /**
     * Calcula si se debe delegar o no respecto al nivel de autorizacion
     * 
     * @param tipoBandeja
     * 
     * @param solicitudesVo
     */
    private void putNivelAutorizacionAuditor(BandejaSubItemProjVO subitem, String tipoBandeja) {
        try {
            ProcedimientoDto procedimiento = new ProcedimientoDto();
            MedicamentoDto medicamento = new MedicamentoDto();
            InsumoDto insumo = new InsumoDto();
            if(subitem.getIdInsumo() != null){
                insumo = insumoManager.findById(subitem.getIdInsumo());
            }else if(subitem.getIdMedicamento() != null){
                medicamento = medicamentoManager.findById(subitem.getIdMedicamento());
            }else if(subitem.getIdProcedimiento() != null){
                procedimiento = procedimientoManager.findById(subitem.getIdProcedimiento());
            }
            RedireccionamientoUtils.obtenerNivelAutorizador(insumo, medicamento, procedimiento, subitem, tipoBandeja);
        } catch (Exception e) {
            LOGGER.error("Error calculando el nivel de autorizacion", e);
            try {
                throw e;
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
    /**
     * Consulta las sedes ips disponibles en el sistema y las retorna para ser mostradas en el componente de selección de sede de IPS
     * 
     * @return
     * @throws Throwable
     */
    @ResponseBody
    @RequestMapping(value = "listarSedeIps", method = RequestMethod.GET)
    public String listarIpsConItem(Long itemId, ModelMap map) throws Throwable {
        String jsonString = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            TopTenSedeIpsDto tenSedeIpsDto = new TopTenSedeIpsDto();
            tenSedeIpsDto.setIdSolicitudItem(itemId);
            RespuestaCompuesta<Set<SedeIpsDto>> respuestaCompuesta = validatorServiceManager.consultarTopTenSedeIps(tenSedeIpsDto);
            jsonString = mapper.writeValueAsString(respuestaCompuesta.getTransactionData());
            jsonString = "{\"aaData\":" + jsonString + "}";

        } catch (Throwable e) {
            LOGGER.error("Error respuesta Top 10 ips", e);
            jsonString = mapper.writeValueAsString(new HashSet<SedeIpsDto>());
            jsonString = "{\"aaData\":" + jsonString + "}";
        }
        return jsonString;
    }

    @ResponseBody
    @RequestMapping(value = "listarSedeIpsGrupo/{autorizacionId}", method = RequestMethod.GET)
    public String listarSedeIpsGrupo(@PathVariable("autorizacionId") Long autorizacionId) throws Throwable {
        String jsonString = null;
        try {
            TopTenSedeIpsDto tenSedeIpsDto = new TopTenSedeIpsDto();
            ObjectMapper mapper = new ObjectMapper();
            tenSedeIpsDto.setIdAutorizacion(autorizacionId);
            RespuestaCompuesta<Set<SedeIpsDto>> respuestaCompuesta = validatorServiceManager.consultarTopTenSedeIps(tenSedeIpsDto);
            jsonString = mapper.writeValueAsString(respuestaCompuesta.getTransactionData());
            jsonString = "{\"aaData\":" + jsonString + "}";

        } catch (Throwable e) {
            LOGGER.error("Error respuesta Top 10 ips", e);
            throw e;
        }
        return jsonString;
    }

    /**
     * Consulta los diagnósticos disponibles en el sistema y los retorna para ser mostrados en el componente de selección de Diagnósticos
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "listarDiagnosticos", method = RequestMethod.GET)
    public String listarDiagnosticos() {
        List<DiagnosticoDto> dtos = diagnosticoManager.findAll();
        List<DiagnosticoVO> vos = VOUtils.toDiagnosticoVO(dtos);

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(vos);
        } catch (JsonGenerationException | JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        jsonString = "{\"aaData\":" + jsonString + "}";

        return jsonString;
    }

    /**
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "listarMedicamentosHomologos", method = RequestMethod.GET)
    public String listarMedicamentos(String codigo, String descripcion) {

        List<MedicamentoDto> medicamentosDto = medicamentoManager.getMedicamentosPOSByCodigoDescripcion(codigo, descripcion);
        List<MedicamentoVO> vos = VOUtils.toMedicamentoVO(medicamentosDto);

        String jsonString = null;

        try {
            jsonString = new ObjectMapper().writeValueAsString(vos);
        } catch (JsonGenerationException | JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        jsonString = "{\"aaData\":" + jsonString + "}";

        return jsonString;
    }

    /**
     * Consulta los diagnósticos disponibles en el sistema y los retorna para ser mostrados en el componente de selección de Diagnósticos
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "listarProcedimientosHomologos", method = RequestMethod.GET)
    public String listarProcedimientosHomologos(String codigo, String descripcion) {
        List<ProcedimientoDto> dtos = procedimientoManager.getProcedimientosPosByCodigoDescripcion(codigo, descripcion);
        List<ProcedimientoMedicamentoVO> vo = new ArrayList<ProcedimientoMedicamentoVO>();
        for (ProcedimientoDto objDto : dtos) {
            ProcedimientoMedicamentoVO objVO = new ProcedimientoMedicamentoVO(objDto);
            vo.add(objVO);
        }
        String jsonString = null;
        try {
            jsonString = new ObjectMapper().writeValueAsString(vo);
        } catch (JsonGenerationException | JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        jsonString = "{\"aaData\":" + jsonString + "}";

        return jsonString;
    }

    @RequestMapping(value = "listarHistorial", method = RequestMethod.GET)
    @ResponseBody
    protected ValidatedPageResponse<List<SolicitudHistorialVO>> listarHistorial(FiltroBandejasHistorialForm filter)
            throws ServletException, IOException {

        ValidatedPageResponse<List<SolicitudHistorialVO>> response = new ValidatedPageResponse<>();
        try {

            // Parseo de fechas
            Date fechaDesde = null;
            if (StringUtils.isNotBlank(filter.getFechaDesde())) {
                fechaDesde = DateUtilities.getDateForString(filter.getFechaDesde(), "dd-MM-yyyy");
            }

            Date fechaHasta = null;
            if (StringUtils.isNotBlank(filter.getFechaHasta())) {
                fechaHasta = DateUtilities.getDateForString(filter.getFechaHasta(), "dd-MM-yyyy");
            }

            // Si la pagina actual es mayor etonces verifico el tamaño del resultado
            if (filter.getActualPage() == 1) {

                // Se busca la cantidad de resultados de la busqueda
                Long count = solicitudHistorialManager.countResults(filter.getTipoDocumento(), filter.getNumeroDocumento(),
                        filter.getNumeroSolicitud(), filter.getEstadoSolicitud(), filter.getRegional(), fechaDesde, fechaHasta);

                // Si la cantidad de registros es 0 retorna mensaje de error
                if (count == 0) {
                    response.addGeneralError("No existen registros para mostrar");
                    return response;
                }

                // Si la cantidad de resultado es mayor a 10 paginas se retorna error
                // para que se realice un llamado a la creacion de pdf desde el navegador
                if (count > 10 * Pagination.DEFAULT_PAGE_SIZE) {
                    response.addGeneralErrors("sizeExceeds");

                    return response;
                }
            }

            // Se carga el historial
            PageVO<SolicitudHistorialVO> historial = solicitudHistorialManager.getHistorial(filter.getTipoDocumento(),
                    filter.getNumeroDocumento(), filter.getNumeroSolicitud(), filter.getEstadoSolicitud(), filter.getRegional(),
                    fechaDesde, fechaHasta, filter.getActualPage());

            if (historial.getTotalElements() <= 0) {
                response.addGeneralError("No existen registros para mostrar");
            } else {
                response.setContent(historial.getContent());
                response.setTotalPages(historial.getTotalPages());
                response.setActualPage(historial.getPaginaActual());
            }

            return response;

        } catch (final Exception e) {
            LOGGER.error("Error buscando el historial de solicitudes", e);
            response.addGeneralError("Error buscando el historial de solicitudes");
            return response;
        }

    }

    @RequestMapping(value = "downloadHistorial", method = RequestMethod.GET)
    protected void donwloadHistorial(FiltroBandejasHistorialForm filter, HttpServletResponse response) throws ServletException, IOException {

        try {

            // Parseo de fechas
            Date fechaDesde = null;
            if (StringUtils.isNotBlank(filter.getFechaDesde())) {
                fechaDesde = DateUtilities.getDateForString(filter.getFechaDesde(), "dd-MM-yyyy");
            }

            Date fechaHasta = null;
            if (StringUtils.isNotBlank(filter.getFechaHasta())) {
                fechaHasta = DateUtilities.getDateForString(filter.getFechaHasta(), "dd-MM-yyyy");
            }

            try {

                byte[] pdf = solicitudHistorialManager.buildHistorialPDF(filter.getTipoDocumento(), filter.getNumeroDocumento(),
                        filter.getNumeroSolicitud(), filter.getEstadoSolicitud(), filter.getRegional(), fechaDesde, fechaHasta);

                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment; filename=\"Historico.pdf\"");
                response.setHeader("Pragma", "no-cache");
                response.setHeader("Cache-Control", "no-cache");
                response.setContentLength(pdf.length);
                response.getOutputStream().write(pdf);
                response.flushBuffer();

            } catch (IOException e) {
                LOGGER.error("Error de E/S.", e);
            }

        } catch (Exception e) {
            LOGGER.error("Error buscando el historial de solicitudes", e);
        }

    }

    /**
     * Establece el tipo de solicitud para el filtro, en los casos que aplique
     * 
     * @param filter
     */
    public void setDatosAdicionalesFiltro(FiltroBandejasForm filter) {

        // Se verifica si es necesario establecer el tipo de solicitud
        if (filter.getTipoSolicitud() == null) {

            // Se obtiene el usuario en sesión
            SaludCoopUserDetails userDetails = (SaludCoopUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userManager.loadUserByUsername(userDetails.getUsername());

            // Se obtiene el tipo de solicitud de acuerdo al rol del usuario
            for (Role role : user.getRoles()) {
                if (ROLE_AUDITOR_AC_NAC.equals(role.getRole()) || ROLE_AUDITOR_AC_REG.equals(role.getRole())) {
                    filter.setTipoSolicitud(tipoPPMManager.getByCodigo(PPM_ACO).getId());
                    break;
                } else if (ROLE_CONTACT_SERVICE_REG.equals(role.getRole()) || ROLE_CONTACT_SERVICE_NAC.equals(role.getRole())) {
                    filter.setTipoSolicitud(tipoPPMManager.getByCodigo(PPM_ACO).getId());
                    break;
                } else if (ROLE_AUDITOR_CTC_NAC.equals(role.getRole()) || ROLE_AUDITOR_CTC_REG.equals(role.getRole())) {
                    filter.setTipoSolicitud(tipoPPMManager.getByCodigo(PPM_NOP).getId());
                    break;
                } else if (RoleUtils.getRolesIPS().contains(role.getId())) {
                    UsuarioEntidadDto ue = usuarioEntidadManager.getUsuarioEntidadByUsuarioId(user.getId());
                    filter.setIpsId(ue.getSedeIps().getId());
                    break;
                }
            }
        }

        if (Boolean.TRUE.equals(filter.getEsTutela())) {
            filter.setEstadoSolicitud(EstadoAutorizacion.PENDIENTE_TUTELA);
        }

    }

    /**
     * Verifica las opciones que deben ser mostradas de acuerdo al tipo de bandeja y al estado en el que se encuentra la solicitud
     * 
     * @param param
     *            Objeto con los parámetros para las bandejas
     * @param tipoAuditor
     *            Indica el tipo de auditor que está ejecutando la solicitud. Usar las constantes {@link SystemConstants#AUDITOR_AC} y
     *            {@link SystemConstants#AUDITOR_CTC}
     */
    public static void validarMostrarOpciones(BandejasParamVO param, int tipoAuditor) {

        // Se verifica el tipo de bandeja
        if (SystemConstants.BANDEJA_NACIONAL == param.getTipoBandeja()) {

            // Se verifica el tipo de auditor para el que se mostrarán las opciones
            if (SystemConstants.AUDITOR_CTC == tipoAuditor) {
                // Se verifica el estado de la solicitud para determinar las opciones a mostrar
                if (EstadoAutorizacion.APROBADA_REGIONAL == param.getEstado() || EstadoAutorizacion.NEGADA_REGIONAL == param.getEstado()
                        || EstadoAutorizacion.RESPUESTA_REGIONAL == param.getEstado()) {
                    // El auditor nacional CTC puede puede Aprobar, No aprobar y Devolver solicitudes
                    param.setMostrarAcciones(new boolean[] { true, true, false, true, false });
                    param.setEditable(Boolean.TRUE);
                    param.setEditableRedireccion(param.isEditable());
                }
            } else if (SystemConstants.AUDITOR_ESPECIALIZADO == tipoAuditor) {
                // Se verifica el estado de la solicitud para determinar las opciones a mostrar
                if (EstadoAutorizacion.PENDIENTE_CTC == param.getEstado() || EstadoAutorizacion.RESPUESTA_IPS == param.getEstado()
                        || EstadoAutorizacion.RESPUESTA_REGIONAL == param.getEstado()
                        || EstadoAutorizacion.APROBADA_REGIONAL == param.getEstado()
                        || EstadoAutorizacion.NEGADA_REGIONAL == param.getEstado()
                        || EstadoAutorizacion.PENDIENTE_AUDITORIA_ESPECIALIZADA == param.getEstado()) {
                    // El auditor especializado puede puede Aprobar y Devolver solicitudes
                    param.setMostrarAcciones(new boolean[] { true, false, false, true, false });
                    param.setEditable(Boolean.TRUE);
                    param.setEditableRedireccion(param.isEditable());
                }
            } else if (SystemConstants.AUDITOR_AC == tipoAuditor) {
                // Se verifica el estado de la solicitud para determinar las opciones a mostrar
                if (EstadoAutorizacion.PENDIENTE_ALTO_COSTO == param.getEstado() || EstadoAutorizacion.RESPUESTA_IPS == param.getEstado()
                        || EstadoAutorizacion.RESPUESTA_REGIONAL == param.getEstado()) {
                    // El auditor nacional alto costo puede puede Autorizar, Anular y Devolver solicitudes
                    param.setMostrarAcciones(new boolean[] { true, true, true, true, false });
                    param.setEditable(Boolean.TRUE);
                    param.setEditableRedireccion(param.isEditable());
                }
            } else if (SystemConstants.CONTACT_SERVICE == tipoAuditor) {
                // Se verifica el estado de la solicitud para determinar las opciones a mostrar
                if (EstadoAutorizacion.PENDIENTE_CONTACT_SERVICE == param.getEstado()
                        || EstadoAutorizacion.RESPUESTA_IPS == param.getEstado()
                        || EstadoAutorizacion.RESPUESTA_REGIONAL == param.getEstado()) {
                    // El usuario de contact service nacional puede puede Autorizar, Anular y Devolver solicitudes
                    param.setMostrarAcciones(new boolean[] { true, true, true, true, false });
                    param.setEditable(Boolean.TRUE);
                    param.setEditableRedireccion(param.isEditable());
                }
            } else if (SystemConstants.AUDITOR_TUTELAS == tipoAuditor) {
                // El auditor de tutelas puede aprobar, escalar, y devolver
                param.setMostrarAcciones(new boolean[] { true, false, true, true, true });
                param.setEditable(Boolean.TRUE);
                param.setEditableRedireccion(Boolean.TRUE);
            } else if (SystemConstants.LINEA_FRENTE == tipoAuditor) {
                // El usuario de línea de frente puede escalar y enviar a tutela
                param.setMostrarAcciones(new boolean[] { false, true, true, false, false });
                param.setEditable(Boolean.FALSE);
                param.setEditableRespuesta(Boolean.TRUE);
                param.setEditableRedireccion(Boolean.FALSE);
            }
        } else if (SystemConstants.BANDEJA_REGIONAL == param.getTipoBandeja()) {

            // Se verifica el tipo de auditor para el que se mostrarán las opciones
            if (SystemConstants.AUDITOR_CTC == tipoAuditor) {

                // Se verifica el estado de la solicitud para determinar las opciones a mostrar
                if (EstadoAutorizacion.PENDIENTE_CTC == param.getEstado()) {
                    // El auditor regional CTC puede puede Aprobar, No aprobar, Anular y Devolver solicitudes
                    param.setMostrarAcciones(new boolean[] { true, true, true, true, false });
                    param.setEditable(Boolean.TRUE);
                    param.setEditableRedireccion(param.isEditable());
                } else if (EstadoAutorizacion.RESPUESTA_IPS == param.getEstado()) {
                    // Si la solicitud fue devuelta por la IPS, el auditor regional CTC puede puede Aprobar, No aprobar, Anular y Devolver
                    // solicitudes
                    param.setMostrarAcciones(new boolean[] { true, true, true, true, false });
                    param.setEditable(Boolean.TRUE);
                    param.setEditableRedireccion(param.isEditable());
                } else if (EstadoAutorizacion.DEVUELTA_REGIONAL == param.getEstado()) {
                    // Si la solicitud fue devuelta por el auditor nacional, el auditor regional CTC puede puede Anular y Responder
                    // solicitudes
                    param.setMostrarAcciones(new boolean[] { false, false, true, true, true });
                    param.setEditable(Boolean.FALSE);
                    param.setEditableRedireccion(param.isEditable());
                    param.setEditableRespuesta(Boolean.TRUE);
                }
            } else if (SystemConstants.AUDITOR_AC == tipoAuditor) {

                // Se verifica el estado de la solicitud para determinar las opciones a mostrar
                if (EstadoAutorizacion.DEVUELTA_REGIONAL == param.getEstado() || EstadoAutorizacion.DEVUELTA_IPS == param.getEstado()) {
                    // El usuario de alto costo regional solo puede responder la solicitud
                    param.setMostrarAcciones(new boolean[] { false, false, false, false, true });
                    param.setEditable(Boolean.FALSE);
                    param.setEditableRedireccion(param.isEditable());
                    param.setEditableRespuesta(Boolean.TRUE);
                }
            } else if (SystemConstants.CONTACT_SERVICE == tipoAuditor) {

                // Se verifica el estado de la solicitud para determinar las opciones a mostrar
                if (EstadoAutorizacion.DEVUELTA_REGIONAL == param.getEstado() || EstadoAutorizacion.DEVUELTA_IPS == param.getEstado()) {
                    // El usuario de contact service regional solo puede responder la solicitud
                    param.setMostrarAcciones(new boolean[] { false, false, false, false, true });
                    param.setEditable(Boolean.FALSE);
                    param.setEditableRedireccion(param.isEditable());
                    param.setEditableRespuesta(Boolean.TRUE);
                }
            }
        }
    }

    /**
     * Verifica la sección de devoluciones que debe ser mostrada, dependiendo del tipo de bandeja y el estado en el que se encuentra la
     * solicitud
     * 
     * @param param
     *            Objeto con los parámetros para las bandejas
     * @param tipoAuditor
     *            Indica el tipo de auditor que está ejecutando la solicitud. Usar las constantes {@link SystemConstants#AUDITOR_AC} y
     *            {@link SystemConstants#AUDITOR_CTC}
     */
    public static void validarCaseDevolucion(BandejasParamVO param, int tipoAuditor) {

        // Se verifica el tipo de bandeja
        if (SystemConstants.BANDEJA_NACIONAL == param.getTipoBandeja()) {

            // Se verifica el tipo de auditor para el que se mostrarán las opciones
            if (SystemConstants.AUDITOR_CTC == tipoAuditor) {
                if (EstadoAutorizacion.RESPUESTA_REGIONAL == param.getEstado() || EstadoAutorizacion.APROBADA_REGIONAL == param.getEstado()
                        || EstadoAutorizacion.NEGADA_REGIONAL == param.getEstado()) {
                    // El auditor nacional CTC ve el concepto dado por el auditor regional
                    param.setCaseDevoluciones(1);
                } else if (EstadoAutorizacion.DEVUELTA_IPS == param.getEstado()) {
                    param.setCaseDevoluciones(6);
                } else if (EstadoAutorizacion.RESPUESTA_IPS == param.getEstado()) {
                    param.setCaseDevoluciones(3);
                } else if (EstadoAutorizacion.PENDIENTE_CTC != param.getEstado()) {
                    param.setCaseDevoluciones(5);
                }
            } else if (SystemConstants.AUDITOR_ESPECIALIZADO == tipoAuditor) {
                if (EstadoAutorizacion.RESPUESTA_REGIONAL == param.getEstado() || EstadoAutorizacion.APROBADA_REGIONAL == param.getEstado()
                        || EstadoAutorizacion.NEGADA_REGIONAL == param.getEstado()) {
                    // El auditor nacional CTC ve el concepto dado por el auditor regional
                    param.setCaseDevoluciones(1);
                } else if (EstadoAutorizacion.RESPUESTA_IPS == param.getEstado()) {
                    param.setCaseDevoluciones(3);
                }
            } else if (SystemConstants.AUDITOR_AC == tipoAuditor) {
                // Se verifica si es necesario mostrar la información de devolución dependiendo del estado de la solicitud
                if (EstadoAutorizacion.RESPUESTA_REGIONAL == param.getEstado()) {
                    param.setCaseDevoluciones(3);
                } else if (EstadoAutorizacion.RESPUESTA_IPS == param.getEstado()) {
                    param.setCaseDevoluciones(4);
                } else if (EstadoAutorizacion.PENDIENTE_ALTO_COSTO != param.getEstado()) {
                    param.setCaseDevoluciones(5);
                }
            } else if (SystemConstants.CONTACT_SERVICE == tipoAuditor) {
                // Se verifica si es necesario mostrar la información de devolución dependiendo del estado de la solicitud
                if (EstadoAutorizacion.RESPUESTA_REGIONAL == param.getEstado()) {
                    param.setCaseDevoluciones(3);
                } else if (EstadoAutorizacion.RESPUESTA_IPS == param.getEstado()) {
                    param.setCaseDevoluciones(4);
                } else if (EstadoAutorizacion.PENDIENTE_CONTACT_SERVICE != param.getEstado()) {
                    param.setCaseDevoluciones(5);
                }
            } else if (SystemConstants.AUDITOR_TUTELAS == tipoAuditor) {
                // Se verifica si la solicitud fue respuesta por la IPS o línea de frente
                if (EstadoAutorizacion.RESPUESTA_IPS == param.getEstado()) {
                    param.setCaseDevoluciones(1);
                }
            }
        } else if (SystemConstants.BANDEJA_REGIONAL == param.getTipoBandeja()) {

            // Se verifica el tipo de auditor para el que se mostrarán las opciones
            if (SystemConstants.AUDITOR_CTC == tipoAuditor) {

                if (EstadoAutorizacion.RESPUESTA_IPS == param.getEstado()) {
                    // El auditor regional ve la respuesta de la IPS
                    param.setCaseDevoluciones(3);
                } else if (EstadoAutorizacion.DEVUELTA_REGIONAL == param.getEstado()) {
                    // El auditor regional ve el concepto dado por el auditor nacional
                    param.setCaseDevoluciones(2);
                } else if (EstadoAutorizacion.PENDIENTE_ACTA == param.getEstado() || EstadoAutorizacion.ANULADA == param.getEstado()
                        || EstadoAutorizacion.NEGADA_NACIONAL == param.getEstado()) {
                    param.setCaseDevoluciones(5);
                }
            } else if (SystemConstants.AUDITOR_AC == tipoAuditor) {
                if (EstadoAutorizacion.DEVUELTA_REGIONAL == param.getEstado() || EstadoAutorizacion.RESPUESTA_REGIONAL == param.getEstado()) {
                    // El auditor regional ve el concepto dado por el auditor nacional
                    param.setCaseDevoluciones(1);
                } else if (EstadoAutorizacion.DEVUELTA_IPS == param.getEstado()) {
                    // La ips ve el concepto dado por el auditor nacional
                    param.setCaseDevoluciones(2);
                } else if (EstadoAutorizacion.PENDIENTE_ALTO_COSTO != param.getEstado()) {
                    param.setCaseDevoluciones(5);
                }
            } else if (SystemConstants.CONTACT_SERVICE == tipoAuditor) {
                if (EstadoAutorizacion.DEVUELTA_REGIONAL == param.getEstado() || EstadoAutorizacion.RESPUESTA_REGIONAL == param.getEstado()) {
                    // El auditor regional ve el concepto dado por el auditor nacional
                    param.setCaseDevoluciones(1);
                } else if (EstadoAutorizacion.DEVUELTA_IPS == param.getEstado()) {
                    // La ips ve el concepto dado por el auditor nacional
                    param.setCaseDevoluciones(2);
                } else if (EstadoAutorizacion.PENDIENTE_CONTACT_SERVICE != param.getEstado()) {
                    param.setCaseDevoluciones(5);
                }
            }
        }
    }

    /**
     * Verifica si el valor de las unidades aprobadas para el medicamento enviadas por parámetro, cumplen con el rango establecido para el
     * medicamento
     * 
     * @param form
     * @param map
     * @return
     */
    @RequestMapping(value = "cumpleRangoMedicamento")
    @ResponseBody
    public Object[] cumpleRangoMedicamento(BandejasForm form, ModelMap map) {
        try {
            Long id = (Long) map.get("idSolicitudItem");
            return medicamentoManager.validarRangoEntrega(id, form.getUnidadesAprobadas(), form.getDiasPeriodo(), form.getNumeroEntregas());
        } catch (Throwable e) {
            LOGGER.error("Error al obtener el rango" + e);
            return new Object[] { -1, "Error al obtener el rango" };
        }
    }

    /**
     * Calcula las fechas de entrega del medicamento que se está autorizando para mostrarlas en pantalla
     * 
     * @param form
     * @return
     */
    @RequestMapping(value = "fechasEntrega")
    @ResponseBody
    public String fechasEntrega(BandejasForm form) {
        List<DescriptivoVO> descriptivoVOs = new ArrayList<DescriptivoVO>();
        Integer numeroEntregas = form.getNumeroEntregas();
        Integer diasPeriodo = form.getDiasPeriodo();
        String formato = SystemConstants.DATE_PATTERN_SIMPLE;
        Calendar fechaRecorrido = Calendar.getInstance();
        for (int i = 0; i < numeroEntregas; i++) {
            DescriptivoVO descriptivoVO = new DescriptivoVO();
            String fechaFormateada = DateUtilities.parseDateTime(fechaRecorrido.getTime(), formato);
            descriptivoVO.setId(i + 1);
            descriptivoVO.setDescripcion(fechaFormateada);
            descriptivoVOs.add(descriptivoVO);
            fechaRecorrido.add(Calendar.DAY_OF_YEAR, diasPeriodo);
        }
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(descriptivoVOs);
        } catch (JsonGenerationException | JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        jsonString = "{\"aaData\":" + jsonString + "}";
        return jsonString;
    }

    @RequestMapping(value = "clearMsgRespuesta")
    public void clearMsgRespuesta(ModelMap map) {
        map.put("msgRespuesta", "");
    }
    
    @RequestMapping(value="downloadXls/{bandeja}")
    public void downloadXls(@PathVariable("bandeja") String bandeja, FiltroBandejasForm filtro, ModelMap map, HttpServletResponse response) throws IOException{
    	
    	List<Integer> roles = getRoleFiltro(bandeja);
    	setDatosAdicionalesFiltro(filtro);
    
    	HSSFWorkbook documento =null;
    	String nombreArchivo="";
    	if (SystemConstants.BANDEJA_AC_NAC.equals(bandeja) 
    			|| SystemConstants.BANDEJA_AC_REG.equals(bandeja)){
    		documento = XLSGenerator.generarXLSBandejaAC(solItemVoDao.getReporte(filtro.getNumeroSolicitud(), filtro.getTipoDocumento(), filtro.getNumeroDocumento(), filtro.getEps(), filtro.getRegional()!=null?filtro.getRegional().longValue():null, filtro.getEstadoSolicitud(), filtro.getTecnologia(), roles));
    		nombreArchivo = "ReporteBandejaAltoCosto";
    	}else if(SystemConstants.BANDEJA_TUTELAS.equals(bandeja)){
    		documento = XLSGenerator.generarXLSBandejaTutela(solItemVoDao.getReporte(filtro.getNumeroSolicitud(), filtro.getTipoDocumento(), filtro.getNumeroDocumento(), filtro.getEps(), filtro.getRegional()!=null?filtro.getRegional().longValue():null, filtro.getEstadoSolicitud(), filtro.getTecnologia(), roles));
    		nombreArchivo = "ReporteBandejaTutelas";
    	}else if(SystemConstants.BANDEJA_CTC_NAC.equals(bandeja) 
    			|| SystemConstants.BANDEJA_CTC_REG.equals(bandeja)){
    		documento = XLSGenerator.generarXLSBandejaCtc(solItemVoDao.getReporte(filtro.getNumeroSolicitud(), filtro.getTipoDocumento(), filtro.getNumeroDocumento(), filtro.getEps(), filtro.getRegional()!=null?filtro.getRegional().longValue():null, filtro.getEstadoSolicitud(), filtro.getTecnologia(), roles));
    		nombreArchivo = "ReporteBandejaCTC";
    	}
    	
    	if (null != documento) {
    		String fechaHoy = LocalDate.fromCalendarFields(new GregorianCalendar()).toString("dd/MM/yyyy");
			
			try {
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "attachment; filename=\"" + nombreArchivo + "-"+fechaHoy + ".xls\"");
				response.setHeader("Pragma", "no-cache");
				response.setHeader("Cache-Control", "no-cache");
				documento.write(response.getOutputStream());
				response.flushBuffer();

			} catch (final IOException e) {
				LOGGER.error("Error de E/S.", e);
			}
		}
    	
    }
    
    
    @RequestMapping(value = "verificarNivelAutorizacionGrupo/{numeroAutorizacion}", method = RequestMethod.GET)
    @ResponseBody
    public String verificarNivelAutorizacionGrupo(FiltroBandejasForm filter,
            @PathVariable("numeroAutorizacion") Long numeroAutorizacion, ModelMap map) {
    	String nombreBandeja = (String) map.get("nombreBandeja");
        List<Integer> roles = getRoleFiltro(nombreBandeja);
        if (roles == null || roles.isEmpty()) {
           return null;
        }
        setDatosAdicionalesFiltro(filter);
        filter.setBandeja(nombreBandeja);
        filter.setNumeroAutorizacion(numeroAutorizacion);
        List<BandejaSubItemProjVO> items = solicitudManager.getItemsSolicitudesUpsMedico(filter, roles);
        int cont=0;
		for (BandejaSubItemProjVO bandejaSubItemProjVO : items) {
			putNivelAutorizacionAuditor(bandejaSubItemProjVO, SystemConstants.REDIRECCIONAMIENTO);
			if (bandejaSubItemProjVO.getEsNivelAutorizacionAuditor())
				cont++;
		}
		map.put("itemsAutorizacion", items);
		if(cont==0)return RedireccionamientoUtils.REDIRECCION;
		else if(cont==items.size())return RedireccionamientoUtils.ESCALAR;
		else  return RedireccionamientoUtils.REDIRECCION_ESCALAR;
    }
    
   
}