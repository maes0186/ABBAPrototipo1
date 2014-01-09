package com.conexia.saludcoop.web.form;

public class FormError {
	
	private String field;
	private String errorMessage;
	private String[] arguments;
	
	public FormError() {
		// TODO Auto-generated constructor stub
	}

	public FormError(String field, String errorMessage) {
		this.field = field;
		this.errorMessage = errorMessage;
	}
	
	public FormError(String field, String errorMessage, String[] arguments) {
		this.field = field;
		this.errorMessage = errorMessage;
		this.arguments = arguments;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String[] getArguments() {
		return arguments;
	}

	public void setArguments(String[] arguments) {
		this.arguments = arguments;
	}
	

}
