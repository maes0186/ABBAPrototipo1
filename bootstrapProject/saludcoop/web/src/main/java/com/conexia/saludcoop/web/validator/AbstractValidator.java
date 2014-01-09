/**
 * 
 */
package com.conexia.saludcoop.web.validator;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * @author gchiesa
 * 
 */
public abstract class AbstractValidator extends LocalValidatorFactoryBean implements Validator {

	@Autowired
	private MessageSource messageSource;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		super.validate(target, errors);
		this.validateBean(target, errors);
	}

	protected String getMessage(String key) {
		return this.messageSource.getMessage(key ,null, Locale.getDefault());
	}
	protected String getMessage(String key, Object... args) {
		return this.messageSource.getMessage(key ,args, Locale.getDefault());
	}
	
	protected String getMessage(String key, Locale locale) {
		return this.messageSource.getMessage(key ,null, locale);
	}
	
	protected String getMessage(String key, Locale locale, Object... args) {
		return this.messageSource.getMessage(key ,args, locale);
	}
	
	protected abstract void validateBean(Object target, Errors errors);

}