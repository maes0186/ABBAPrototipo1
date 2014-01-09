package com.conexia.saludcoop.web.controller.prestador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

import com.conexia.saludcoop.common.dto.DescriptivoDto;
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
import com.conexia.saludcoop.common.repository.SolicitudItemDao;
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
import com.conexia.saludcoop.web.manager.CausaExternaManager;
import com.conexia.saludcoop.web.manager.CausalAnulacionManager;
import com.conexia.saludcoop.web.manager.CausalDevolucionManager;
import com.conexia.saludcoop.web.manager.CriterioNegacionManager;
import com.conexia.saludcoop.web.manager.EntidadRecobroManager;
import com.conexia.saludcoop.web.manager.EpsManager;
import com.conexia.saludcoop.web.manager.EstadoAutorizacionManager;
import com.conexia.saludcoop.web.manager.FinalidadManager;
import com.conexia.saludcoop.web.manager.LateralidadManager;
import com.conexia.saludcoop.web.manager.ObjetivoProcedimientoManager;
import com.conexia.saludcoop.web.manager.RegionalManager;
import com.conexia.saludcoop.web.manager.SolicitudItemManager;
import com.conexia.saludcoop.web.manager.TipoCatastroficoManager;
import com.conexia.saludcoop.web.manager.TipoIdentificacionManager;
import com.conexia.saludcoop.web.manager.TipoPPMManager;
import com.conexia.saludcoop.web.manager.UsuarioManager;
import com.conexia.saludcoop.web.manager.ValidatorServiceManager;
import com.conexia.saludcoop.web.manager.ViaAdministracionManager;
import com.conexia.saludcoop.web.vo.BandejaAutorizacionItemProjVO;
import com.conexia.saludcoop.web.vo.BandejaSubItemProjVO;
import com.conexia.saludcoop.web.vo.BandejasVO;
import com.conexia.saludcoop.web.vo.DocumentoSoporteVO;
import com.conexia.saludcoop.web.vo.PageVO;
import com.conexia.saludcoop.web.vo.RegionalVO;
import com.conexia.saludcoop.web.vo.VOUtils;

/**
 * @author mcuervo
 * 
 */
@Controller
@SessionAttributes({ "idSolicitudItem", "nombreBandeja","itemsAutorizacion" })
public class BandejaAutorizacionesRedireccionamientoController extends BaseValidatingController {

    private static Logger LOGGER = LoggerFactory.getLogger(BandejaAutorizacionesRedireccionamientoController.class);

    @Autowired
    private SolicitudItemManager solicitudItemManager;

    @Autowired
    private UsuarioManager userManager;

    @Autowired
    private CausaExternaManager causaExternaManager;

    @Autowired
    private FinalidadManager finalidadManager;

    @Autowired
    private TipoCatastroficoManager tipoCatastroficoManager;

    @Autowired
    private EntidadRecobroManager entidadRecobroManager;

    @Autowired
    private CausalAnulacionManager causalAnulacionManager;

    @Autowired
    private CausalDevolucionManager causalDevolucionManager;

    @Autowired
    private CriterioNegacionManager criterioNegacionManager;

    @Autowired
    private ObjetivoProcedimientoManager objetivoManager;

    @Autowired
    private LateralidadManager lateralidadManager;

    @Autowired
    private ValidatorServiceManager validatorServiceManager;
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
    private SolicitudItemDao sd;

    @Autowired
    private TipoPPMManager tipoPPMManager;

    @Autowired
    private ViaAdministracionManager viaManager;

    @Autowired
    private RegionalManager regionalManager;

    @RequestMapping(value = "/prestador/cargarAutorizacionesRedireccionamiento", method = RequestMethod.GET)
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

            filter.setEstadoSolicitud(EstadoAutorizacion.AUTORIZADO);

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
            if (SystemConstants.BANDEJA_REDIRECCION.equals(bandeja)) {
                if (SystemConstants.ROLE_LINEA_FRENTE.equals(role.getRole())) {
                    roles.add(role.getId());
                }
            }
        }

        return roles;
    }

    @RequestMapping(value = "/prestador/gestionarItemAutorizadoRedireccionamiento/{itemId}", method = RequestMethod.GET)
    protected String getDetalleAutorizacion(ModelMap map, @PathVariable("itemId") Long id) {
        String salida = null;
        BandejasVO vo = null;
        String tipoBandeja = null;
        Integer tipoAuditor = null;
        try {
            AutorizacionDto dto = obtenerRolEstado(id);
            RoleDTO roleDTO = dto.getTarget();
            DescriptivoDto estado = dto.getEstadoAutorizacion();
            if (roleDTO == null || (roleDTO != null && roleDTO.getId() == null)) {
                map.put("idSolicitudItem", id);
                vo = im.getInfoItem(id, VOUtils.BANDEJA_IPS_PRESTADOR);
                if (EstadoAutorizacion.AUTORIZADO.equals(vo.getBandejaIpsPrestadorVO().getEstado())) {
                    vo.getBandejaIpsPrestadorVO().setAutorizada(true);
                }
                cargarIps(vo, map);
                salida = "prestador/common/gestionarItemIpsMedico";
            } else {
                if (estado.getId().equals(EstadoAutorizacion.PENDIENTE_CONTACT_SERVICE)) {
                    // Contact service
                    tipoBandeja = SystemConstants.BANDEJA_NACIONAL;
                    tipoAuditor = RedireccionamientoUtils.BANDEJA_CS;
                    salida = "auditor/redireccion/redireccionItemCS";
                } else if (estado.getId().equals(EstadoAutorizacion.PENDIENTE_ALTO_COSTO)) {
                    // Auditor Alto costo
                    tipoBandeja = SystemConstants.BANDEJA_NACIONAL;
                    tipoAuditor = RedireccionamientoUtils.BANDEJA_AC;
                    salida = "auditor/redireccion/redireccionItemAC";
                }
                vo = im.getInfoItem(id, tipoAuditor, tipoBandeja);
                cargarAuditores(vo, map, id, tipoBandeja, tipoAuditor);

            }
            return salida;
        }

        catch (Throwable e) {
            LOGGER.error("Error obteniendo el item de la autorizacion", e);
            throw e;
        }
    }
    

    @RequestMapping(value = "/prestador/consumirSolicitudRedireccionamiento", method = RequestMethod.POST)
    protected @ResponseBody
    ValidatedResponse<List<AutorizacionDto>> consumirSolicitud(@ModelAttribute FormularioConsumirForm form, ModelMap map) {

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

    @RequestMapping(value = "/prestador/funcionalidadRedireccionamiento/{itemId}", method = RequestMethod.POST)
    @ResponseBody
    public ValidatedPageResponse<Boolean> funcionalidadRedireccionamiento(Long idSedeIps, @PathVariable("itemId") Long itemId, ModelMap map) {
        try {
            ValidatedPageResponse<Boolean> response = new ValidatedPageResponse<Boolean>();
            GestionItemRedir_AnulaDto dto = new GestionItemRedir_AnulaDto();
            dto.setIdSedeIps(idSedeIps);
            dto.setIdSolicitudItem(itemId);
            dto.setEsGrupo(false);
            dto.setEsParaAuditor(false);
            AutorizacionDto autorizacionDto = obtenerRolEstado(itemId);
            RoleDTO roleDTO = autorizacionDto.getTarget();
            if (roleDTO != null)
                dto.setRoleId(roleDTO.getId());
            RespuestaDto respuesta = validatorServiceManager.redireccionarItem(dto);
            if (respuesta.getCodigoRespuesta() == 0) {
                response.setContent(true);
            } else {
                LOGGER.error("Error procesando la redirección problemas con el servicio de la regla RNRedireccionamientoIPS"
                        + respuesta.getMensajeRespuesta());
                response.addGeneralError(respuesta.getMensajeRespuesta());
            }
            return response;
        } catch (Throwable e) {
            LOGGER.error("Error con funcionalidadRedireccionamiento" + e);
            throw e;
        }

    }

    @RequestMapping(value = "/prestador/funcionalidadRedireccionamientoGrupos/{idAutorizacion}", method = RequestMethod.POST)
    @ResponseBody
    public String funcionalidadRedireccionamientoGrupos(Long idSedeIps, @PathVariable("idAutorizacion") Long idAutorizacion, ModelMap map) {
        try {
            GestionItemRedir_AnulaDto dto = new GestionItemRedir_AnulaDto();
            dto.setIdSedeIps(idSedeIps);
            dto.setIdAutorizacion(idAutorizacion);
            dto.setEsGrupo(true);
            dto.setEsParaAuditor(false);
            RespuestaDto respuesta = validatorServiceManager.redireccionarItem(dto);
            LOGGER.warn(respuesta.getMensajeRespuesta());
            return String.valueOf(idSedeIps) + idAutorizacion;
        } catch (Throwable e) {
            LOGGER.error("Error con funcionalidadRedireccionamiento" + e);
            throw e;
        }

    }

    @RequestMapping(value = "/prestador/funcionalidadEscalamientoRed/{itemId}", method = RequestMethod.POST)
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

            RespuestaDto respuesta = validatorServiceManager.redireccionarItem(dto);
            LOGGER.warn(respuesta.getMensajeRespuesta());
            return String.valueOf(idJustificacion) + itemId;
        } catch (Throwable e) {
            LOGGER.error("Error con funcionalidadEscalamiento" + e);
            throw e;
        }

    }
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/prestador/funcionalidadEscalamientoGrupo/", method = RequestMethod.POST)
    @ResponseBody
    public void funcionalidadEscalamientoGrupo(String idJustificacion, ModelMap map) {
        try {
        	List<BandejaSubItemProjVO> items= (List<BandejaSubItemProjVO>) map.get("itemsAutorizacion");
        	for (BandejaSubItemProjVO bandejaSubItemProjVO : items) {
        		redireccionamientoPorItem(idJustificacion, bandejaSubItemProjVO.getNumeroItem(),map);
    		}
        } catch (Throwable e) {
            LOGGER.error("Error con funcionalidadEscalamientoPorgrupo" + e);
            throw e;
        }

    }
    
    private RespuestaDto redireccionamientoPorItem(String idJustificacion,long itemId, ModelMap map){
    	GestionItemRedir_AnulaDto dto = new GestionItemRedir_AnulaDto();
        dto.setJustificacion(idJustificacion);
        dto.setIdSolicitudItem(itemId);
        //No es grupo por que se va a tratar cada item en un ciclo
        dto.setEsGrupo(false);
        dto.setEsParaAuditor(true);
        EscalamientoDto escalaDto = new EscalamientoDto();
        escalaDto.setItemId(itemId);
        escalaDto.setNombreBandeja(map.get("nombreBandeja").toString());
        RespuestaCompuesta<RoleDTO> rtaCmp = validatorServiceManager.getRoleEscalamiento(escalaDto);
        dto.setRoleId(ConverterUtil.getDataFromTransactionData((Object)rtaCmp.getTransactionData(), "id", new Integer(0)));
        RespuestaDto respuesta = validatorServiceManager.redireccionarItem(dto);
        return respuesta;
    }

    @SuppressWarnings("unchecked")
    private AutorizacionDto obtenerRolEstado(Long subitem) {
        try {
            AutorizacionDto autorizacionDto = new AutorizacionDto();
            RespuestaCompuesta<AutorizacionDto> rta = validatorServiceManager.calcularNivelAutorizacion(subitem);
            Object datosSerializados = rta.getTransactionData();
            LinkedHashMap<String, Object> mapa = (LinkedHashMap<String, Object>) datosSerializados;
            RoleDTO roleDTO = RedireccionamientoUtils.obtenerRol(mapa);
            Integer estado = RedireccionamientoUtils.obtenerEstado(mapa);
            DescriptivoDto estadoAu = new DescriptivoDto();
            autorizacionDto.setTarget(roleDTO);
            estadoAu.setId(estado);
            autorizacionDto.setEstadoAutorizacion(estadoAu);
            return autorizacionDto;
        } catch (Exception e) {
            LOGGER.error("Error calculando el nivel de autorizacion", e);
            throw e;
        }
    }

    private void cargarIps(BandejasVO vo, ModelMap map) {
        cargarElementosDescriptivo("vias", map, viaManager.getAll());
        map.put("infoGeneral", vo.getInfoGeneralVO());
        map.put("bandejaIps", vo.getBandejaIpsPrestadorVO());
        map.put("redirec", true);
        map.put("anul", false);
        map.put("ipsTitulo", "Redireccionar IPS");
        map.put("importar", "true");
        map.put("regionales", ConverterUtil.tranformObjects(regionalManager.getAll(), RegionalVO.class));
        map.put("direccionamiento", vo.getDireccionamientoVO());
        cargarElementosDescriptivo("estadosAutorizacion", map, estadoManager.getAll());

    }

    private void cargarAuditores(BandejasVO vo, ModelMap map, Long id, String tipoBandeja, int tipoAuditor) {
        cargarElementosDescriptivo("causasExternas", map, causaExternaManager.getAll());
        cargarElementosDescriptivo("finalidades", map, finalidadManager.getAll());
        cargarElementosDescriptivo("tiposCatastroficos", map, tipoCatastroficoManager.getAll());
        cargarElementosDescriptivo("entidadesRecobro", map, entidadRecobroManager.getAll());
        cargarElementosTipoIdentificacion("tiposDeDocumento", map, tipoIdentAfiliadoManager.getAll());
        cargarElementosDescriptivo("causalesAnulacion", map, causalAnulacionManager.getAll());
        cargarElementosDescriptivo("causalesDevolucion", map, causalDevolucionManager.getAll());
        cargarElementosDescriptivo("criteriosNegacion", map, criterioNegacionManager.getAll());
        cargarElementosDescriptivo("estadosAutorizacion", map, estadoManager.getAll());
        cargarElementosDescriptivo("tiposSolicitud", map, tipoPPMManager.getAll());
        map.put("regionales", ConverterUtil.tranformObjects(regionalManager.getAll(), RegionalVO.class));
        map.put("eps", VOUtils.toEpsVO(epsManager.findAll()));

        // Para el componente de documentos adjuntos debe subirse un mapa
        Map<String, List<DocumentoSoporteVO>> documentos = new HashMap<>();
        documentos.put("docAdjuntos", vo.getAdjuntos());
        documentos.put("docAdjuntosItem", vo.getAdjuntosItem());
        map.put("docAdjuntos", documentos);
        map.put("idSolicitudItem", id);
        vo.getBandejasParam().setTipoBandeja(tipoBandeja);
        vo.getBandejasParam().setEditable(Boolean.FALSE);
        vo.getBandejasParam().setEditableRespuesta(Boolean.FALSE);
        vo.getBandejasParam().setEditableRedireccion(Boolean.TRUE);
        vo.getBandejasParam().setCaseDevoluciones(5);
        vo.getBandejasParam().setMostrarAcciones(new boolean[] { false, false, false, false, false });

        if (VOUtils.BANDEJA_AC == tipoAuditor) {
            map.put("bandejaAC", vo.getBandejaAltoCostoVO());
        } else if (VOUtils.BANDEJA_CS == tipoAuditor) {
            map.put("bandejaCS", vo.getBandejaContactServiceVO());
        }
        map.put("diagnosticoBandeja", vo.getDiagnosticoBandeja());
        map.put("direccionamiento", vo.getDireccionamientoVO());
        map.put("bandejasParam", vo.getBandejasParam());
        map.put("infoSolicitud", vo.getInfoSolicitudBandejaVO());
        map.put("infoGeneral", vo.getInfoGeneralVO());
        map.put("infoDevoluciones", vo.getInfoDevoluciones());
        map.put("redireccionAutorizAuditor", Boolean.TRUE);
        map.put("redireccionAutorizAuditorLdf", Boolean.TRUE);
    }
}
