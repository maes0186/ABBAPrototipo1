package com.conexia.saludcoop.web.controller.prestador;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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

import com.conexia.saludcoop.common.dto.AfiliadoDto;
import com.conexia.saludcoop.common.dto.DiagnosticoDto;
import com.conexia.saludcoop.common.dto.EspecialidadDto;
import com.conexia.saludcoop.common.dto.InsumoDto;
import com.conexia.saludcoop.common.dto.MedicamentoDto;
import com.conexia.saludcoop.common.dto.MunicipioDto;
import com.conexia.saludcoop.common.dto.ProcedimientoDto;
import com.conexia.saludcoop.common.dto.ProfesionalEspecialidadDto;
import com.conexia.saludcoop.common.dto.SedeIpsDto;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudParcialDto;
import com.conexia.saludcoop.common.entity.security.User;
import com.conexia.saludcoop.common.exception.ServiceException;
import com.conexia.saludcoop.security.SaludCoopUserDetails;
import com.conexia.saludcoop.util.ValidatedResponse;
import com.conexia.saludcoop.web.BaseValidatingController;
import com.conexia.saludcoop.web.form.IdentificacionForm;
import com.conexia.saludcoop.web.form.ProcedimientoHomologoSearchForm;
import com.conexia.saludcoop.web.form.SeachCodigoDescripcionForm;
import com.conexia.saludcoop.web.form.SeachProfesionalForm;
import com.conexia.saludcoop.web.form.SearchSedeIPSForm;
import com.conexia.saludcoop.web.form.SearchSolicitudParcialForm;
import com.conexia.saludcoop.web.manager.DiagnosticoManager;
import com.conexia.saludcoop.web.manager.EspecialidadManager;
import com.conexia.saludcoop.web.manager.IAfiliadoManager;
import com.conexia.saludcoop.web.manager.InsumosManager;
import com.conexia.saludcoop.web.manager.MedicamentoManager;
import com.conexia.saludcoop.web.manager.MunicipioManager;
import com.conexia.saludcoop.web.manager.ProcedimientoManager;
import com.conexia.saludcoop.web.manager.ProfesionalEspecialidadManager;
import com.conexia.saludcoop.web.manager.SedeIpsManager;
import com.conexia.saludcoop.web.manager.SolicitudParcialManager;
import com.conexia.saludcoop.web.manager.UsuarioEntidadManager;
import com.conexia.saludcoop.web.manager.UsuarioManager;
import com.conexia.saludcoop.web.vo.AfiliadoVO;
import com.conexia.saludcoop.web.vo.EspecialidadVO;
import com.conexia.saludcoop.web.vo.InsumoVO;
import com.conexia.saludcoop.web.vo.MedicamentoVO;
import com.conexia.saludcoop.web.vo.MunicipioVO;
import com.conexia.saludcoop.web.vo.ProcedimientoMedicamentoVO;
import com.conexia.saludcoop.web.vo.ProfesionalVO;
import com.conexia.saludcoop.web.vo.SedeIpsVO;
import com.conexia.saludcoop.web.vo.SolicitudParcialVO;
import com.conexia.saludcoop.web.vo.VOUtils;
import com.conexia.saludcoop.web.vo.utils.ParserVO;

@Controller
@RequestMapping(value = "*/search", method = RequestMethod.POST)
@SessionAttributes({ "sedeIps","profesionalEspecialidad", "afiliado" })
public class SearchController extends BaseValidatingController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(SearchController.class);
	
	
	@Autowired
	private ProcedimientoManager prestManager;
	@Autowired
	private MedicamentoManager medicamentosManager;
	
	@Autowired
	private InsumosManager insumosManager;
	
	@Autowired
	private DiagnosticoManager dxManager;
	@Autowired
	private SedeIpsManager sedeManager;
	@Autowired
	private EspecialidadManager especialidadManager;

	@Autowired
	private ProfesionalEspecialidadManager profesionalManager;

	@Autowired
	private IAfiliadoManager afiliadoManager;

	@Autowired
	private ParserVO pvo;
	
	@Autowired
	private SolicitudParcialManager solicitudParcialManager;
	
	@Autowired
	private UsuarioEntidadManager usuarioEntidadManager;

	@Autowired
	private UsuarioManager userManager;

	@Autowired
    private MunicipioManager municipioManager;
	

	//
	@RequestMapping(value = "/procedimientos")
	protected @ResponseBody
	ValidatedResponse<List<ProcedimientoMedicamentoVO>> buscarPrestacion(@ModelAttribute SeachCodigoDescripcionForm form, ModelMap map) {

		ValidatedResponse<List<ProcedimientoMedicamentoVO>> validatedResponse = new ValidatedResponse<>();

		List<ProcedimientoDto> procedimientosDto = prestManager.getProcedimientoByCodigoDescripcion(form.getCodigo(), form.getDescripcion());

		if (procedimientosDto == null || procedimientosDto.isEmpty()) {
			validatedResponse.addGeneralError("No se encontraron resultados");
		} else {

			List<ProcedimientoMedicamentoVO> pmvos = new Vector<ProcedimientoMedicamentoVO>();
			for (ProcedimientoDto m : procedimientosDto) {
				pmvos.add(new ProcedimientoMedicamentoVO(m));
			}

			validatedResponse.setContent(pmvos);
		}

		return validatedResponse;

	}

	@RequestMapping(value = "/procedimientosPosPrevios")
	protected @ResponseBody
	ValidatedResponse<List<ProcedimientoMedicamentoVO>> buscarProcedimientoPOSPrevios(@ModelAttribute SeachCodigoDescripcionForm form, ModelMap map) {

		return buscarProcedimientoPOS(form, map);

	}

	@RequestMapping(value = "/procedimientoPosHomologo")
	protected @ResponseBody
	ValidatedResponse<List<ProcedimientoMedicamentoVO>> buscarProcedimientoPOSHomologos(@ModelAttribute ProcedimientoHomologoSearchForm form, ModelMap map) {

		return buscarProcedimientoPOS(form.getProcedimientoPosHomologo(), map);

	}

	@RequestMapping(value = "/procedimientosPOS")
	protected @ResponseBody
	ValidatedResponse<List<ProcedimientoMedicamentoVO>> buscarProcedimientoPOS(@ModelAttribute SeachCodigoDescripcionForm form, ModelMap map) {

		ValidatedResponse<List<ProcedimientoMedicamentoVO>> validatedResponse = new ValidatedResponse<>();

		List<ProcedimientoDto> procedimientosDto = prestManager.getProcedimientosPosByCodigoDescripcion(form.getCodigo(), form.getDescripcion());

		if (procedimientosDto == null || procedimientosDto.isEmpty()) {
			validatedResponse.addGeneralError("No se encontraron resultados");
		} else {

			List<ProcedimientoMedicamentoVO> pmvos = new Vector<ProcedimientoMedicamentoVO>();
			for (ProcedimientoDto m : procedimientosDto) {
				pmvos.add(new ProcedimientoMedicamentoVO(m));
			}

			validatedResponse.setContent(pmvos);
		}

		return validatedResponse;

	}

	@RequestMapping(value = "/diagnosticos")
	protected @ResponseBody
	ValidatedResponse<List<DiagnosticoDto>> buscarDiagnosticos(@ModelAttribute SeachCodigoDescripcionForm form, ModelMap map) {

		ValidatedResponse<List<DiagnosticoDto>> validatedResponse = new ValidatedResponse<>();

		List<DiagnosticoDto> diagnosticos = dxManager.getDiagnosticosByCodigoDescripcion(form.getCodigo(), form.getDescripcion());

		if (diagnosticos == null || diagnosticos.isEmpty()) {
			validatedResponse.addGeneralError("No se encontraron resultados");
		} else {
			validatedResponse.setContent(diagnosticos);
		}

		return validatedResponse;

	}

	@RequestMapping(value = "/medicamentos")
	protected @ResponseBody
	ValidatedResponse<List<ProcedimientoMedicamentoVO>> buscarMedicamentos(@ModelAttribute SeachCodigoDescripcionForm form, ModelMap map) {

		ValidatedResponse<List<ProcedimientoMedicamentoVO>> validatedResponse = new ValidatedResponse<>();

		List<MedicamentoDto> medicamentosDto = medicamentosManager.getMedicamentosByCodigoDescripcionComercial(form.getCodigo(), form.getDescripcion(),form.isComercial());

		if (medicamentosDto == null || medicamentosDto.isEmpty()) {
			validatedResponse.addGeneralError("No se encontraron resultados");
		} else {

			List<ProcedimientoMedicamentoVO> pmvos = new Vector<ProcedimientoMedicamentoVO>();
			for (MedicamentoDto m : medicamentosDto) {
				pmvos.add(new ProcedimientoMedicamentoVO(m));
			}

			validatedResponse.setContent(pmvos);
		}

		return validatedResponse;

	}
	
	@RequestMapping(value = "/insumos")
	protected @ResponseBody
	ValidatedResponse<List<ProcedimientoMedicamentoVO>> buscarInsumos(@ModelAttribute SeachCodigoDescripcionForm form, ModelMap map) {

		ValidatedResponse<List<ProcedimientoMedicamentoVO>> validatedResponse = new ValidatedResponse<>();

		List<InsumoDto> insumosDto = insumosManager.getInsumoByCodigoDescripcion(form.getCodigo(), form.getDescripcion());

		if (insumosDto == null || insumosDto.isEmpty()) {
			validatedResponse.addGeneralError("No se encontraron resultados");
		} else {

			List<ProcedimientoMedicamentoVO> pmvos = new Vector<ProcedimientoMedicamentoVO>();
			for (InsumoDto insumo : insumosDto) {
				pmvos.add(new ProcedimientoMedicamentoVO(insumo));
			}

			validatedResponse.setContent(pmvos);
		}

		return validatedResponse;

	}

	@RequestMapping(value = "/insumosPOS")
	protected @ResponseBody
	ValidatedResponse<List<ProcedimientoMedicamentoVO>> buscarInsumosPos(@ModelAttribute SeachCodigoDescripcionForm form, ModelMap map) {

		ValidatedResponse<List<ProcedimientoMedicamentoVO>> validatedResponse = new ValidatedResponse<>();

		List<InsumoDto> insumosDto = insumosManager.getInsumosPOSByCodigoDescripcion(form.getCodigo(), form.getDescripcion());

		if (insumosDto == null || insumosDto.isEmpty()) {
			validatedResponse.addGeneralError("No se encontraron resultados");
		} else {

			List<ProcedimientoMedicamentoVO> pmvos = new Vector<ProcedimientoMedicamentoVO>();
			for (InsumoDto m : insumosDto) {
				pmvos.add(new ProcedimientoMedicamentoVO(m));
			}

			validatedResponse.setContent(pmvos);
		}

		return validatedResponse;

	}

	@RequestMapping(value = "/insumosPosPrevios")
	protected @ResponseBody
	ValidatedResponse<List<ProcedimientoMedicamentoVO>> buscarInsumosPosPrevios(@ModelAttribute SeachCodigoDescripcionForm form, ModelMap map) {

		return buscarInsumosPos(form, map);

	}	
	
	
	@RequestMapping(value = "/medicamentosPOS")
	protected @ResponseBody
	ValidatedResponse<List<ProcedimientoMedicamentoVO>> buscarMedicamentosPos(@ModelAttribute SeachCodigoDescripcionForm form, ModelMap map) {

		ValidatedResponse<List<ProcedimientoMedicamentoVO>> validatedResponse = new ValidatedResponse<>();

		List<MedicamentoDto> medicamentosDto = medicamentosManager.getMedicamentosPOSByCodigoDescripcion(form.getCodigo(), form.getDescripcion());

		if (medicamentosDto == null || medicamentosDto.isEmpty()) {
			validatedResponse.addGeneralError("No se encontraron resultados");
		} else {

			List<ProcedimientoMedicamentoVO> pmvos = new Vector<ProcedimientoMedicamentoVO>();
			for (MedicamentoDto m : medicamentosDto) {
				pmvos.add(new ProcedimientoMedicamentoVO(m));
			}

			validatedResponse.setContent(pmvos);
		}

		return validatedResponse;

	}

	@RequestMapping(value = "/medicamentosPosPrevios")
	protected @ResponseBody
	ValidatedResponse<List<ProcedimientoMedicamentoVO>> buscarMedicamentosPosPrevios(@ModelAttribute SeachCodigoDescripcionForm form, ModelMap map) {

		return buscarMedicamentosPos(form, map);

	}

	@RequestMapping(value = "/medicamentoPosHomologo/{id}")
	protected @ResponseBody
	ValidatedResponse<MedicamentoVO> buscarMedicamentoPosHomologo(@PathVariable("id") Long id, ModelMap map) {

		ValidatedResponse<MedicamentoVO> validatedResponse = new ValidatedResponse<>();

		MedicamentoDto medicamentoDto = medicamentosManager.getMedicamentoHomologo(id);

		if (medicamentoDto == null) {
			validatedResponse.addGeneralError("No se encontró el medicamento homólogo");
		} else {

			validatedResponse.setContent(VOUtils.toOneMedicamentoVO(medicamentoDto));
		}

		return validatedResponse;

	}
	
	@RequestMapping(value = "/insumoPosHomologo/{id}")
	protected @ResponseBody
	ValidatedResponse<InsumoVO> buscarInsumoPosHomologo(@PathVariable("id") Long id, ModelMap map) {

		ValidatedResponse<InsumoVO> validatedResponse = new ValidatedResponse<>();

		InsumoDto insumoDto = insumosManager.getInsumoHomologo(id);

		if (insumoDto == null) {
			validatedResponse.addGeneralError("No se encontró el insumo homólogo");
		} else {

			validatedResponse.setContent(VOUtils.toOneInsumoVO(insumoDto));
		}

		return validatedResponse;

	}

	@RequestMapping(value = "/profesional")
	protected @ResponseBody
	ValidatedResponse<List<ProfesionalVO>> buscarProfesional(@ModelAttribute SeachProfesionalForm form, ModelMap map) {

		ValidatedResponse<List<ProfesionalVO>> validatedResponse = new ValidatedResponse<>();
		SedeIpsDto sedeIps = (SedeIpsDto)map.get("sedeIps");
		List<ProfesionalEspecialidadDto> profesionalDtos = new Vector<>();
		if(sedeIps == null){
			profesionalDtos = profesionalManager.getProfesionales(form);	
		}else{
			profesionalDtos = profesionalManager.getProfesionalesBySede(sedeIps, form);
		}

		if (profesionalDtos == null || profesionalDtos.isEmpty()) {
			validatedResponse.addGeneralError("No se encontraron resultados");
		} else {

			List<ProfesionalVO> pmvos = new Vector<ProfesionalVO>();
			for (ProfesionalEspecialidadDto m : profesionalDtos) {
				pmvos.add(new ProfesionalVO(m));
			}

			validatedResponse.setContent(pmvos);
		}

		return validatedResponse;

	}

	@RequestMapping(value = "/afiliadoDocumento")
	protected @ResponseBody
	ValidatedResponse<List<AfiliadoVO>> buscarAfiliadoByDocumento(@ModelAttribute IdentificacionForm form, ModelMap map) {

		ValidatedResponse<List<AfiliadoVO>> validatedResponse = new ValidatedResponse<>();

		AfiliadoDto afiliadoDto = null;

		try {
			afiliadoDto = afiliadoManager.getAfiliadoByTipoNumeroDocumento(form.getTipoIdentificacion(), form.getNumeroIdentificacion(), form.getEps());

			if (afiliadoDto == null ) {
				validatedResponse.addGeneralError("No se encontraron resultados");
			} else {

				List<AfiliadoVO> pmvos = new Vector<AfiliadoVO>();

				pmvos.add(pvo.getAfiliadoVO(afiliadoDto));
				validatedResponse.setContent(pmvos);
			}

		} catch (ServiceException e) {
			validatedResponse.addGeneralError("Error en la comunicación");
		}
		return validatedResponse;
	}

	@RequestMapping(value = "/afiliadoNombres")
	protected @ResponseBody
	ValidatedResponse<List<AfiliadoVO>> buscarAfiliadoByNombres(@ModelAttribute IdentificacionForm form, ModelMap map) {

		ValidatedResponse<List<AfiliadoVO>> validatedResponse = new ValidatedResponse<>();

		List<AfiliadoDto> afiliadoDtos = new Vector<>();

		try {
			afiliadoDtos = afiliadoManager.getAfiliadosByNombres(form.getNombres(), form.getApellidos(), form.getEps());

			if (afiliadoDtos == null || afiliadoDtos.isEmpty()) {
				validatedResponse.addGeneralError("No se encontraron resultados");
			} else {

				List<AfiliadoVO> pmvos = new Vector<AfiliadoVO>();
				for (AfiliadoDto m : afiliadoDtos) {
					pmvos.add(pvo.getAfiliadoVO(m));
				}

				validatedResponse.setContent(pmvos);
			}
		} catch (ServiceException e) {

			e.printStackTrace();
			validatedResponse.addGeneralError("Error en la comunicación");
		}
		return validatedResponse;

	}

	
	@RequestMapping(value = "/sedeIps")
	protected @ResponseBody
	ValidatedResponse<List<SedeIpsVO>> buscarSedesIps(
			@ModelAttribute SearchSedeIPSForm form, ModelMap map) {
		try {
			AfiliadoVO afiliado = (AfiliadoVO)map.get("afiliado");
			Long eps=null;
			if(afiliado!=null)eps=afiliado.getEps();
			ValidatedResponse<List<SedeIpsVO>> validatedResponse = new ValidatedResponse<>();
			List<SedeIpsVO> sedesEncontradas = new Vector<>();
			List<SedeIpsDto> sedeIpsDto = sedeManager.getSedesByIpsDireccion(
					form.getTipoIdentificacion(),
					form.getNumeroIdentificacion(), form.getRazonSocial(),
					form.getDepartamentoSedeIpsId(), form.getMunicipioSedeIpsId(),
					form.getDireccion(), eps);

			if (sedeIpsDto == null || sedeIpsDto.isEmpty()) {
				validatedResponse
						.addGeneralError("No se encontraron resultados");
			} else {
				for (SedeIpsDto sedeIps : sedeIpsDto) {
					sedesEncontradas.add(new SedeIpsVO(sedeIps));
				}
				validatedResponse.setContent(sedesEncontradas);
			}

			return validatedResponse;
		} catch (Throwable e) {
			LOGGER.error("Error buscando sedes Ips", e);
			throw e;
		}
	}
	
	@RequestMapping(value = "/solicitudParcial")
	protected @ResponseBody
	ValidatedResponse<List<SolicitudParcialVO>> buscarSolicitudParcial(@ModelAttribute SearchSolicitudParcialForm form, ModelMap map) {
		
		SaludCoopUserDetails userDetails = (SaludCoopUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userManager.loadUserByUsername(userDetails.getUsername());
				
		ValidatedResponse<List<SolicitudParcialVO>> validatedResponse = new ValidatedResponse<>();
		List<SolicitudParcialVO> solParcialVOs = new Vector<>();
		List<SolicitudParcialDto> solParcialDtos = solicitudParcialManager.getSolicitudesParciales(form, user);

		if (solParcialDtos == null || solParcialDtos.isEmpty()) {
			validatedResponse.addGeneralError("No se encontraron resultados");
		} else {
			for (SolicitudParcialDto solParcialDto : solParcialDtos) {
				solParcialVOs.add(new SolicitudParcialVO(solParcialDto));
			}
			validatedResponse.setContent(solParcialVOs);
		}

		return validatedResponse;

	}
	
	@RequestMapping(value = "/ciudadesByDept")
    protected @ResponseBody
    ValidatedResponse<List<MunicipioVO>> buscarCiudadesByDepartamento(Long departamentoId, ModelMap map) {
                        
        ValidatedResponse<List<MunicipioVO>> validatedResponse = new ValidatedResponse<>();
        
        List<MunicipioVO> municipioVOs = new Vector<>();
        List<MunicipioDto> municipioDtos = municipioManager.findByDepartamentoId(departamentoId);

        if (municipioDtos == null || municipioDtos.isEmpty()) {
            validatedResponse.addGeneralError("No se encontraron resultados");
        } else {
            for (MunicipioDto municipioDto : municipioDtos) {
                municipioVOs.add(new MunicipioVO(municipioDto));
            }
            validatedResponse.setContent(municipioVOs);
        }

        return validatedResponse;

    }
	
	//
	@RequestMapping(value = "/especialidades")
	protected @ResponseBody
	ValidatedResponse<List<EspecialidadVO>> buscarEspecialidades(@ModelAttribute SeachCodigoDescripcionForm form, ModelMap map) {

		ValidatedResponse<List<EspecialidadVO>> validatedResponse = new ValidatedResponse<>();

		List<EspecialidadDto> especialidadesDto = especialidadManager.getEspecialidadByCodigoDescripcion(form.getCodigo(), form.getDescripcion());

		if (especialidadesDto == null || especialidadesDto.isEmpty()) {
			validatedResponse.addGeneralError("No se encontraron resultados");
		} else {

			List<EspecialidadVO> esvos = new ArrayList<EspecialidadVO>();
			for (EspecialidadDto m : especialidadesDto) {
				esvos.add(new EspecialidadVO(m));
			}

			validatedResponse.setContent(esvos);
		}

		return validatedResponse;

	}
	
	@RequestMapping(value = "/sedeById/{id}", method = RequestMethod.GET)
	protected @ResponseBody
	ValidatedResponse<SedeIpsVO> buscarSedePorId(@PathVariable("id") Long id, ModelMap map) {

		ValidatedResponse<SedeIpsVO> validatedResponse = new ValidatedResponse<>();
		SedeIpsDto sede = sedeManager.findOne(id);		
		if (sede == null) {
			validatedResponse.addGeneralError("No se encontraron resultados");
		} else {
			validatedResponse.setContent(new SedeIpsVO(sede));
		}
		return validatedResponse;
	}
	
	@RequestMapping(value = "/profesionalById/{id}", method = RequestMethod.GET)
	protected @ResponseBody
	ValidatedResponse<ProfesionalVO> buscarProfesionalPorId(@PathVariable("id") Long id, ModelMap map) {

		ValidatedResponse<ProfesionalVO> validatedResponse = new ValidatedResponse<>();
		ProfesionalEspecialidadDto prof = profesionalManager.findOne(id);		
		if (prof == null) {
			validatedResponse.addGeneralError("No se encontraron resultados");
		} else {
			validatedResponse.setContent(new ProfesionalVO(prof));
		}
		return validatedResponse;
	}
	
	@RequestMapping(value = "/procedimientoById/{id}", method = RequestMethod.GET)
	protected @ResponseBody
	ValidatedResponse<ProcedimientoMedicamentoVO> buscarProcedimientoPorId(@PathVariable("id") Long id, ModelMap map) {

		ValidatedResponse<ProcedimientoMedicamentoVO> validatedResponse = new ValidatedResponse<>();
		ProcedimientoDto prest = prestManager.findById(id);		
		if (prest == null) {
			validatedResponse.addGeneralError("No se encontraron resultados");
		} else {
			validatedResponse.setContent(new ProcedimientoMedicamentoVO(prest));
		}
		return validatedResponse;
	}

	
}
