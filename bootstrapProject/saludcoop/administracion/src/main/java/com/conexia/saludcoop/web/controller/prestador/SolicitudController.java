package com.conexia.saludcoop.web.controller.prestador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Vector;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.conexia.repository.access.StoredFile;
import com.conexia.repository.exception.FileRepositoryException;
import com.conexia.saludcoop.common.dto.AfiliadoDto;
import com.conexia.saludcoop.common.dto.InsumoTopeDto;
import com.conexia.saludcoop.common.dto.MunicipioDto;
import com.conexia.saludcoop.common.dto.ProfesionalEspecialidadDto;
import com.conexia.saludcoop.common.dto.RespuestaCompuesta;
import com.conexia.saludcoop.common.dto.RespuestaDto;
import com.conexia.saludcoop.common.dto.SedeIpsDto;
import com.conexia.saludcoop.common.dto.UsuarioEntidadDto;
import com.conexia.saludcoop.common.dto.transaccional.AutorizacionDto;
import com.conexia.saludcoop.common.dto.transaccional.DocumentoSoporteDto;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudDto;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudParcialDto;
import com.conexia.saludcoop.common.dto.transaccional.ValidarTopesCantidadDto;
import com.conexia.saludcoop.common.entity.security.Role;
import com.conexia.saludcoop.common.entity.security.User;
import com.conexia.saludcoop.common.enumerator.TipoPeriodo;
import com.conexia.saludcoop.common.exception.ServiceException;
import com.conexia.saludcoop.common.io.repository.FileRepositoryProvider;
import com.conexia.saludcoop.common.util.I18NUtils;
import com.conexia.saludcoop.common.util.SystemConstants;
import com.conexia.saludcoop.security.SaludCoopUserDetails;
import com.conexia.saludcoop.util.ValidatedResponse;
import com.conexia.saludcoop.web.BaseValidatingController;
import com.conexia.saludcoop.web.form.DiagnosticoItemForm;
import com.conexia.saludcoop.web.form.InsumoItemForm;
import com.conexia.saludcoop.web.form.MedicamentoItemForm;
import com.conexia.saludcoop.web.form.ProcedimientoItemForm;
import com.conexia.saludcoop.web.form.SolicitudForm;
import com.conexia.saludcoop.web.form.utils.ParserSolicitudForm;
import com.conexia.saludcoop.web.manager.CausaExternaManager;
import com.conexia.saludcoop.web.manager.DepartamentoManager;
import com.conexia.saludcoop.web.manager.EspecialidadManager;
import com.conexia.saludcoop.web.manager.FinalidadManager;
import com.conexia.saludcoop.web.manager.IAfiliadoManager;
import com.conexia.saludcoop.web.manager.InsumoTopeManager;
import com.conexia.saludcoop.web.manager.LateralidadManager;
import com.conexia.saludcoop.web.manager.MedicamentoManager;
import com.conexia.saludcoop.web.manager.ObjetivoProcedimientoManager;
import com.conexia.saludcoop.web.manager.OrigenRepeticionManager;
import com.conexia.saludcoop.web.manager.ProfesionalEspecialidadManager;
import com.conexia.saludcoop.web.manager.RespuestaClinicaObservadaManager;
import com.conexia.saludcoop.web.manager.SedeIpsManager;
import com.conexia.saludcoop.web.manager.SolicitudParcialManager;
import com.conexia.saludcoop.web.manager.TipoCatastroficoManager;
import com.conexia.saludcoop.web.manager.TipoDocumentoSoporteManager;
import com.conexia.saludcoop.web.manager.TipoIdentificacionManager;
import com.conexia.saludcoop.web.manager.TipoPrestacionManager;
import com.conexia.saludcoop.web.manager.UsuarioEntidadManager;
import com.conexia.saludcoop.web.manager.UsuarioManager;
import com.conexia.saludcoop.web.manager.ValidatorServiceManager;
import com.conexia.saludcoop.web.manager.ViaAdministracionManager;
import com.conexia.saludcoop.web.manager.exceptions.NoSePuedeElilminarSPException;
import com.conexia.saludcoop.web.vo.AfiliadoVO;
import com.conexia.saludcoop.web.vo.FileMeta;
import com.conexia.saludcoop.web.vo.ProfesionalVO;
import com.conexia.saludcoop.web.vo.SedeIpsVO;
import com.conexia.saludcoop.web.vo.utils.ParserVO;

@Controller
@SessionAttributes({ "afiliado", "archivos", "archivosItem", "resumen", "sedeIps", "profesionalEspecialidad", "json", "nroSolicitud", "autorizaciones", "aplicaTutela", "esLDF" })
public class SolicitudController extends BaseValidatingController {

	private static Logger LOGGER = LoggerFactory.getLogger(SolicitudController.class);

	public static final String SOLICITUD = "solicitud";

	@Autowired
	private IAfiliadoManager aManager;
	@Autowired
	private EspecialidadManager eManager;
	@Autowired
	private LateralidadManager lateralidadManager;
	@Autowired
	private ViaAdministracionManager viaAdministracionManager;
	@Autowired
	private CausaExternaManager causaExternaManager;
	@Autowired
	private FinalidadManager finalidadManager;
	@Autowired
	private TipoCatastroficoManager tipoCatastroficoManager;
	@Autowired
	private RespuestaClinicaObservadaManager respClinicaObservadaManager;
	@Autowired
	private ObjetivoProcedimientoManager objetivoManager;
	@Autowired
	private TipoPrestacionManager tipoPrestacionManager;
	@Autowired
	private OrigenRepeticionManager origenRepeticionManager;
	@Autowired
	private TipoDocumentoSoporteManager tipoDocSoporteManager;
	@Autowired
	private ParserSolicitudForm parserForm;
	@Autowired
	private ProfesionalEspecialidadManager profesionalManager;
	@Autowired
	private ParserVO parserVO;
	@Autowired
	private ValidatorServiceManager validatorServiceManager;
	@Autowired
	private TipoIdentificacionManager tipoIdentificacionManager;
	@Autowired
	private EspecialidadManager especialidadManager;
	@Autowired
	private UsuarioEntidadManager usuarioEntidadManager;
	@Autowired
	private UsuarioManager userManager;
	@Autowired
	private SedeIpsManager sedeManager;
	@Autowired
	private SolicitudParcialManager solParManager;
    @Autowired
    private DepartamentoManager departamentoManager;
    @Autowired
    private InsumoTopeManager insumoTopeManager;
	/**
	 * Proveedor de archivos del repositorio.
	 */
	@Autowired
	private FileRepositoryProvider fileRepositoryProvider;
	@Autowired
	private ParserVO pvo;

	@Autowired
	private MedicamentoManager medicamentoManager;

	@RequestMapping(value = "/prestador/solicitud", method = RequestMethod.GET)
	protected String iniciarSolicitud(ModelMap map, HttpSession session) {
		resetSession(map, session);
		SaludCoopUserDetails userDetails = (SaludCoopUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userManager.loadUserByUsername(userDetails.getUsername());

		UsuarioEntidadDto ue = usuarioEntidadManager.getUsuarioEntidad(user.getId());

		if (ue.getSedeIps() != null) {
			map.put("sedeIps", ue.getSedeIps());
		}

		if (ue.getProfesionalEspecialidad() != null) {
			map.put("profesionalEspecialidad", ue.getProfesionalEspecialidad());
		}

		cargarElementosDescriptivo("viasAdministracion", map, viaAdministracionManager.getAll());
		cargarElementosDescriptivo("lateralidades", map, lateralidadManager.getAll());
		cargarElementosDescriptivo("causasExternas", map, causaExternaManager.getAll());
		cargarElementosDescriptivo("finalidades", map, finalidadManager.getAll());
		cargarElementosDescriptivo("tiposCatastroficos", map, tipoCatastroficoManager.getAll());
		cargarElementosDescriptivo("respClinicaObservadas", map, respClinicaObservadaManager.getAll());
		cargarElementosDescriptivo("objetivos", map, objetivoManager.getAll());
		cargarElementosDescriptivo("tiposPrestacion", map, tipoPrestacionManager.getAll());
		cargarElementosDescriptivo("origenesRepeticion", map, origenRepeticionManager.getAll());
		cargarElementosDescriptivo("especialidades", map, especialidadManager.getAll());
		cargarElementosTipoIdentificacion("tipoIdentificacion", map, tipoIdentificacionManager.getAll());

		map.put("departamentos", departamentoManager.findAll());
		map.put("municipios", new ArrayList<MunicipioDto>());
		map.put("tiposPeriodo", TipoPeriodo.values());
		map.put("archivos", new HashMap<String, DocumentoSoporteDto>());
		map.put("archivosItem", new HashMap<String, DocumentoSoporteDto>());
		
		verificarAplicaTutela(map);

		// map.put("ips", new IpsDto());

		return "prestador/common/solicitud";
	}


	
	private void verificarAplicaTutela(ModelMap map) {

        // Se obtiene el usuario en sesión
        SaludCoopUserDetails userDetails = (SaludCoopUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userManager.loadUserByUsername(userDetails.getUsername());

        AfiliadoVO afiliado = (AfiliadoVO) map.get("afiliado");
        
        boolean aplicaTutela = false;
        
        if(afiliado.getTutela() != null && afiliado.getTutela() == 1) {
            for (Role role : user.getRoles()) {
                if (SystemConstants.ROLE_LINEA_FRENTE.equals(role.getRole())) {
                    aplicaTutela = true;
                    break;
                }
            }
        }
        map.put("aplicaTutela", aplicaTutela);
	}

	@RequestMapping(value = "/prestador/crearSolicitud", method = RequestMethod.POST)
	protected @ResponseBody
	ValidatedResponse<List<AutorizacionDto>> crearSolicitud(@ModelAttribute SolicitudForm form, ModelMap map) {
		ValidatedResponse<List<AutorizacionDto>> response = new ValidatedResponse<>();
        try {
    		@SuppressWarnings("unchecked")
    		HashMap<String, DocumentoSoporteDto> archivos = (HashMap<String, DocumentoSoporteDto>) map.get("archivos");
    		if (archivos == null) {
    			archivos = new HashMap<>();
    		}
    
    		HashMap<String, DocumentoSoporteDto> archivosItem = (HashMap<String, DocumentoSoporteDto>) map.get("archivosItem");
    		if (archivosItem == null) {
    			archivosItem = new HashMap<>();
    		}
    		AfiliadoVO afiliado = (AfiliadoVO) map.get("afiliado");
    
    		borrarVacios(form);
    
    		copiarDatosCompartidos(form);
			AfiliadoDto afiliadoDto = aManager.getAfiliadoByTipoNumeroDocumento(afiliado.getTipoIdentID(), afiliado.getNumeroIdentificacion());

			SaludCoopUserDetails userDetails = (SaludCoopUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			User user = userManager.loadUserByUsername(userDetails.getUsername());
			form.setUserId(user.getId());
			ProfesionalEspecialidadDto profesional = (ProfesionalEspecialidadDto) map.get("profesionalEspecialidad");
			SedeIpsDto sedeIps = (SedeIpsDto) map.get("sedeIps");
			SolicitudDto solicitud = parserForm.toSolicitudDto(form, sedeIps, archivosItem);
			solicitud.setObservaciones(form.getObservaciones());
			solicitud.setFechaCreacion(new Date());
			solicitud.setAfiliado(afiliadoDto);

			solicitud.setSedeIps(sedeIps);
			solicitud.setProfesionalSolicitante(profesional.getProfesional());
			// TODO: HAY QUE GUARDAR PROFESIONAL ESPECIALIDAD EN LA SOLICITUD

			for (Entry<String, DocumentoSoporteDto> archivo : archivos.entrySet()) {
				solicitud.getDocumentosSoporte().add(archivo.getValue());
			}
		
			DocumentoSoporteDto resumen = (DocumentoSoporteDto) map.get("resumen");
			if (resumen != null) {
				solicitud.getDocumentosSoporte().add(resumen);
			}
			
			solicitud.setLdf((boolean)map.get("esLDF"));

			RespuestaCompuesta<List<AutorizacionDto>> autorizaciones = validatorServiceManager.solicitarAutorizacion(solicitud);
			if (autorizaciones.getTransactionData() != null && !autorizaciones.getTransactionData().isEmpty()) {
				response.setContent(autorizaciones.getTransactionData());
				map.put("autorizaciones", autorizaciones.getTransactionData());
			} else {
				response.addGeneralError(autorizaciones.getRespuestaDto().getMensajeRespuesta());
			}

			// TODO : enviar al validador
		} catch (ServiceException e) {
            LOGGER.error("Error al crear la solicitud", e);
			response.addGeneralError(e.getMessage());
		} catch (Throwable e) {
		    LOGGER.error("Error al crear la solicitud", e);
		    response.addGeneralError(I18NUtils.getInstance().getText("message.error.crearSolicitud"));
		}

		return response;
	}

	@RequestMapping(value = "/prestador/set_profesional", method = RequestMethod.POST)
	protected @ResponseBody
	ValidatedResponse<String> setProfesional(@ModelAttribute ProfesionalVO form, ModelMap map) {
		ValidatedResponse<String> response = new ValidatedResponse<String>();
		ProfesionalEspecialidadDto pDto = profesionalManager.findOne(form.getId());
		if (pDto != null) {
			map.put("profesionalEspecialidad", pDto);
			response.setContent("Ok");
		} else {
			response.addGeneralErrors("Profesional no encontrado");
		}

		return response;
	}

	@RequestMapping(value = "/prestador/set_sedeIps", method = RequestMethod.POST)
	protected @ResponseBody
	ValidatedResponse<String> setSedeIps(@ModelAttribute SedeIpsVO form, ModelMap map) {
		ValidatedResponse<String> response = new ValidatedResponse<String>();
		SedeIpsDto sDto = sedeManager.findOne(form.getId());
		if (sDto != null) {
			map.put("sedeIps", sDto);
			response.setContent("Ok");
		} else {
			response.addGeneralErrors("Sede Ips no encontrado");
		}

		return response;
	}

	
	@Secured("ROLE_LDF")
	@RequestMapping(value = "/prestador/eliminarSolicitudParcial/{nroSolicitud}", method = RequestMethod.POST)
	protected @ResponseBody
	ValidatedResponse<String> eliminarSolicitudParcial(@PathVariable String nroSolicitud, ModelMap map) {
		ValidatedResponse<String> response = new ValidatedResponse<>();
		if(nroSolicitud != null && !nroSolicitud.isEmpty()){
			try {
				solParManager.eliminarSolicitudParcial(nroSolicitud);
				response.setContent("OK");
			} catch (NoSePuedeElilminarSPException e) {
				response.addGeneralError(I18NUtils.getInstance().getText("message.error.spNoBorrada"));
				return response;
			}
		}
		
		return response;
	}
	
	@Secured("ROLE_LDF")
	@RequestMapping(value = "/prestador/guardarParcialmenteSolicitud", method = RequestMethod.POST)
	protected @ResponseBody
	ValidatedResponse<String> guardarParcialmenteSolicitud(@ModelAttribute SolicitudForm form, ModelMap map) {

		ValidatedResponse<String> response = new ValidatedResponse<>();

		AfiliadoVO afiliado = (AfiliadoVO) map.get("afiliado");

		borrarVacios(form);

		final ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// TODO: ver si es necesario hacer un cambio de algún caracter en
		// especial
		// mapper.getJsonFactory().setCharacterEscapes(new
		// SpecialCharacterEscapes());
		Integer nroSolicitud= null;
		try {
			String jsonSolicitud = mapper.writeValueAsString(form);

			SedeIpsDto sede = (SedeIpsDto) map.get("sedeIps");

			if (map.get("nroSolicitud") != null) {
				nroSolicitud = solParManager.modificarSolicitudParcial((Integer) map.get("nroSolicitud"), sede != null ? sede.getId() : null, jsonSolicitud);
			} else {
				SaludCoopUserDetails userDetails = (SaludCoopUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				User user = userManager.loadUserByUsername(userDetails.getUsername());

				nroSolicitud = solParManager.guardarNuevaSolicitudParcial(sede != null ? sede.getId() : null, afiliado, user, jsonSolicitud);
				if(nroSolicitud!= null){
					map.put("nroSolicitud", nroSolicitud);
				}
			}

			// TODO: acá habría que guardar jsonSolicitud en algún campo de
			// alguna tabla.

		} catch (final IOException ex) {
			ex.printStackTrace();
			nroSolicitud = null;
		}

		if (nroSolicitud!= null) {
			
			response.setContent(String.valueOf(nroSolicitud));
		} else {
			response.addGeneralError("Ocurrió un error al guardar la solicitud.");
		}

		return response;
	}

	@RequestMapping(value = "/prestador/cargarSolicitud/{id}", method = RequestMethod.GET)
	protected String cargarSolicitud(@PathVariable("id") Integer id, ModelMap map, HttpSession session) {

		SolicitudParcialDto solPar = solParManager.findById(id);

		try {
			AfiliadoDto afiliadoDto = aManager.getAfiliadoByTipoNumeroDocumento(solPar.getTipoIdentificacionAfiliado().getId(), solPar.getNumeroIdentificacionAfiliado());
			
			map.addAttribute("nroSolicitud", solPar.getNroSolicitud());
			map.addAttribute("esParcial", true);
			map.addAttribute("json", solPar.getFormData());
			map.remove("afiliado");
			map.addAttribute("afiliado", pvo.getAfiliadoVO(afiliadoDto));
			map.put("tiposPeriodo", TipoPeriodo.values());
			map.put("archivos", new HashMap<String, String>());
			iniciarSolicitud(map, session);
			
		} catch (ServiceException e) {
			LOGGER.error(e.getMessage(), e);
		}
		map.put("menuActivo", "menu_2");
		
		return "prestador/common/solicitud";
	}

	@RequestMapping(value = "/prestador/loadJson", method = RequestMethod.POST)
	protected @ResponseBody
	ValidatedResponse<String> loadJson(ModelMap map) {
		ValidatedResponse<String> response = new ValidatedResponse<>();
		response.setContent((String) map.get("json"));
		map.remove("json");
		map.remove("esParcial");
		return response;
	}

	private void borrarVacios(SolicitudForm form) {
		Integer i = -1;
		if (form.getMedicamentos() != null) {
			List<MedicamentoItemForm> medItems = new Vector<>();
			for (MedicamentoItemForm medItem : form.getMedicamentos()) {
				i++;
				if (medItem.getCodigo() != null) {
					medItems.add(medItem);

				}
			}
			form.setMedicamentos(medItems);
		}
		i = -1;
		if (form.getProcedimientos() != null) {
			List<ProcedimientoItemForm> procItems = new Vector<>();
			for (ProcedimientoItemForm procItem : form.getProcedimientos()) {
				i++;
				if (procItem.getCodigo() != null) {
					procItems.add(procItem);
				}
			}
			form.setProcedimientos(procItems);
		}
		
		i = -1;
		if (form.getInsumos() != null) {
			List<InsumoItemForm> procItems = new Vector<>();
			for (InsumoItemForm procItem : form.getInsumos()) {
				i++;
				if (procItem.getCodigo() != null) {
					procItems.add(procItem);
				}
			}
			form.setInsumos(procItems);
		}
		i = -1;
		if (form.getDiagnosticos() != null) {

			List<DiagnosticoItemForm> diagnosticos = new Vector<>();
			for (DiagnosticoItemForm diagnostico : form.getDiagnosticos()) {
				i++;
				if (diagnostico.getCodigo() != null) {
					diagnosticos.add(diagnostico);
				}
			}
			form.setDiagnosticos(diagnosticos);
		}

	}

	private void copiarDatosCompartidos(SolicitudForm form) {
		for (MedicamentoItemForm med : form.getMedicamentos()) {
			if (med.getFormularioCTC() != null) {
				med.getFormularioCTC().setCausaExterna(med.getPrescripcion().getCausaExterna());
				med.getFormularioCTC().setFinalidad(med.getPrescripcion().getFinalidad());
				med.getFormularioCTC().setTipoCatastrofico(med.getPrescripcion().getTipoCatastrofico());
			}
		}

		for (ProcedimientoItemForm proc : form.getProcedimientos()) {
			if (proc.getFormularioCTC() != null) {
				proc.getFormularioCTC().setCausaExterna(proc.getPrescripcion().getCausaExterna());
				proc.getFormularioCTC().setFinalidad(proc.getPrescripcion().getFinalidad());
				proc.getFormularioCTC().setTipoCatastrofico(proc.getPrescripcion().getTipoCatastrofico());
			}
		}
		
		for (InsumoItemForm insumo : form.getInsumos()) {
			if (insumo.getFormularioCTC() != null) {
				insumo.getFormularioCTC().setCausaExterna(insumo.getPrescripcion().getCausaExterna());
				insumo.getFormularioCTC().setFinalidad(insumo.getPrescripcion().getFinalidad());
				insumo.getFormularioCTC().setTipoCatastrofico(insumo.getPrescripcion().getTipoCatastrofico());
			}
		}
	}

	@RequestMapping(value = "/prestador/upload/otros", method = RequestMethod.POST)
	protected void uploadOtros(MultipartHttpServletRequest request, HttpServletResponse response, ModelMap map) {

		HashMap<String, DocumentoSoporteDto> archivos = (HashMap<String, DocumentoSoporteDto>) map.get("archivos");

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
				archivos.put(mpf.getOriginalFilename(), doc);

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
	
	
	@RequestMapping(value = "/prestador/upload/formularioCTC", method = RequestMethod.POST)
	protected void uploadFormularioCTC(@RequestParam("tipoDocumento") String tipoDocumento,
									   @RequestParam("idItem") String idItem, 
									MultipartHttpServletRequest request, HttpServletResponse response, ModelMap map) {

		HashMap<String, DocumentoSoporteDto> archivos = (HashMap<String, DocumentoSoporteDto>) map.get("archivosItem");

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
				doc.setTipoDocSoporte(tipoDocSoporteManager.getByCodigo(tipoDocumento));
				doc.setNombreArchivoServidor(archivo.getFilename());
				archivos.put(tipoDocumento + "_" + idItem, doc);

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

	}
	@RequestMapping(value = "/prestador/upload/resumen", method = RequestMethod.POST)
	protected void uploadResumen(MultipartHttpServletRequest request, HttpServletResponse response, ModelMap map) {

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
				doc.setTipoDocSoporte(tipoDocSoporteManager.getByCodigo(SystemConstants.DOC_RESUMEN));
				doc.setNombreArchivoServidor(archivo.getFilename());

				map.put("resumen", doc);

			} catch (final IOException e) {
				/* TODO Se debe manejar este error p7ara presentarlo en pantalla */
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

	@RequestMapping(value = "/prestador/eliminarResumen", method = RequestMethod.POST)
	protected void eliminarResumen(HttpServletResponse response, ModelMap map) {
	    if(map.get("resumen") != null) {
	        removeFile(((DocumentoSoporteDto) map.get("resumen")).getNombreArchivoServidor());
	    }
		map.put("resumen", null);
	}

	@SuppressWarnings("unchecked")
    @RequestMapping(value = "/prestador/eliminarOtros", method = RequestMethod.POST)
	protected void eliminarOtros(HttpServletResponse response, ModelMap map, @RequestParam String name) {
		HashMap<String, DocumentoSoporteDto> archivos = (HashMap<String, DocumentoSoporteDto>) map.get("archivos");
		if (archivos != null) {
			if (archivos.containsKey(name)) {
			    removeFile(archivos.get(name).getNombreArchivoServidor());
				archivos.remove(name);
			}
		}

	}
	
	private void removeFile(String fileName) {
	    if (fileName != null) {
            try {
                fileRepositoryProvider.getInstance().deleteFile(fileName);
            } catch (Exception e) {
                LOGGER.error("Error al eliminar el archivo", e);
            }
        }
	}

	private void resetSession(ModelMap modelMap, HttpSession session) {
		
		Boolean esParcial = (Boolean) modelMap.get("esParcial");
		removeFromSession(modelMap, session, "archivos");
		
		removeFromSession(modelMap, session, "archivosItem");
		

		if(esParcial == null || (esParcial!= null && !esParcial)){
			removeFromSession(modelMap, session, "nroSolicitud");
		}
		
		removeFromSession(modelMap, session, "sedeIps");

		removeFromSession(modelMap, session, "profesionalEspecialidad");

		removeFromSession(modelMap, session, "resumen");
	}

	/**
	 * Verifica si el valor de las unidades aprobadas para el medicamento
	 * enviadas por parámetro, cumplen con el rango establecido para el
	 * medicamento
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/prestador/cumpleRangoMedicamento")
	@ResponseBody
	public Object[] cumpleRangoMedicamento(Long idMedicamento, Integer dosis, Integer frecuencia, Integer cada, Integer duracion, ModelMap map) {
		try {

			AfiliadoVO afiliado = (AfiliadoVO) map.get("afiliado");

			return medicamentoManager.validarRangoMedicamentoSolicitud(idMedicamento, dosis, frecuencia, cada, duracion, afiliado.getId());
		} catch (Throwable e) {
			LOGGER.error("Error al obtener el rango" + e);
			return new Object[] { -1, "Error al obtener el rango" };
		}
	}
	
	/**
	 * Verifica si un medicamento es condicionado y, en caso afirmativo, si el diagnostico asociado es valido
	 * 
	 * @param med
	 * @param dx
	 * @return
	 */
	@RequestMapping(value = "/prestador/cumpleMedicamentoCondicionadoDiagnostico/{med}/{dx}")
	@ResponseBody
	public boolean cumpleMedicamentoCondicionadoDiagnostico(@PathVariable("med") String med, @PathVariable("dx") String dx) {

		return  medicamentoManager.cumpleMedicamentoCondicionadoDiagnostico(med, dx);
		
	}
	
	/**
	 * Verifica si el insumo es de alguno de los kits entregados por la entidad prestadora
	 * @param id id del insumo que se va a verificar
	 * @return si es o no insumo de algun kit
	 */
	@RequestMapping(value = "/prestador/esInsumoKit/{id}")
    @ResponseBody
	public boolean esInsumoKit(@PathVariable("id") String id){
	    InsumoTopeDto dto = insumoTopeManager.findOneByInsumoId(id);
	    if(dto != null){
	        return true;
	    } else {
	        return false;
	    }
	}

	@RequestMapping(value = "/prestador/formularioCTCInsumoKit/{id}/{cant}", method = RequestMethod.GET)
    @ResponseBody
	public boolean formularioCTCInsumoKit(@PathVariable("id") Long id, @PathVariable("cant") Integer cant, ModelMap map){
	    
	    AfiliadoVO afiliado = (AfiliadoVO) map.get("afiliado");
	    
	    if(afiliado != null){
    	    ValidarTopesCantidadDto dto = new ValidarTopesCantidadDto();
    	    dto.setIdInsumo(id);
    	    dto.setCantidad(cant);
    	    dto.setIdAfiliado(afiliado.getId());
    	    
    	    RespuestaDto respuesta = validatorServiceManager.excedioTope(dto);
            
            if (respuesta != null) {
                return respuesta.getCodigoRespuesta() == 0 ? Boolean.TRUE : Boolean.FALSE;
            } else {
                return false;
            }
	    }	    
	    
	    return false;
	}
}
