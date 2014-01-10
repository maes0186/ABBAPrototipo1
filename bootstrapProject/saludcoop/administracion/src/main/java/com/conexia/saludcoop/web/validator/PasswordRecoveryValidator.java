package com.conexia.saludcoop.web.validator;

import javax.servlet.http.HttpServletRequest;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

@Component
public class PasswordRecoveryValidator  extends AbstractValidator{

	@Autowired
	private ReCaptcha reCaptchaService = null;
	
	@Override
	protected void validateBean(Object target, Errors errors) {
		BeanWrapper bean = PropertyAccessorFactory.forBeanPropertyAccess(target);
		String usuario = (String) bean.getPropertyValue("nombre");
	    if (StringUtils.isEmpty(usuario)) {
	    	errors.rejectValue("nombre","",getMessage("validation.required", getMessage("label.user")));
		}
	}
	
	
	/**
	 * Valida que el captcha ingresado coincida con el último que se generó para la session
	 * Se llama desde PasswordRecoveryController
	 * @param request Es el HttpServletRequest para obtener la session id
	 * @param target El formulario que contiene el captcha
	 * @param errors La lista de errores
	 */
	
	public void validateCaptcha(HttpServletRequest request, Object target, BindingResult br){
		BeanWrapper bean = PropertyAccessorFactory.forBeanPropertyAccess(target);
		String inputCaptcha = (String) bean.getPropertyValue("recaptcha_response_field");
		if (StringUtils.isEmpty(inputCaptcha)){
			br.rejectValue("recaptcha_response_field","",getMessage("validation.required", getMessage("label.jcaptcha")));
		}else{
			String challenge = request.getParameter("recaptcha_challenge_field");
		    String remoteAddr = request.getRemoteAddr();
		    ReCaptchaResponse reCaptchaResponse = reCaptchaService.checkAnswer(remoteAddr, challenge, inputCaptcha);
		    if(!reCaptchaResponse.isValid()) {
		    	br.rejectValue("recaptcha_response_field","",getMessage("validation.captcha.missmatch"));
		    }

		}
	}
}
