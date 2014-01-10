package com.conexia.saludcoop.web.form.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.conexia.saludcoop.web.form.IdentificacionForm;

public class IdentificacionFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return IdentificacionForm.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		IdentificacionForm iForm = (IdentificacionForm)target;
		if(iForm.getTipoIdentificacion() == null){
			
			if(isNullOrEmpty(iForm.getNombres()) && isNullOrEmpty(iForm.getApellidos())){
				errors.reject("validation.required.al.menos.uno", "Complete al menos un campo de filtro");
			}
		}else{
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numeroIdentificacion",
					"validation.required ","Número Identificación");
		}
	}

	private boolean isNullOrEmpty(String nombres) {
		return nombres == null || nombres.isEmpty();
	}

}
