package com.conexia.saludcoop.spring.editor;

import java.text.DateFormat;

import org.springframework.beans.propertyeditors.CustomDateEditor;

public class SaludCoopCustomDateEditor extends CustomDateEditor {

	public SaludCoopCustomDateEditor(DateFormat dateFormat, boolean allowEmpty, int exactDateLength) {
		super(dateFormat, allowEmpty, exactDateLength);
	}

	public SaludCoopCustomDateEditor(DateFormat dateFormat, boolean allowEmpty) {
		super(dateFormat, allowEmpty);
	}

	

}
