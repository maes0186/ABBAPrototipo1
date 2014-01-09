package com.conexia.saludcoop.web.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class RecuperaContraseniaValidator  extends AbstractValidator{

	@Override
	protected void validateBean(Object target, Errors errors) {
		BeanWrapper bean = PropertyAccessorFactory.forBeanPropertyAccess(target);
		String usuario = (String) bean.getPropertyValue("nombre");
	    if (StringUtils.isEmpty(usuario)) {
			errors.rejectValue("nombre","",getMessage("validation.required", getMessage("label.user")));
		}
	}

}
