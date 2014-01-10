package com.conexia.saludcoop.web.controller.prestador;

import static com.conexia.saludcoop.common.util.SystemConstants.ROLE_AUDITOR_CTC_NAC;
import static com.conexia.saludcoop.common.util.SystemConstants.ROLE_AUDITOR_CTC_REG;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.conexia.saludcoop.common.dto.RespuestaDto;
import com.conexia.saludcoop.common.dto.transaccional.GestionItemRedir_AnulaDto;
import com.conexia.saludcoop.common.entity.security.Role;
import com.conexia.saludcoop.common.entity.security.User;
import com.conexia.saludcoop.common.util.ConverterUtil;
import com.conexia.saludcoop.common.util.SystemConstants;
import com.conexia.saludcoop.security.SaludCoopUserDetails;
import com.conexia.saludcoop.util.ValidatedResponse;
import com.conexia.saludcoop.web.BaseValidatingController;
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
import com.conexia.saludcoop.web.vo.BandejasVO;
import com.conexia.saludcoop.web.vo.DocumentoSoporteVO;
import com.conexia.saludcoop.web.vo.RegionalVO;
import com.conexia.saludcoop.web.vo.VOUtils;

@Controller
@SessionAttributes({"msgRespuesta" })
@RequestMapping(value = "/redireccionarAutorizacion/", method = RequestMethod.GET)
public class BandejaRedireccionAutorizAuditorController extends BaseValidatingController {
    private static Logger LOGGER = LoggerFactory.getLogger(BandejaRedireccionAutorizAuditorController.class);

    @Autowired
    private TipoIdentificacionManager tipoIdentAfiliadoManager;

    @Autowired
    private EstadoAutorizacionManager estadoManager;

    @Autowired
    private TipoPPMManager tipoPPMManager;

    @Autowired
    private EpsManager epsManager;

    @Autowired
    private RegionalManager regionalManager;

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

    @RequestMapping(value = "gestionarItem/{itemId}")
    protected String gestionarItem(@PathVariable("itemId") Long id, ModelMap map) {
        try {
            int tipoAuditor = getTipoAuditor();

            if(tipoAuditor == -1) {
                return "redirect: /main";
            }
            
            String tipoBandeja = getTipoBandeja();
            BandejasVO vo = solicitudItemManager.getInfoItem(id, tipoAuditor, tipoBandeja);

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
            cargarElementosDescriptivo("objetivos", map, objetivoManager.getAll());
            
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

            map.put("diagnosticoBandeja", vo.getDiagnosticoBandeja());
            map.put("direccionamiento", vo.getDireccionamientoVO());
            map.put("bandejasParam", vo.getBandejasParam());
            map.put("infoSolicitud", vo.getInfoSolicitudBandejaVO());
            map.put("infoGeneral", vo.getInfoGeneralVO());
            map.put("infoDevoluciones", vo.getInfoDevoluciones());
            map.put("redireccionAutorizAuditor", Boolean.TRUE);
            map.put("conceptoCTC", vo.getConceptoCTCVO());
            
            boolean tieneFormCTC = false;
            if (vo.getTipoItem() == SystemConstants.ITEM_MEDICAMENTO) {
                map.put("infoMedicamento", vo.getBandejaMedicamentoVO());
                tieneFormCTC = vo.getBandejaMedicamentoVO().isTieneFormCTC();
            } else if (vo.getTipoItem() == SystemConstants.ITEM_PROCEDIMIENTO) {
                map.put("infoProcedimiento", vo.getBandejaProcedimientoVO());
                tieneFormCTC = vo.getBandejaProcedimientoVO().isTieneFormCTC();
            } else if (vo.getTipoItem() == SystemConstants.ITEM_INSUMO) {
                map.put("infoInsumo", vo.getBandejaInsumoVO());
                tieneFormCTC = vo.getBandejaInsumoVO().isTieneFormCTC();
            }
            
            map.put("tieneFormCTC", tieneFormCTC);

            map.put("tipoItem", vo.getTipoItem());
            return "auditor/redireccion/redireccionItemCTC";
        } catch (Exception e) {
            LOGGER.error("Error al consultar el detalle del item", e);
            return "redireccionarAutorizacion/bandeja/";
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
    @RequestMapping(value = "procesarTramite", method = RequestMethod.POST)
    @ResponseBody
    public ValidatedResponse<Boolean> procesarTramite(Long idSedeIps, Long itemId, ModelMap map) {
        try {
            ValidatedResponse<Boolean> response = new ValidatedResponse<>();
            
            // Se construye el objeto que será enviado al validador para procesar la solicitud
            GestionItemRedir_AnulaDto dto = new GestionItemRedir_AnulaDto();
            dto.setIdSedeIps(idSedeIps);
            dto.setIdSolicitudItem(itemId);
            dto.setEsGrupo(false);
            dto.setEsParaAuditor(false);

            RespuestaDto respuesta = validatorServiceManager.redireccionarItem(dto);
            
            if (respuesta != null) {
                response.setContent(respuesta.getCodigoRespuesta() == 0 ? Boolean.TRUE : Boolean.FALSE);
                response.addGeneralError(respuesta.getMensajeRespuesta());
                
                if(respuesta.getCodigoRespuesta() == 0) {
                    map.put("msgRespuesta", "La solicitud fue procesada con éxito");
                }
            }
            return response;
        } catch (Throwable e) {
            LOGGER.error("Error con funcionalidadRedireccionamiento" + e);
            throw e;
        }
    }

    private int getTipoAuditor() {
        User user = getUsuario();

        for (Role role : user.getRoles()) {
            switch (role.getRole()) {
            case SystemConstants.ROLE_AUDITOR_CTC_REG:
                return SystemConstants.AUDITOR_CTC;
            default:
                return -1;
            }
        }
        
        return -1;
    }
    
    /**
     * Determina el tipo de bandeja (Nacional o regional) a partir de los roles asociados al usuario
     * 
     * @return
     */
    private String getTipoBandeja() {
        User user = getUsuario();
        // Se obtiene el tipo de bandeja de acuerdo al rol del usuario
        for (Role role : user.getRoles()) {
            if (ROLE_AUDITOR_CTC_NAC.equals(role.getRole())) {
                return SystemConstants.BANDEJA_NACIONAL;
            } else if (ROLE_AUDITOR_CTC_REG.equals(role.getRole())) {
                return SystemConstants.BANDEJA_REGIONAL;
            }
        }
        return SystemConstants.BANDEJA_NACIONAL;
    }

    private User getUsuario() {
        // Se obtiene el usuario en sesión
        SaludCoopUserDetails userDetails = (SaludCoopUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userManager.loadUserByUsername(userDetails.getUsername());
    }

}
