package com.conexia.saludcoop.web.crud;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.conexia.saludcoop.common.crud.ProfesionalItemVO;
import com.conexia.saludcoop.common.dto.EspecialidadDto;
import com.conexia.saludcoop.common.dto.MunicipioDto;
import com.conexia.saludcoop.common.dto.ProfesionalDto;
import com.conexia.saludcoop.common.dto.SedeIpsDto;
import com.conexia.saludcoop.common.util.I18NUtils;
import com.conexia.saludcoop.util.ValidatedPageResponse;
import com.conexia.saludcoop.web.controller.util.RedireccionamientoUtils;
import com.conexia.saludcoop.web.crud.util.CrudUtil;
import com.conexia.saludcoop.web.form.FiltroCrudForm;
import com.conexia.saludcoop.web.form.ProfesionalForm;
import com.conexia.saludcoop.web.manager.DepartamentoManager;
import com.conexia.saludcoop.web.manager.EspecialidadManager;
import com.conexia.saludcoop.web.manager.ProfesionalManager;
import com.conexia.saludcoop.web.manager.SedeIpsManager;
import com.conexia.saludcoop.web.manager.TipoIdentificacionManager;
import com.conexia.saludcoop.web.vo.PageVO;
import com.conexia.saludcoop.web.vo.ProfesionalVO;

@Controller
@RequestMapping(value = "/profesionalCrud/")
@SessionAttributes({"item"})
public class ProfesionalCrudController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(ProfesionalCrudController.class);
	
	@Autowired
	private ProfesionalManager pm;
	@Autowired
	private TipoIdentificacionManager tipoIdentificacionManager;
	@Autowired
	private SedeIpsManager sedeManager;
	@Autowired
	private EspecialidadManager especialidadManager;
	@Autowired
	private ProfesionalManager profesionalManager;
    @Autowired
    private DepartamentoManager departamentoManager;
    
    
	@RequestMapping(value = "/cargarProfesionales", method = RequestMethod.POST)
	@ResponseBody
	public ValidatedPageResponse<List<ProfesionalItemVO>> cargarProfesionales(FiltroCrudForm filter, ModelMap map) {
		
		ValidatedPageResponse<List<ProfesionalItemVO>> response = new ValidatedPageResponse<>();

		try {
			PageVO<ProfesionalItemVO> paginaBandeja =  pm.getProfesionalesProj(filter);
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
	 * Para editar un profesional
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "gestionarEdicion/{itemId}")
    protected String gestionarEdicion(@PathVariable("itemId") String id, ModelMap map) {
		ProfesionalDto profesionalDto=pm.findOne(Long.valueOf(id));
		ProfesionalVO profesionalVO= toProfesionalVO(profesionalDto);
		map.put("departamentos", departamentoManager.findAll());
		map.put("municipios", new ArrayList<MunicipioDto>());
		map.put("profesional", profesionalVO);
		map.put("esEdicion", true);
		map.put("item", id);
		CrudUtil.cargarElementosTipoIdentificacion("tipoIdentificacion", map, tipoIdentificacionManager.getAll());
       return "crud/detail/gestionarProfesionalDetail";
       
    }
	
	@RequestMapping(value = "gestionarCreacion")
    protected String gestionarCreacion(ModelMap map) {
		ProfesionalVO profesionalVO= new ProfesionalVO();
		map.put("profesional", profesionalVO);
		map.put("esEdicion", false);
		map.put("item","");
		CrudUtil.cargarElementosTipoIdentificacion("tipoIdentificacion", map, tipoIdentificacionManager.getAll());
       return "crud/detail/gestionarProfesionalDetail";
    }
	
	private ProfesionalVO toProfesionalVO(ProfesionalDto dto){
		ProfesionalVO profesionalVO= new ProfesionalVO();
		profesionalVO.setPrimerNombre(dto.getPrimerNombre());
		profesionalVO.setSegundoNombre(dto.getSegundoNombre());
		profesionalVO.setPrimerApellido(dto.getPrimerApellido());
		profesionalVO.setSegundoApellido(dto.getSegundoApellido());
		profesionalVO.setNumeroDocumento(dto.getNumeroIdentificacion());
		profesionalVO.setTipoDocumento(Integer.valueOf(dto.getTipoIdentificacion()));
		profesionalVO.setRegistroMedico(dto.getRegistroMedico());
		profesionalVO.setId(dto.getId());
		return profesionalVO;
	}
	
	private ProfesionalDto toProfesionalDto(ProfesionalForm form){
		ProfesionalDto profesionalDto= new ProfesionalDto();
		profesionalDto.setPrimerNombre(form.getPrimerNombre());
		profesionalDto.setSegundoNombre(form.getSegundoNombre());
		profesionalDto.setPrimerApellido(form.getPrimerApellido());
		profesionalDto.setSegundoApellido(form.getSegundoApellido());
		profesionalDto.setNumeroIdentificacion(form.getNumeroDocumento());
		profesionalDto.setTipoIdentificacion(String.valueOf(form.getTipoDocumento()));
		profesionalDto.setId(form.getId());
		profesionalDto.setRegistroMedico(form.getRegistroMedico());
		return profesionalDto;
	}
	
	
    @RequestMapping(value = "guardarProfesional", method = RequestMethod.POST)
    @ResponseBody
    public ValidatedPageResponse<String> guardarProfesional(ProfesionalForm form, ModelMap map) {
        ValidatedPageResponse<String> response = new ValidatedPageResponse<>();
        try {
            if (form.getRegistroMedico() == null || (form.getRegistroMedico() != null && form.getRegistroMedico().isEmpty())) {
                response.addGeneralError(I18NUtils.getInstance().getText(RedireccionamientoUtils.ERROR_REGISTRO_MEDICO));
                response.setContent("");
            }
            if (form.getNumeroDocumento() == null || (form.getNumeroDocumento() != null && form.getNumeroDocumento().isEmpty())) {
                response.addGeneralError(I18NUtils.getInstance().getText(RedireccionamientoUtils.ERROR_DOCUMENTO));
                response.setContent("");
            }
            if (response.getGeneralErrors() == null || (response.getGeneralErrors() != null && response.getGeneralErrors().size() != 0)) {
                return response;
            }
            Long id = null;
            SedeIpsDto ipsDto = null;
            List<EspecialidadDto> especialidadesDto = null;
            EspecialidadDto especialidadDto = null;
            if (map.get("item") != null) {
                String aux = (String) map.get("item");
                if (!aux.equals(""))
                    id = Long.valueOf(aux);
            }
            ProfesionalDto dto = toProfesionalDto(form);
            if (id != null && !id.equals("")) {
                List<SedeIpsDto> sedeIpsDto = sedeManager.getSedesByIpsDireccion(form.getTipoIdentificacion(),
                        form.getNumeroIdentificacion(), form.getRazonSocial(), null, form.getMunicipioCodigo(), form.getDireccion(), null);

                if (sedeIpsDto != null && sedeIpsDto.size() > 0) {
                    especialidadesDto = especialidadManager.getEspecialidadByCodigo(form.getCodigo());
                    // Solo es una por que ya se aplicaron los filtros
                    especialidadDto = especialidadesDto.get(0);
                    ipsDto = sedeIpsDto.get(0);
                }
            } else {
                // Validaciones para creacion
                List<ProfesionalDto> dtos = profesionalManager.findByRegistroMedicoOrNumeroIdentificacion(form.getRegistroMedico(),
                        form.getNumeroDocumento());
                if (dtos == null || (dtos != null && dtos.size() > 0)) {
                    response.addGeneralError("No se puede crear el profesional por que el registro medico y/o Numero de Documento ya existe");
                    response.setContent("");
                    return response;
                }
                response.setContent(RedireccionamientoUtils.NUEVO_ELEMENTO);

            }
            dto.setId(id);
            pm.guardarProfesional(dto, ipsDto, especialidadDto);
            return response;
        } catch (Throwable e) {
            LOGGER.error("Error guardando el profesional", e);
            response.addGeneralError("Error guardando el profesional");
            response.setContent("");
            return response;
        }
    }
	
	@ResponseBody
    @RequestMapping(value = "listarSedeIpsPorMedico", method = RequestMethod.GET)
    public String listarSedeIpsPorMedico(ModelMap map) throws Throwable {
        String jsonString = null;
        String idProfesional = (String) map.get("item");
        try {
        	ObjectMapper mapper = new ObjectMapper();
        	List<SedeIpsDto> sedeIpsDtos=sedeManager.buscarIpsPorProfesional(Long.valueOf(idProfesional));
            jsonString = mapper.writeValueAsString(sedeIpsDtos);
            jsonString = "{\"aaData\":" + jsonString + "}";
        } catch (Throwable e) {
            LOGGER.error("Error listando las sedes", e);
            throw e;
        }
        return jsonString;
    }
	
	@ResponseBody
    @RequestMapping(value = "listarEspecialidadesPorMedico", method = RequestMethod.GET)
    public String listarEspecialidadesPorMedico(ModelMap map) throws Throwable {
        String jsonString = null;
        String idProfesional = (String) map.get("item");
        try {
        	ObjectMapper mapper = new ObjectMapper();
        	List<EspecialidadDto> especialidadesDtos=especialidadManager.buscarEspecialidadesPorMedico(Long.valueOf(idProfesional));
            jsonString = mapper.writeValueAsString(especialidadesDtos);
            jsonString = "{\"aaData\":" + jsonString + "}";
        } catch (Throwable e) {
            LOGGER.error("Error listando las especialidades", e);
            throw e;
        }
        return jsonString;
    }
	
	

}
