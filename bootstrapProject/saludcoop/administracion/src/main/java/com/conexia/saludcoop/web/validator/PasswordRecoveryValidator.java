package com.conexia.saludcoop.web.validator;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import nl.captcha.Captcha;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

@Component
public class PasswordRecoveryValidator  extends AbstractValidator{

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
		String inputCaptcha = (String) bean.getPropertyValue("jcaptchaText");
		if (StringUtils.isEmpty(inputCaptcha)){
			br.rejectValue("jcaptchaText","",getMessage("validation.required", getMessage("label.jcaptcha")));
		}else{
			Captcha captcha = (Captcha) request.getSession().getAttribute(Captcha.NAME);
		    try {
				request.setCharacterEncoding("UTF-8");
				if (!captcha.isCorrect(inputCaptcha)) {
			    	br.rejectValue("jcaptchaText","",getMessage("validation.captcha.missmatch"));
			    }
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // Do this so we can capture non-Latin chars
		    

		}
	}
}
