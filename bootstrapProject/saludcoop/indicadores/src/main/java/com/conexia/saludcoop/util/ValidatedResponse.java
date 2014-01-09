package com.conexia.saludcoop.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase que envuelve una respuesta a una petición a servidor junto con mensajes de
 * validaci&oacute;n. El contenido de la respuesta está parametrizado.
 * 
 * @author gchiesa
 * 
 * @param <T> la respuesta del servidor.
 */
public class ValidatedResponse<T> implements IValidationResultsContainer {

	/**
	 * Mapa de los mensajes de validación donde la clave es el nombre de una property a validar y el
	 * valor es el mensaje de validaci&oacute;n asociado.
	 */
	private Map<String, String> validationResult;

	/**
	 * Mapa de errores generales.
	 */
	private List<String> generalErrors;

	/**
	 * Contenido de la respuesta.
	 */
	private T content;

	/**
	 * Constructor por default.
	 */
	public ValidatedResponse() {

		this.validationResult = new HashMap<String, String>();
		this.generalErrors = new ArrayList<String>();
	}

	/**
	 * Devuelve el valor de validationResult.
	 * 
	 * @return El valor de validationResult.
	 */
	public final Map<String, String> getValidationResult() {

		return (Collections.unmodifiableMap(this.validationResult));
	}

	/**
	 * Devuelve el valor de content.
	 * 
	 * @return El valor de content.
	 */
	public final T getContent() {

		return this.content;
	}

	/**
	 * Asigna un nuevo valor a content.
	 * 
	 * @param content El valor a asignar a content.
	 */
	public final void setContent(final T content) {

		this.content = content;
	}

	/**
	 * Devuelve el valor de generalErrors.
	 * 
	 * @return El valor de generalErrors.
	 */
	public final List<String> getGeneralErrors() {

		return (Collections.unmodifiableList(this.generalErrors));
	}

	/**
	 * Agrega un error general.
	 * 
	 * @param errorMessage el mensaje de error
	 */
	public final void addGeneralErrors(final String errorMessage) {

		this.generalErrors.add(errorMessage);
	}

	@Override
	public final void addFieldValidationMessage(final String object, final String message) {

		if (this.getValidationResult().containsKey(object)) {
			String newMessage = this.getValidationResult().get(object);

			newMessage += " " + message;

			this.validationResult.put(object, newMessage);
		} else {
			this.validationResult.put(object, message);
		}
	}

	@Override
    public final void addGeneralValidationMessage(final String message) {

		this.generalErrors.add(message);
	}
	
	@Override
	public final boolean containsErrors() {
		
		return (!this.generalErrors.isEmpty() || !this.getValidationResult().isEmpty());
	}

	
    /**
     * Asigna un nuevo valor a validationResult.
     *
     * @param validationResult El valor a asignar a validationResult.
     */
    public void setValidationResult(Map<String, String> validationResult) {
    
    	this.validationResult = validationResult;
    }

	
    /**
     * Asigna un nuevo valor a generalErrors.
     *
     * @param generalErrors El valor a asignar a generalErrors.
     */
    public void setGeneralErrors(List<String> generalErrors) {
    
    	this.generalErrors = generalErrors;
    }
	
    /**
     * Agrega un String a la lista de generalErrors.
     *
     * @param generalErrorString El valor a agregar a generalErrors.
     */
    public void addGeneralError(String generalErrorString) {
    	this.generalErrors.add(generalErrorString);
    }
	
}
