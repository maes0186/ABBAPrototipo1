package com.conexia.saludcoop.common.exception;

/**
 * Excepción lanzada desde las capas de servicios (Services).
 * 
 * @author Sebastián Matienzo
 */
public class ServiceException extends Exception {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 16436326236521L;

	/**
	 * Constructor por defecto.
	 */
	public ServiceException() {

		super();
	}

	/**
	 * Constructor con causa de excepción.
	 * 
	 * @param e Causa de excepción.
	 */
	public ServiceException(final Throwable e) {

		super(e);
	}

	/**
	 * Constructor con mensaje personalizado.
	 * 
	 * @param message Mensaje personalizado.
	 */
	public ServiceException(final String message) {

		super(message);
	}

	/**
	 * Constructor con causa de excepción y mensaje personalizado.
	 * 
	 * @param message Mensaje personalizado.
	 * @param cause Causa.
	 */
	public ServiceException(final String message, final Throwable cause) {

		super(message, cause);
	}
}
