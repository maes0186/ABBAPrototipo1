/**
 * 
 */
package com.conexia.saludcoop.web.controller.prestador;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.conexia.saludcoop.common.dto.AfiliadoDto;
import com.conexia.saludcoop.common.dto.MunicipioDto;
import com.conexia.saludcoop.common.dto.RespuestaDto;
import com.conexia.saludcoop.util.ValidatedResponse;
import com.conexia.saludcoop.web.BaseValidatingController;
import com.conexia.saludcoop.web.form.DatosAfiliadoForm;
import com.conexia.saludcoop.web.form.IdentificacionForm;
import com.conexia.saludcoop.web.manager.EpsManager;
import com.conexia.saludcoop.web.manager.DepartamentoManager;
import com.conexia.saludcoop.web.manager.IAfiliadoManager;
import com.conexia.saludcoop.web.manager.TipoIdentificacionManager;
import com.conexia.saludcoop.web.manager.ValidatorServiceManager;
import com.conexia.saludcoop.web.validator.IdentificacionValidator;
import com.conexia.saludcoop.web.vo.AfiliadoVO;
import com.conexia.saludcoop.web.vo.VOUtils;
import com.conexia.saludcoop.web.vo.utils.ParserVO;

/**
 * @author nobregon
 * 
 */
@Controller
@SessionAttributes({ "beneList", "beneficiarioTrx", "afiliado","sedeIPs" })
@RequestMapping(method = RequestMethod.GET)
public class IdentificacionController extends BaseValidatingController {

	@Autowired
	private IdentificacionValidator identificacionValidator;

	@Autowired
	private IAfiliadoManager afiliadoManager;

	@Autowired
	private ValidatorServiceManager validatorServiceManager;

	@Autowired
	private ParserVO pvo;

    @Autowired
    private DepartamentoManager departamentoManager;
	
	@Autowired
	private TipoIdentificacionManager tipoIdentificacionManager;
	
	@Autowired
    private EpsManager epsManager;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(getIdentificacionValidator());
		
		
	}

	@RequestMapping(value = "/prestador/comprobacionDerechos")
	protected String iniciarComprobacion(@ModelAttribute String target,
			ModelMap map) {

		cargarElementosTipoIdentificacion("tipoIdentificacion", map, tipoIdentificacionManager.getAll());
		map.put("eps", VOUtils.toEpsVO(epsManager.findAll()));
		map.put("departamentos", departamentoManager.findAll());
		map.put("target", target);
		return "prestador/common/identificacion";
	}
	
	@RequestMapping(value = "/prestador/comprobacionDerechos/{target}", method = RequestMethod.POST)
	protected ModelAndView comprobarDerechos(
			@ModelAttribute IdentificacionForm afiliadoForm, ModelMap map) {

		ModelAndView mv = new ModelAndView();
		if (afiliadoForm.getTarget().equals(SolicitudController.SOLICITUD)) {
			mv.setViewName("redirect:/prestador/solicitud");

		} else {
			mv.setViewName("redirect:/prestador/comprobacionDerechos");
		}
		return mv;

	}


	@RequestMapping(value = "/prestador/identificacion", method = RequestMethod.POST)
	@ResponseBody
	protected ValidatedResponse<AfiliadoVO> comprobarDerechos(@ModelAttribute AfiliadoVO afiliadoVo, ModelMap model, HttpSession session) throws Exception {
			ValidatedResponse<AfiliadoVO> validatedResponse = new ValidatedResponse<>();
		removeFromSession(model, session, "afiliado");
		
		AfiliadoDto afiliadoDto = afiliadoManager.getAfiliadoByTipoNumeroDocumento(afiliadoVo.getTipoIdentID(), afiliadoVo.getNumeroIdentificacion());
		RespuestaDto respuesta = validatorServiceManager.comprobarDerechos(afiliadoDto);
		Integer codigoRespuesta = new Integer(getMessage("respuestaTrx.codigoOk"));
			
		if(respuesta != null && respuesta.getCodigoRespuesta() == codigoRespuesta){
			model.put("afiliado", afiliadoVo);
			validatedResponse.setContent(afiliadoVo);

		} else {
			List<String> errores = new Vector<>();
			if(respuesta == null){
				errores.add("Error - No se pudo comunicar con el validador");
			}else{
				errores.add(respuesta.getMensajeRespuesta());	
			}
			
			validatedResponse.setGeneralErrors(errores);
			
		}

		return validatedResponse;

	}
	
	@RequestMapping(value = "/prestador/getAfiliado", method = RequestMethod.POST)
	@ResponseBody
	protected ValidatedResponse<AfiliadoVO> getAfiliado(ModelMap model) throws Exception {
		ValidatedResponse<AfiliadoVO> validatedResponse = new ValidatedResponse<>();
		AfiliadoVO afiliadoVo = (AfiliadoVO)model.get("afiliado");
		
		validatedResponse.setContent(afiliadoVo);

		return validatedResponse;

	}
	
	@RequestMapping(value = "/afiliado/guardarDatosContacto", method = RequestMethod.POST)
    @ResponseBody
    protected ValidatedResponse<Boolean> saveDatosContacto(ModelMap model, DatosAfiliadoForm form) throws Exception {
        ValidatedResponse<Boolean> validatedResponse = new ValidatedResponse<>();
        
        AfiliadoDto afiliadoDto = new AfiliadoDto();
        afiliadoDto.setId(form.getId());
        afiliadoDto.setMunicipioResidencia(new MunicipioDto());
        afiliadoDto.getMunicipioResidencia().setId(form.getMunicipioId());
        afiliadoDto.setDireccionDeResidencia(form.getDireccionResidencial());
        afiliadoDto.setEmailPersonal(form.getEmailPersonal());
        afiliadoDto.setTelefonoCelular(form.getTelefonoCelular());
        afiliadoDto.setTelefonoResidencial(form.getTelefonoResidencial());
        
        RespuestaDto respuesta = validatorServiceManager.actualizarDatosContactoAfiliado(afiliadoDto);
        
        if (validatedResponse != null) {
            validatedResponse.setContent(respuesta.getCodigoRespuesta() == 0 ? Boolean.TRUE : Boolean.FALSE);
            validatedResponse.addGeneralError(respuesta.getMensajeRespuesta());
        }
        
        AfiliadoVO vo = (AfiliadoVO) model.get("afiliado");
        vo.setMunicipioId(form.getMunicipioId());
        vo.setDepartamentoId(form.getDepartamentoId());
        vo.setDireccionResidencial(form.getDireccionResidencial());
        vo.setEmailPersonal(form.getEmailPersonal());
        vo.setTelefonoCelular(form.getTelefonoCelular());
        vo.setTelefonoResidencial(form.getTelefonoResidencial());
        
        return validatedResponse;

    }

	/**
	 */
	@RequestMapping(value = "/prestador/identificacion/{nombre}")
	@ResponseBody
	protected ValidatedResponse<List<AfiliadoDto>> buscarBeneficiariosPorNombre(
			@PathVariable("nombre") String nombre, Model model)
			throws Exception {

		ValidatedResponse<List<AfiliadoDto>> validatedResponse = new ValidatedResponse<List<AfiliadoDto>>();

		if (!StringUtils.isEmpty(nombre)) {
			List<AfiliadoDto> benDtos = new ArrayList<AfiliadoDto>();

			if (!CollectionUtils.isEmpty(benDtos)) {
				model.addAttribute("beneList", benDtos);
			} else {
				validatedResponse.addGeneralErrors(this
						.getMessage("message.noResults"));
			}
		} else {
			List<String> errors = new ArrayList<String>(1);
			errors.add(getMessage("messages.required",
					getMessage("label.name.lasts") + " "
							+ getMessage("label.andor") + " "
							+ getMessage("label.names")));
			validatedResponse.setGeneralErrors(errors);
		}
		return validatedResponse;
	}
	
	@RequestMapping(value="/removeAfiliadoFromSession", method=RequestMethod.GET)
	@ResponseBody
	public void resetAfiliadoFromSession(ModelMap model, HttpSession session){
		removeFromSession(model, session, "afiliado");	
	}

	/**
	 * Devuelve el valor de identificacionValidator.
	 * 
	 * @return El valor de identificacionValidator.
	 */
	public IdentificacionValidator getIdentificacionValidator() {
		return identificacionValidator;
	}

	/**
	 * Asigna un nuevo valor a identificacionValidator.
	 * 
	 * @param identificacionValidator
	 *            El valor a asignar a identificacionValidator.
	 */
	public void setIdentificacionValidator(
			IdentificacionValidator identificacionValidator) {
		this.identificacionValidator = identificacionValidator;
	}

	public IAfiliadoManager getAfiliadoManager() {
		return afiliadoManager;
	}

	public void setAfiliadoManager(IAfiliadoManager afiliadoManager) {
		this.afiliadoManager = afiliadoManager;
	}

}
