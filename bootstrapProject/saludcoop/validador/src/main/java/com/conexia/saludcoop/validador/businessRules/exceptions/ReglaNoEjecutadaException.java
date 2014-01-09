package com.conexia.saludcoop.validador.businessRules.exceptions;

public class ReglaNoEjecutadaException extends Exception {

	public ReglaNoEjecutadaException(Exception e) {
		super.setStackTrace(e.getStackTrace());
	}

}
