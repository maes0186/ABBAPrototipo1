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
public class IdentificacionValidator extends AbstractValidator{
	@Override
	protected void validateBean(Object target, Errors errors) {
		BeanWrapper bean = PropertyAccessorFactory.forBeanPropertyAccess(target);
		
		String tipo = (String) bean.getPropertyValue("tipoDocumento");
		String nro = (String) bean.getPropertyValue("numeroDocumento");
		
		if ((StringUtils.isEmpty(tipo) || StringUtils.isEmpty(nro) )) {
	    	errors.rejectValue("tipoYNumero","",getMessage("validation.required", getMessage("label.id.type")+" "+getMessage("label.and")+" "+getMessage("label.id.number")));
		}
	}
}
