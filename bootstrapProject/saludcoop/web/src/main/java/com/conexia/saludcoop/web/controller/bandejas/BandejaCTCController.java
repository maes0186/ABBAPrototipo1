package com.conexia.saludcoop.web.controller.bandejas;

import static com.conexia.saludcoop.common.util.SystemConstants.ROLE_AUDITOR_CTC_NAC;
import static com.conexia.saludcoop.common.util.SystemConstants.ROLE_AUDITOR_CTC_REG;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.conexia.repository.access.StoredFile;
import com.conexia.repository.exception.FileRepositoryException;
import com.conexia.saludcoop.common.dto.BandejasDto;
import com.conexia.saludcoop.common.dto.FormatoNegacionServiciosDto;
import com.conexia.saludcoop.common.dto.RespuestaDto;
import com.conexia.saludcoop.common.dto.transaccional.DocumentoSoporteDto;
import com.conexia.saludcoop.common.entity.security.Role;
import com.conexia.saludcoop.common.entity.security.User;
import com.conexia.saludcoop.common.enumerator.PeriodosVigencia;
import com.conexia.saludcoop.common.io.repository.FileRepositoryProvider;
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
import com.conexia.saludcoop.web.manager.DepartamentoManager;
import com.conexia.saludcoop.web.manager.EntidadRecobroManager;
import com.conexia.saludcoop.web.manager.EpsManager;
import com.conexia.saludcoop.web.manager.EstadoAfiliacionManager;
import com.conexia.saludcoop.web.manager.EstadoAutorizacionManager;
import com.conexia.saludcoop.web.manager.FinalidadManager;
import com.conexia.saludcoop.web.manager.ObjetivoProcedimientoManager;
import com.conexia.saludcoop.web.manager.RegionalManager;
import com.conexia.saludcoop.web.manager.SolicitudItemManager;
import com.conexia.saludcoop.web.manager.SolicitudManager;
import com.conexia.saludcoop.web.manager.TipoCatastroficoManager;
import com.conexia.saludcoop.web.manager.TipoDocumentoSoporteManager;
import com.conexia.saludcoop.web.manager.TipoIdentificacionManager;
import com.conexia.saludcoop.web.manager.TipoPPMManager;
import com.conexia.saludcoop.web.manager.UsuarioManager;
import com.conexia.saludcoop.web.manager.ValidatorServiceManager;
import com.conexia.saludcoop.web.pdf.FormatoNegacionServicioGenerator;
import com.conexia.saludcoop.web.vo.BandejasVO;
import com.conexia.saludcoop.web.vo.DocumentoSoporteVO;
import com.conexia.saludcoop.web.vo.InfoBandejaMedicamentoVO;
import com.conexia.saludcoop.web.vo.InfoBandejaProcedimientoVO;
import com.conexia.saludcoop.web.vo.RegionalVO;
import com.conexia.saludcoop.web.vo.SolicitudHistorialVO;
import com.conexia.saludcoop.web.vo.VOUtils;

/**
 * 
 * @author mortega
 * 
 */
@Controller
@SessionAttributes({ "archivos", "idSolicitudItem", "nombreBandeja", "msgRespuesta" })
@RequestMapping(value = "/auditorCTC/")
public class BandejaCTCController extends BaseValidatingController {

    private static Logger LOGGER = LoggerFactory.getLogger(BandejaCTCController.class);

    @Autowired
    private TipoIdentificacionManager tipoIdentAfiliadoManager;

    @Autowired
    private EstadoAfiliacionManager estadoAfiliacionManager;

    @Autowired
    private EpsManager epsManager;

    @Autowired
    private DepartamentoManager departamentoManager;

    @Autowired
    private RegionalManager regionalManager;

    @Autowired
    private EstadoAutorizacionManager estadoManager;

    @Autowired
    private TipoPPMManager tipoPPMManager;

    @Autowired
    private UsuarioManager userManager;

    @Autowired
    private SolicitudItemManager solicitudItemManager;

    @Autowired
    private ValidatorServiceManager validatorServiceManager;

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
    private SolicitudManager solicitudManager;

    @Autowired
    private ObjetivoProcedimientoManager objetivoManager;    

    @Autowired
    private TipoDocumentoSoporteManager tipoDocSoporteManager;

    /**
     * Proveedor de archivos del repositorio.
     */
    @Autowired
    private FileRepositoryProvider fileRepositoryProvider;

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "gestionarItem/{itemId}")
    protected String gestionarItem(@PathVariable("itemId") Long id, ModelMap map) {
        try {

            // En caso que existan archivos previamente en la sesión se eliminan
            HashMap<String, List<DocumentoSoporteDto>> archivos = (HashMap<String, List<DocumentoSoporteDto>>) map.get("archivos");
            if (archivos != null && archivos.containsKey("archivosCTC")) {
                archivos.put("archivosCTC", new ArrayList<DocumentoSoporteDto>());
            }

            String bandeja = (String) map.get("nombreBandeja");

            String tipoBandeja = getTipoBandeja(bandeja);
            BandejasVO vo = solicitudItemManager.getInfoItem(id, VOUtils.BANDEJA_CTC, tipoBandeja);

            // Metodo encaragdo de obtener elementos que se necesitan calcular
            obtenerInformacionCalculada(vo);
            map.put("infoSolicitud", vo.getInfoSolicitudBandejaVO());
            map.put("diagnosticoBandeja", vo.getDiagnosticoBandeja());
            map.put("conceptoCTC", vo.getConceptoCTCVO());
            map.put("infoDevoluciones", vo.getInfoDevoluciones());
            map.put("direccionamiento", vo.getDireccionamientoVO());
            cargarElementosDescriptivo("causasExternas", map, causaExternaManager.getAll());
            cargarElementosDescriptivo("finalidades", map, finalidadManager.getAll());
            cargarElementosDescriptivo("tiposCatastroficos", map, tipoCatastroficoManager.getAll());
            cargarElementosDescriptivo("entidadesRecobro", map, entidadRecobroManager.getAll());
            cargarElementosTipoIdentificacion("tiposDeDocumento", map, tipoIdentAfiliadoManager.getAll());
            cargarElementosDescriptivo("objetivos", map, objetivoManager.getAll());
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

            // Se verifica el tipo de bandeja
            vo.getBandejasParam().setTipoBandeja(tipoBandeja);
            BandejasController.validarCaseDevolucion(vo.getBandejasParam(),
                    bandeja.equals(SystemConstants.BANDEJA_ESPECIALIZADA) ? SystemConstants.AUDITOR_ESPECIALIZADO
                            : SystemConstants.AUDITOR_CTC);
            BandejasController.validarMostrarOpciones(vo.getBandejasParam(),
                    bandeja.equals(SystemConstants.BANDEJA_ESPECIALIZADA) ? SystemConstants.AUDITOR_ESPECIALIZADO
                            : SystemConstants.AUDITOR_CTC);

            map.put("infoGeneral", vo.getInfoGeneralVO());
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
            map.put("bandejasParam", vo.getBandejasParam());

            return "auditor/gestionarItemCTC";
        } catch (Exception e) {
            LOGGER.error("Error al consultar el detalle del item", e);
            return "auditorCTC/bandejaCTC";
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
            RespuestaDto respuesta;
            // Se construye el objeto que será enviado al validador para procesar la solicitud
            BandejasDto dto = new BandejasDto();
            BeanUtils.copyProperties(form, dto);
            dto.setTipoBandeja(getTipoBandeja(""));

            if (archivos != null && archivos.containsKey("archivosCTC")) {
                dto.setDocumentosSoporte(new HashSet<DocumentoSoporteDto>());
                dto.getDocumentosSoporte().addAll(archivos.get("archivosCTC"));
            }

            String bandeja = (String) map.get("nombreBandeja");
            if (bandeja.equals(SystemConstants.BANDEJA_ESPECIALIZADA)) {
                respuesta = validatorServiceManager.autorizacionBandejaEspecializada(dto);
            } else {
                respuesta = validatorServiceManager.autorizarSolicitudCTC(dto);
            }

            // Se verifica la respuesta retornada por el método del validador
            if (respuesta != null) {
                response.setContent(respuesta.getCodigoRespuesta() == 0 ? Boolean.TRUE : Boolean.FALSE);
                response.addGeneralError(respuesta.getMensajeRespuesta());

                if (respuesta.getCodigoRespuesta() == 0) {
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
    private String getTipoBandeja(String bandeja) {
        // Se obtiene el usuario en sesión
        SaludCoopUserDetails userDetails = (SaludCoopUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userManager.loadUserByUsername(userDetails.getUsername());

        // Se obtiene el tipo de bandeja de acuerdo al rol del usuario
        for (Role role : user.getRoles()) {
            if (ROLE_AUDITOR_CTC_NAC.equals(role.getRole()) || bandeja.equals(SystemConstants.BANDEJA_ESPECIALIZADA)) {
                return SystemConstants.BANDEJA_NACIONAL;
            } else if (ROLE_AUDITOR_CTC_REG.equals(role.getRole())) {
                return SystemConstants.BANDEJA_REGIONAL;
            }
        }
        return SystemConstants.BANDEJA_NACIONAL;
    }

    /**
     * Carga el historial de las solicitudes de los ultimos 30 días
     * 
     * @param vo
     */
    private void obtenerInformacionCalculada(BandejasVO vo) {
        InfoBandejaProcedimientoVO bandejaProcVO = vo.getBandejaProcedimientoVO();
        InfoBandejaMedicamentoVO bandejaMediVO = vo.getBandejaMedicamentoVO();
        List<SolicitudHistorialVO> listHistorial = solicitudManager.getHistorialSolicitudes(
                vo.getInfoGeneralVO().getTipoIdentificacionUsuarioId(), vo.getInfoGeneralVO().getIdentificacionUsuario(),
                (bandejaProcVO != null && bandejaProcVO.getProcedimientoSolicitado() != null && bandejaProcVO.getProcedimientoSolicitado().getCodigo() != null) ? bandejaProcVO.getProcedimientoSolicitado().getCodigo() : null, 
                (bandejaMediVO != null && bandejaMediVO.getMedicamentoSolicitado() != null && bandejaMediVO.getMedicamentoSolicitado().getMedicamento() != null && bandejaMediVO.getMedicamentoSolicitado().getMedicamento().getCodigo() != null) ? bandejaMediVO.getMedicamentoSolicitado().getMedicamento().getCodigo() : null, 
                        PeriodosVigencia.PERIODO_30.getPeriodo());

        if (listHistorial != null && listHistorial.size() > 0) {
            SolicitudHistorialVO historialVO = listHistorial.get(0);
            vo.getInfoGeneralVO().setSolicitudEntregas(historialVO.getUnidadesAprobadas() > 0 ? historialVO.getNumeroSolicitud() : null);
            vo.getInfoGeneralVO().setFechaLiberacion(historialVO.getFecha());
            vo.getInfoGeneralVO().setNumeroAutorizaciones(listHistorial.size());
        }
    }
    

    @RequestMapping(value = "/formatoNegacionServicios/{nroItem}", method = RequestMethod.GET)
    protected void formatoNegacionServicios(@PathVariable("nroItem") Long nroItem,  ModelMap model, HttpServletRequest request, HttpServletResponse response) throws MalformedURLException {
  
        FormatoNegacionServiciosDto formato = solicitudItemManager.getFormatoNegacionServicios(nroItem);
        URL url = null;
        
        if(formato.getEps() != null){
            if (formato.getEps().equals("Cruz Blanca EPS")){
                url = new URL("http://localhost:"+request.getServerPort()+"/"+request.getContextPath()+"/resources/images/CruzBlanca-logo.png");
            }else if(formato.getEps().equals("Cafesalud EPS Contributivo") || formato.getEps().equals("Cafesalud EPS Subsidiado")){
                url = new URL("http://localhost:"+request.getServerPort()+"/"+request.getContextPath()+"/resources/images/Cafesalud-logo.png");
            }else {
                url = new URL("http://localhost:"+request.getServerPort()+"/"+request.getContextPath()+"/resources/images/SaludCoopEps-logo.png");
            }
        } else {
            url = new URL("http://localhost:"+request.getServerPort()+"/"+request.getContextPath()+"/resources/images/SaludCoopEps-logo.png");
        }
        
        StoredFile documento = FormatoNegacionServicioGenerator.crearPdf(fileRepositoryProvider, formato, url);
        
        if (null != documento) {
            try {
                byte[] retrieveFile = this.fileRepositoryProvider.getInstance().retrieveFile(documento);
                try {
                    response.setContentType("application/octet-stream");
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + documento.getFilename() + ".pdf\"");
                    response.setHeader("Pragma", "no-cache");
                    response.setHeader("Cache-Control", "no-cache");
                    response.setContentLength(retrieveFile.length);

                    final InputStream is = new ByteArrayInputStream(retrieveFile);
                    IOUtils.copy(is, response.getOutputStream());
                    response.flushBuffer();
                    
                } catch (final IOException e) {
                    LOGGER.error("Error de E/S.", e);
                }
            } catch (FileNotFoundException | FileRepositoryException e1) {
                LOGGER.error("Error al obtener el archivo.", e1);
            }
        }
    }
}
