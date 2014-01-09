package com.conexia.saludcoop.web.controller.bandejas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
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
import com.conexia.saludcoop.common.util.ConverterUtil;
import com.conexia.saludcoop.common.util.SystemConstants;
import com.conexia.saludcoop.security.SaludCoopUserDetails;
import com.conexia.saludcoop.util.DateUtilities;
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
import com.conexia.saludcoop.web.manager.RegionalManager;
import com.conexia.saludcoop.web.manager.SolicitudItemManager;
import com.conexia.saludcoop.web.manager.TipoCatastroficoManager;
import com.conexia.saludcoop.web.manager.TipoDocumentoSoporteManager;
import com.conexia.saludcoop.web.manager.TipoIdentificacionManager;
import com.conexia.saludcoop.web.manager.TipoPPMManager;
import com.conexia.saludcoop.web.manager.UsuarioManager;
import com.conexia.saludcoop.web.manager.ValidatorServiceManager;
import com.conexia.saludcoop.web.vo.BandejasVO;
import com.conexia.saludcoop.web.vo.DescriptivoVO;
import com.conexia.saludcoop.web.vo.DocumentoSoporteVO;
import com.conexia.saludcoop.web.vo.RegionalVO;
import com.conexia.saludcoop.web.vo.VOUtils;

/**
 * <b>Bandeja Contact Service</b><br />
 * Clase encargada de controlar las peticiones desde la bandeja de contact service
 * 
 * @author <a href="mailto:prodas@conexia.com">Pablo Rodas</a>
 * @date 07/10/2013
 * 
 */
@Controller
@SessionAttributes({ "archivos", "idSolicitudItem", "msgRespuesta" })
@RequestMapping(value = "/contactService/")
public class BandejaContactServiceController extends BaseValidatingController {

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

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "gestionarItem/{itemId}")
    protected String gestionarItem(@PathVariable("itemId") Long id, ModelMap map) {
        try {
            // En caso que existan archivos previamente en la sesión se eliminan
            HashMap<String, List<DocumentoSoporteDto>> archivos = (HashMap<String, List<DocumentoSoporteDto>>) map.get("archivos");
            if (archivos != null && archivos.containsKey("archivosCS")) {
                archivos.put("archivosCS", new ArrayList<DocumentoSoporteDto>());
            }

            String tipoBandeja = getTipoBandeja();
            BandejasVO vo = solicitudItemManager.getInfoItem(id, VOUtils.BANDEJA_CS, tipoBandeja);

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

            BandejasController.validarCaseDevolucion(vo.getBandejasParam(), SystemConstants.CONTACT_SERVICE);
            BandejasController.validarMostrarOpciones(vo.getBandejasParam(), SystemConstants.CONTACT_SERVICE);

            map.put("diagnosticoBandeja", vo.getDiagnosticoBandeja());
            map.put("bandejaCS", vo.getBandejaContactServiceVO());
            map.put("direccionamiento", vo.getDireccionamientoVO());
            map.put("bandejasParam", vo.getBandejasParam());
            map.put("infoSolicitud", vo.getInfoSolicitudBandejaVO());
            map.put("infoGeneral", vo.getInfoGeneralVO());
            map.put("conceptoCS", vo.getConceptoCSVO());
            map.put("infoDevoluciones", vo.getInfoDevoluciones());

            return "auditor/gestionarItemCS";
        } catch (Exception e) {
            LOGGER.error("Error al consultar el detalle del item", e);
            return "auditor/bandejaContactService";
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

            if (archivos != null && archivos.containsKey("archivosCS")) {
                dto.setDocumentosSoporte(new HashSet<DocumentoSoporteDto>());
                dto.getDocumentosSoporte().addAll(archivos.get("archivosCS"));
            }

            RespuestaDto respuesta = validatorServiceManager.autorizarSolicitudCS(dto);
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
            if (SystemConstants.ROLE_CONTACT_SERVICE_NAC.equals(role.getRole())) {
                return SystemConstants.BANDEJA_NACIONAL;
            } else if (SystemConstants.ROLE_CONTACT_SERVICE_REG.equals(role.getRole())) {
                return SystemConstants.BANDEJA_REGIONAL;
            }
        }
        return SystemConstants.BANDEJA_NACIONAL;
    }

    @RequestMapping(value = "fechasEntrega")
    @ResponseBody
    public String fechasEntrega(BandejasForm form) {
        List<DescriptivoVO> descriptivoVOs = new ArrayList<DescriptivoVO>();
        Date fechaActual = new Date();
        Integer numeroEntregas = form.getNumeroEntregas();
        Integer diasPeriodo = form.getDiasPeriodo();
        String formato = "MM/dd/yy";
        Date fechaRecorrido = fechaActual;
        for (int i = 0; i < numeroEntregas; i++) {
            DescriptivoVO descriptivoVO = new DescriptivoVO();
            String fechaFormateada = DateUtilities.parseDateTime(fechaRecorrido, formato);
            descriptivoVO.setDescripcion(fechaFormateada);
            descriptivoVOs.add(descriptivoVO);
            fechaRecorrido = addDaysToDate(fechaRecorrido, diasPeriodo);
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

    private Date addDaysToDate(Date date, int days) {
        return addMinutesToDate(date, 60 * 24 * days);
    }

    private Date addMinutesToDate(Date date, int minutes) {
        Calendar calendarDate = Calendar.getInstance();
        calendarDate.setTime(date);
        calendarDate.add(Calendar.MINUTE, minutes);
        return calendarDate.getTime();
    }

}
