/**
 * 
 */
package com.conexia.saludcoop.web.controller.prestador;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.conexia.saludcoop.web.BaseValidatingController;
import com.conexia.saludcoop.web.form.ActualizarDatosBeneficiarioForm;
import com.conexia.saludcoop.web.manager.IAfiliadoManager;
import com.conexia.saludcoop.web.validator.ActualizacionDatosBeneficiarioValidator;

/**
 * @author nobregon
 *
 */
@Controller
public class ActualizacionDatosBeneficiarioController extends BaseValidatingController{
	
	@Autowired 
	private IAfiliadoManager beneficiarioManager;
	
	@Autowired
	private ActualizacionDatosBeneficiarioValidator actDatosBeneValidator;
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(getActDatosBeneValidator());
	}
	
	@RequestMapping(value="",method = RequestMethod.POST)
	public String actualizarDatos(
			@ModelAttribute @Valid ActualizarDatosBeneficiarioForm form,
			@ModelAttribute BindingResult bindingResult,
			Model model){
		
//		BeneficiarioDto beneficiarioDto = (BeneficiarioDto)model.asMap().get("beneficiarioTrx");
		
//		getBeneficiarioManager().actualizarDatosBeneficiario(beneficiarioDto);
		
		
		//TODO: Enviar datos no actualizables a esuqema de novedades
		
		return "";
		
	}
	

	/**
	 * Devuelve el valor de beneficiarioManager.
	 *
	 * @return El valor de beneficiarioManager.
	 */
	public IAfiliadoManager getBeneficiarioManager() {
		return beneficiarioManager;
	}

	/**
	 * Asigna un nuevo valor a beneficiarioManager.
	 *
	 * @param beneficiarioManager El valor a asignar a beneficiarioManager.
	 */
	public void setBeneficiarioManager(IAfiliadoManager beneficiarioManager) {
		this.beneficiarioManager = beneficiarioManager;
	}


	/**
	 * Devuelve el valor de actDatosBeneValidator.
	 *
	 * @return El valor de actDatosBeneValidator.
	 */
	public ActualizacionDatosBeneficiarioValidator getActDatosBeneValidator() {
		return actDatosBeneValidator;
	}


	/**
	 * Asigna un nuevo valor a actDatosBeneValidator.
	 *
	 * @param actDatosBeneValidator El valor a asignar a actDatosBeneValidator.
	 */
	public void setActDatosBeneValidator(
			ActualizacionDatosBeneficiarioValidator actDatosBeneValidator) {
		this.actDatosBeneValidator = actDatosBeneValidator;
	}
	
	
	

}
