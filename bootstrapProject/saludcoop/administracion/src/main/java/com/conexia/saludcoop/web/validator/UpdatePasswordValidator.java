/**
 * 
 */
package com.conexia.saludcoop.web.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;


/**
 * @author nobregon
 *
 */
@Component
public class UpdatePasswordValidator extends AbstractValidator{

	/**
	 * Valida que las contrasenias ingresadas sean iguales 
	 */
	@Override
	protected void validateBean(Object target, Errors errors) {
		BeanWrapper bean = PropertyAccessorFactory.forBeanPropertyAccess(target);
		String password = (String) bean.getPropertyValue("password");
		String repeatedPassword = (String) bean.getPropertyValue("repeatedPassword");
	    if (StringUtils.isEmpty(password)) {
	    	errors.rejectValue("password","",getMessage("validation.required", getMessage("label.password")));
		}
	    if (StringUtils.isEmpty(repeatedPassword)) {
	    	errors.rejectValue("repeatedPassword","",getMessage("validation.required", getMessage("label.repeatedPassword")));
		}
	    if (!StringUtils.equals(password, repeatedPassword)){
	    	errors.rejectValue("repeatedPassword","",getMessage("validation.passwords.missmatch"));
	    }
	}

}
