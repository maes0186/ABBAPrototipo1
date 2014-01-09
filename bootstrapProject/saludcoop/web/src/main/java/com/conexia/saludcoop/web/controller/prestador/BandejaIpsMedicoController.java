package com.conexia.saludcoop.web.controller.prestador;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.conexia.repository.access.StoredFile;
import com.conexia.repository.exception.FileRepositoryException;
import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.dto.RespuestaDto;
import com.conexia.saludcoop.common.dto.transaccional.AutorizacionDto;
import com.conexia.saludcoop.common.dto.transaccional.DocumentoSoporteDto;
import com.conexia.saludcoop.common.dto.transaccional.FormulaMedicamentoDto;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudDto;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudItemDto;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudMedicamentoDto;
import com.conexia.saludcoop.common.io.repository.FileRepositoryProvider;
import com.conexia.saludcoop.common.util.ConverterUtil;
import com.conexia.saludcoop.common.util.SystemConstants;
import com.conexia.saludcoop.util.ValidatedPageResponse;
import com.conexia.saludcoop.util.ValidatedResponse;
import com.conexia.saludcoop.web.BaseValidatingController;
import com.conexia.saludcoop.web.form.DevolucionSolicitudForm;
import com.conexia.saludcoop.web.manager.EpsManager;
import com.conexia.saludcoop.web.manager.EstadoAutorizacionManager;
import com.conexia.saludcoop.web.manager.RegionalManager;
import com.conexia.saludcoop.web.manager.SolicitudItemManager;
import com.conexia.saludcoop.web.manager.SolicitudManager;
import com.conexia.saludcoop.web.manager.TipoDocumentoSoporteManager;
import com.conexia.saludcoop.web.manager.TipoIdentificacionManager;
import com.conexia.saludcoop.web.manager.TipoPPMManager;
import com.conexia.saludcoop.web.manager.UsuarioManager;
import com.conexia.saludcoop.web.manager.ValidatorServiceManager;
import com.conexia.saludcoop.web.manager.ViaAdministracionManager;
import com.conexia.saludcoop.web.vo.BandejasVO;
import com.conexia.saludcoop.web.vo.DocumentoSoporteVO;
import com.conexia.saludcoop.web.vo.FileMeta;
import com.conexia.saludcoop.web.vo.RegionalVO;
import com.conexia.saludcoop.web.vo.VOUtils;

/**
 * @author mcuervo
 * 
 */
@Controller
@RequestMapping(method = RequestMethod.GET)
@SessionAttributes({"afiliado", "archivosComplementarios","msgRespuesta", "permiteConsumo"})
public class BandejaIpsMedicoController extends BaseValidatingController {

	private static Logger LOGGER = LoggerFactory.getLogger(BandejaIpsMedicoController.class);
	
	@Autowired
	private SolicitudManager sm;
	
	@Autowired
    private RegionalManager regionalManager;
	
	@Autowired
    private EstadoAutorizacionManager estadoManager;

	@Autowired
	private TipoDocumentoSoporteManager tipoDocSoporteManager;
	
	@Autowired
	private ValidatorServiceManager validatorServiceManager;

	@Autowired
	private SolicitudItemManager im;
	
	@Autowired
    private EpsManager epsManager;
    
	@Autowired
	private TipoIdentificacionManager tipoIdentAfiliadoManager;
	
    @Autowired
    private TipoPPMManager tipoPPMManager;

    @Autowired
    private ViaAdministracionManager viaManager;
    
    @Autowired
	private UsuarioManager userManager;
    
    /**
	 * Proveedor de archivos del repositorio.
	 */
	@Autowired
	private FileRepositoryProvider fileRepositoryProvider;

	@RequestMapping(value = "/prestador/gestionarItem/{itemId}")
	protected String getDetalleAutorizacion(ModelMap map, @PathVariable("itemId") Long id) {
		try {
	        BandejasVO vo = im.getInfoItem(id, VOUtils.BANDEJA_IPS_PRESTADOR);
	        
	        cargarElementosDescriptivo("vias", map, viaManager.getAll());
	        
	        map.put("infoGeneral", vo.getInfoGeneralVO());
	        map.put("bandejaIps", vo.getBandejaIpsPrestadorVO());
	        map.put("regionales", ConverterUtil.tranformObjects(regionalManager.getAll(), RegionalVO.class));
	        cargarElementosDescriptivo("estadosAutorizacion", map, estadoManager.getAll());
	        map.put("archivosComplementarios", new HashMap<String, String>());
	        Map<String, List<DocumentoSoporteVO>> documentos = new HashMap<>();
            documentos.put("docAdjuntos", vo.getAdjuntos());
            documentos.put("docAdjuntosItem", vo.getAdjuntosItem());
            map.put("docAdjuntos", documentos);
	//        StoredFile file = new StoredFile(vo.getBandejaIpsPrestadorVO().getAdjuntos().get(0).get)
	        return "prestador/common/gestionarItemIpsMedico";
		} catch (final Exception e) {
	        LOGGER.error("Error al buscar detalle del item", e);
	        return "/common/error";
	    }
	}
	
    @RequestMapping(value = "/prestador/buscarArchivo/{nombreArchivoOriginal}/{nombreArchivoServidor}")
	protected @ResponseBody boolean buscarArchivo(@PathVariable("nombreArchivoServidor") String nombreArchivoServidor, @PathVariable("nombreArchivoOriginal") String nombreArchivoOriginal) {
		boolean existe = false;
        try {
        	this.fileRepositoryProvider.getInstance().retrieveFile(
                    new StoredFile(nombreArchivoServidor));
            existe = true;
        } catch (FileNotFoundException | FileRepositoryException e1) {
            e1.printStackTrace();
        }
		return existe;
      
	}
	@RequestMapping(value = "/prestador/download/{nombreArchivoOriginal}/{nombreArchivoServidor}")
	protected void descargarArchivo(HttpServletResponse response, @PathVariable("nombreArchivoServidor") String nombreArchivoServidor, @PathVariable("nombreArchivoOriginal") String nombreArchivoOriginal) {
		
		byte[] retrieveFile;
		try {
			retrieveFile = this.fileRepositoryProvider.getInstance().retrieveFile(
					new StoredFile(nombreArchivoServidor));
			try {
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition", "attachment; filename=\"" + nombreArchivoOriginal + "\"");
				response.setHeader("Pragma", "no-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setContentLength(retrieveFile.length);
				
				final InputStream is = new ByteArrayInputStream(retrieveFile);
				IOUtils.copy(is, response.getOutputStream());
				response.flushBuffer();
			} catch (final IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException | FileRepositoryException e1) {
			e1.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("unchecked")
    @RequestMapping(value = "/prestador/enviarItem", method = RequestMethod.POST)
	protected @ResponseBody ValidatedResponse<String> getEnviarItem(@ModelAttribute DevolucionSolicitudForm form, ModelMap map) {
		ValidatedResponse<String> response = new ValidatedPageResponse<String>();
		
		try {
			HashMap<String, DocumentoSoporteDto> archivos = (HashMap<String, DocumentoSoporteDto>) map.get("archivosComplementarios");
			if (archivos == null) {
				archivos = new HashMap<>();
			}
					
			SolicitudItemDto dto = new SolicitudItemDto();
			dto.setNroItem(Long.valueOf(form.getNroSolicitud()));
	//			dto.sets;
			if (form.getTipoItem().equals(1)){
				FormulaMedicamentoDto med = new FormulaMedicamentoDto();
				med.setDosis(Integer.valueOf(form.getDosisFrecuencia()));
				med.setDuracion(Integer.valueOf(form.getDuracionTratamiento()));
				DescriptivoDto via = new DescriptivoDto();
				via.setId(form.getViaAdministracion());
				med.setViaAdministracion(via);
				med.setPosologia(form.getPosologia());
				SolicitudMedicamentoDto solMed = new SolicitudMedicamentoDto();
				solMed.setFormulaMedicamento(med);
				dto.setSolMedicamento(solMed);				
			}
			
			dto.setSolicitud(new SolicitudDto());
			DocumentoSoporteDto doc = null;
			for (DocumentoSoporteDto archivo : archivos.values()) {
				dto.getSolicitud().getDocumentosSoporte().add(archivo);
			}
	
			dto.setAutorizacion(new AutorizacionDto());
			dto.getAutorizacion().setJustificacionIps(form.getRespuestaIps());
			RespuestaDto rta = validatorServiceManager.devolverSolicitudItem(dto);
			// TODO : enviar al validador
			
			if (rta.getCodigoRespuesta() == 0 ){
				response.setContent("");
				map.put("msgRespuesta", "La solicitud fue procesada con éxito");
			}
			else
				response.getGeneralErrors().add("Error al enviar la solicitud.");
				// TODO : enviar al validador
	
			return response;
		} catch (final Exception e) {
	        LOGGER.error("Error enviando la solicitud", e);
	        response.addGeneralError("Error al enviar la solicitud");
	        return response;
	    }
	}

	@SuppressWarnings("unchecked")
    @RequestMapping(value = "/prestador/upload/docComplementarios", method = RequestMethod.POST)
	protected void uploadNuevos(MultipartHttpServletRequest request, HttpServletResponse response, ModelMap map) {
		
		HashMap<String, DocumentoSoporteDto> archivos = (HashMap<String, DocumentoSoporteDto>) map.get("archivosComplementarios");
		
		LinkedList<FileMeta> files = new LinkedList<FileMeta>();
		FileMeta fileMeta = null;
		
		// 1. build an iterator
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;
		
		// 2. get each file
		while (itr.hasNext()) {
			
			// 2.1 get next MultipartFile
			mpf = request.getFile(itr.next());
			System.out.println(mpf.getOriginalFilename() + " uploaded! " + files.size());
			
			// 2.2 if files > 10 remove the first from the list
			if (files.size() >= 10)
				files.pop();
			
			// 2.3 create new fileMeta
			fileMeta = new FileMeta();
			fileMeta.setFileName(mpf.getOriginalFilename());
			fileMeta.setFileSize(mpf.getSize() / 1024 + " Kb");
			fileMeta.setFileType(mpf.getContentType());
			
			/* Se almacena el archivo en el repositorio */
			try {
				final StoredFile archivo = this.fileRepositoryProvider.getInstance().storeFile(mpf.getBytes());
				DocumentoSoporteDto doc = new DocumentoSoporteDto();
	            doc.setNombreArchivoOriginal(mpf.getOriginalFilename());
	            doc.setTipoDocSoporte(tipoDocSoporteManager.getByCodigo(SystemConstants.DOC_OTROS));
	            doc.setNombreArchivoServidor(archivo.getFilename());

	            archivos.put(archivo.getFilename(), doc);
	            
			} catch (final IOException e) {
				/* TODO Se debe manejar este error para presentarlo en pantalla */
				throw new RuntimeException("Error al obtener contenido a almacenar: " + e.getMessage());
			} catch (final FileRepositoryException e) {
				/* TODO Se debe manejar este error para presentarlo en pantalla */
				throw new RuntimeException("Error al almacenar archivo: " + e.getMessage());
			}
			
			// 2.4 add to files
			files.add(fileMeta);
		}
		// result will be like this
		// [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
		
	}    	
	
}
