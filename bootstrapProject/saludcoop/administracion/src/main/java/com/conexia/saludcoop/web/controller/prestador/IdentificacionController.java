/**
 * 
 */
package com.conexia.saludcoop.web.controller.prestador;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
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
import com.conexia.saludcoop.common.dto.TipoIdentificacionDto;
import com.conexia.saludcoop.util.ValidatedResponse;
import com.conexia.saludcoop.web.BaseValidatingController;
import com.conexia.saludcoop.web.form.IdentificacionForm;
import com.conexia.saludcoop.web.manager.AfiliadoManager;
import com.conexia.saludcoop.web.manager.TipoIdentificacionAfiliadoManager;
import com.conexia.saludcoop.web.manager.ValidatorServiceManager;
import com.conexia.saludcoop.web.validator.IdentificacionValidator;
import com.conexia.saludcoop.web.vo.AfiliadoVO;
import com.conexia.saludcoop.web.vo.TipoIdentificacionVO;
import com.conexia.saludcoop.web.vo.utils.ParserVO;

/**
 * @author nobregon
 * 
 */
@Controller
@SessionAttributes({ "beneList", "beneficiarioTrx", "afiliado" })
@RequestMapping(method = RequestMethod.GET)
public class IdentificacionController extends BaseValidatingController {

	@Autowired
	private IdentificacionValidator identificacionValidator;

	@Autowired
	private AfiliadoManager afiliadoManager;

	@Autowired
	private ValidatorServiceManager validatorServiceManager;

	@Autowired
	private ParserVO pvo;

	@Autowired
	private TipoIdentificacionAfiliadoManager tipoIdentAfiliadoManager;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(getIdentificacionValidator());
		
		
	}

	@RequestMapping(value = "/prestador/comprobacionDerechos")
	protected String iniciarComprobacion(@ModelAttribute String target,
			ModelMap map) {

		Vector<TipoIdentificacionVO> tiposDeDocumento = new Vector<TipoIdentificacionVO>();
		for (TipoIdentificacionDto td : tipoIdentAfiliadoManager.getAll()) {
			tiposDeDocumento.add(new TipoIdentificacionVO(td));
		}
		map.put("tiposDeDocumento", tiposDeDocumento);
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
	protected ValidatedResponse<AfiliadoVO> identificar(
			@ModelAttribute @Valid IdentificacionForm form,
			BindingResult bindingResult, ModelMap model) throws Exception {

		ValidatedResponse<AfiliadoVO> validatedResponse = new ValidatedResponse<AfiliadoVO>();
		if (!bindingResult.hasErrors()) {
			AfiliadoDto afiliadoDto = getAfiliadoManager()
					.getBeneficiarioByTipoNumeroDocumento(
							Integer.valueOf(form.getTipoDocumento()),
							form.getNumeroDocumento());

			validatorServiceManager.comprobarDerechos(afiliadoDto);

			AfiliadoVO afiliado = pvo.getAfiliadoVO(afiliadoDto);
			model.put("afiliado", afiliado);
			validatedResponse.setContent(afiliado);

		} else {
			validatedResponse
					.setValidationResult(getFieldErrorsStringMap(bindingResult));
			validatedResponse
					.setGeneralErrors(getGlobalErrorsList(bindingResult));
		}

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

	public AfiliadoManager getAfiliadoManager() {
		return afiliadoManager;
	}

	public void setAfiliadoManager(AfiliadoManager afiliadoManager) {
		this.afiliadoManager = afiliadoManager;
	}

}
