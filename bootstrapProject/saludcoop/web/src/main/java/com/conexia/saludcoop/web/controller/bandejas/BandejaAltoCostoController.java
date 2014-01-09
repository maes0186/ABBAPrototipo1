package com.conexia.saludcoop.web.controller.bandejas;

import static com.conexia.saludcoop.common.util.SystemConstants.ROLE_AUDITOR_AC_NAC;
import static com.conexia.saludcoop.common.util.SystemConstants.ROLE_AUDITOR_AC_REG;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.conexia.saludcoop.common.dto.BandejasDto;
import com.conexia.saludcoop.common.dto.RespuestaDto;
import com.conexia.saludcoop.common.dto.transaccional.DocumentoSoporteDto;
import com.conexia.saludcoop.common.entity.security.Role;
import com.conexia.saludcoop.common.entity.security.User;
import com.conexia.saludcoop.common.enumerator.PeriodosVigencia;
import com.conexia.saludcoop.common.util.ConverterUtil;
import com.conexia.saludcoop.common.util.SystemConstants;
import com.conexia.saludcoop.security.SaludCoopUserDetails;
import com.conexia.saludcoop.util.ValidatedResponse;
import com.conexia.saludcoop.web.BaseValidatingController;
import com.conexia.saludcoop.web.form.BandejasForm;
import com.conexia.saludcoop.web.manager.CausaExternaManager;
import com.conexia.saludcoop.web.manager.CausalAnulacionManager;
import com.conexia.saludcoop.web.manager.CausalDevolucionManager;
import com.conexia.saludcoop.web.manager.CriterioNegacionManager;
import com.conexia.saludcoop.web.manager.EntidadRecobroManager;
import com.conexia.saludcoop.web.manager.EpsManager;
import com.conexia.saludcoop.web.manager.EstadoAutorizacionManager;
import com.conexia.saludcoop.web.manager.FinalidadManager;
import com.conexia.saludcoop.web.manager.LateralidadManager;
import com.conexia.saludcoop.web.manager.MedicamentoManager;
import com.conexia.saludcoop.web.manager.RegionalManager;
import com.conexia.saludcoop.web.manager.SolicitudItemManager;
import com.conexia.saludcoop.web.manager.SolicitudManager;
import com.conexia.saludcoop.web.manager.TipoCatastroficoManager;
import com.conexia.saludcoop.web.manager.TipoDocumentoSoporteManager;
import com.conexia.saludcoop.web.manager.TipoIdentificacionManager;
import com.conexia.saludcoop.web.manager.TipoPPMManager;
import com.conexia.saludcoop.web.manager.UsuarioManager;
import com.conexia.saludcoop.web.manager.ValidatorServiceManager;
import com.conexia.saludcoop.web.vo.BandejasVO;
import com.conexia.saludcoop.web.vo.DocumentoSoporteVO;
import com.conexia.saludcoop.web.vo.RegionalVO;
import com.conexia.saludcoop.web.vo.SolicitudHistorialVO;
import com.conexia.saludcoop.web.vo.VOUtils;

/**
 * <b>Bandeja auditor alto costo</b> Clase encargada de controlar las peticiones desde la bandeja para auditores de operaciones de alto
 * costo
 * 
 * @author <a href="mailto:prodas@conexia.com">Pablo Rodas</a>
 * @date 07/10/2013
 * 
 */
@Controller
@SessionAttributes({ "archivos","idSolicitudItem", "msgRespuesta" })
@RequestMapping(value = "/auditorAltoCosto/")
public class BandejaAltoCostoController extends BaseValidatingController {

    private static Logger LOGGER = LoggerFactory.getLogger(BandejaCTCController.class);

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
    private CausaExternaManager causaExternaManager;

    @Autowired
    private FinalidadManager finalidadManager;
    
    @Autowired
    private TipoCatastroficoManager tipoCatastroficoManager;
    
    @Autowired
    private EntidadRecobroManager entidadRecobroManager;

    @Autowired
    private LateralidadManager lateralidadManager;

    @Autowired
    private SolicitudItemManager solicitudItemManager;

    @Autowired
    private UsuarioManager userManager;

    @Autowired
    private CausalAnulacionManager causalAnulacionManager;

    @Autowired
    private CausalDevolucionManager causalDevolucionManager;

    @Autowired
    private CriterioNegacionManager criterioNegacionManager;

    @Autowired
    private ValidatorServiceManager validatorServiceManager;

    @Autowired
    private TipoDocumentoSoporteManager tipoDocSoporteManager;
    
    @Autowired
    private MedicamentoManager medicamentoManager;
    
    @Autowired
    private SolicitudManager solicitudManager;

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "gestionarItem/{itemId}")
    protected String gestionarItem(@PathVariable("itemId") Long id, ModelMap map) {
        try {
            // En caso que existan archivos previamente en la sesión se eliminan
            HashMap<String, List<DocumentoSoporteDto>> archivos = (HashMap<String, List<DocumentoSoporteDto>>) map.get("archivos");
            if(archivos != null && archivos.containsKey("archivosAC")) {
                archivos.put("archivosAC", new ArrayList<DocumentoSoporteDto>());
            }
            
            String tipoBandeja = getTipoBandeja();

            BandejasVO vo = solicitudItemManager.getInfoItem(id, VOUtils.BANDEJA_AC, tipoBandeja);

            // Metodo encaragdo de obtener elementos que se necesitan calcular
            obtenerInformacionCalculada(vo);
            cargarElementosTipoIdentificacion("tiposDeDocumento", map, tipoIdentAfiliadoManager.getAll());
            cargarElementosDescriptivo("causasExternas", map, causaExternaManager.getAll());
            cargarElementosDescriptivo("finalidades", map, finalidadManager.getAll());
            cargarElementosDescriptivo("tiposCatastroficos", map, tipoCatastroficoManager.getAll());
            cargarElementosDescriptivo("lateralidades", map, lateralidadManager.getAll());
            cargarElementosDescriptivo("entidadesRecobro", map, entidadRecobroManager.getAll());
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
            BandejasController.validarCaseDevolucion(vo.getBandejasParam(), SystemConstants.AUDITOR_AC);
            BandejasController.validarMostrarOpciones(vo.getBandejasParam(), SystemConstants.AUDITOR_AC);
            map.put("diagnosticoBandeja", vo.getDiagnosticoBandeja());
            map.put("bandejaAC", vo.getBandejaAltoCostoVO());
            map.put("direccionamiento", vo.getDireccionamientoVO());
            map.put("bandejasParam", vo.getBandejasParam());
            map.put("infoSolicitud", vo.getInfoSolicitudBandejaVO());
            map.put("infoGeneral", vo.getInfoGeneralVO());
            map.put("conceptoAC", vo.getConceptoACVO());
            map.put("infoDevoluciones", vo.getInfoDevoluciones());

            return "auditor/gestionarItemAC";
        } catch (Exception e) {
            LOGGER.error("Error al consultar el detalle del item", e);
            return "auditorAltoCosto/bandejaAltoCosto";
        }
    }

    /**
     * Método encargado de procesar el trámite realizado a la autorización por parte del auditor regional o nacional, para el item
     * solicitado
     * 
     * @param form
     *            Datos editables por el auditor en el formulario de autorización
     * @return Objeto que indica si la solicitud fue procesada correctamente o no
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "procesarTramite")
    @ResponseBody
    public ValidatedResponse<Boolean> procesarTramite(BandejasForm form, ModelMap map) {
        ValidatedResponse<Boolean> response = new ValidatedResponse<>();
        HashMap<String, List<DocumentoSoporteDto>> archivos = (HashMap<String, List<DocumentoSoporteDto>>) map.get("archivos");
        response.setContent(Boolean.FALSE);

        try {
            // Se construye el objeto que será enviado al validador para procesar la solicitud
            BandejasDto dto = new BandejasDto();
            BeanUtils.copyProperties(form, dto);
            dto.setTipoBandeja(getTipoBandeja());

            if (archivos != null && archivos.containsKey("archivosAC")) {
                dto.setDocumentosSoporte(new HashSet<DocumentoSoporteDto>());
                dto.getDocumentosSoporte().addAll(archivos.get("archivosAC"));
            }

            RespuestaDto respuesta = validatorServiceManager.autorizarSolicitudAC(dto);

            // Se verifica la respuesta retornada por el método del validador
            if (respuesta != null) {
                response.setContent(respuesta.getCodigoRespuesta() == 0 ? Boolean.TRUE : Boolean.FALSE);
                response.addGeneralError(respuesta.getMensajeRespuesta());
                
                if(respuesta.getCodigoRespuesta() == 0) {
                    map.put("msgRespuesta", "La solicitud fue procesada con éxito");
                }
            }
            
        } catch (Exception e) {
            LOGGER.error("Error al realizar la autorización de la solicitud CTC", e);
        }
        return response;
    }

    /**
     * Determina el tipo de bandeja (Nacional o regional) a partir de los roles asociados al usuario
     * 
     * @return
     */
    private String getTipoBandeja() {
        // Se obtiene el usuario en sesión
        SaludCoopUserDetails userDetails = (SaludCoopUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userManager.loadUserByUsername(userDetails.getUsername());

        // Se obtiene el tipo de bandeja de acuerdo al rol del usuario
        for (Role role : user.getRoles()) {
            if (ROLE_AUDITOR_AC_NAC.equals(role.getRole())) {
                return SystemConstants.BANDEJA_NACIONAL;
            } else if (ROLE_AUDITOR_AC_REG.equals(role.getRole())) {
                return SystemConstants.BANDEJA_REGIONAL;
            }
        }
        return SystemConstants.BANDEJA_NACIONAL;
    }
    /**
     * Carga el historial de las solicitudes de los ultimos 30 días
     * @param vo
     */
    private void obtenerInformacionCalculada(BandejasVO vo) {
        
        List<SolicitudHistorialVO> listHistorial = solicitudManager.getHistorialSolicitudes(vo.getInfoGeneralVO().getTipoIdentificacionUsuarioId(), 
                vo.getInfoGeneralVO().getIdentificacionUsuario(), 
                (vo.getBandejaAltoCostoVO() != null && 
                 vo.getBandejaAltoCostoVO().getProcedimiento() != null && 
                 vo.getBandejaAltoCostoVO().getProcedimiento().getCodigo() != null) ? vo.getBandejaAltoCostoVO().getProcedimiento().getCodigo() : null,
                 (vo.getBandejaAltoCostoVO() != null &&
                 vo.getBandejaAltoCostoVO().getMedicamento() != null && 
                 vo.getBandejaAltoCostoVO().getMedicamento().getCodigo() != null) ? vo.getBandejaAltoCostoVO().getMedicamento().getCodigo() :null,
                 PeriodosVigencia.PERIODO_30.getPeriodo());
        
        if (listHistorial != null && listHistorial.size() > 0) {
            SolicitudHistorialVO historialVO = listHistorial.get(0);
            vo.getInfoGeneralVO().setSolicitudEntregas(historialVO.getNumeroSolicitud());
            vo.getInfoGeneralVO().setFechaLiberacion(historialVO.getFecha());
            vo.getInfoGeneralVO().setNumeroAutorizaciones(listHistorial.size());
        }
    }

}
