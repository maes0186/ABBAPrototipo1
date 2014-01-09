package com.conexia.saludcoop.common.mail.exception;

import com.conexia.saludcoop.common.exception.ServiceException;

/**
 * No se puede enviar un Email.
 * 
 * @author Sebastián Matienzo
 */
public class CannotSendEmailException extends ServiceException {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 5346346126L;

	/**
	 * Constructor por defecto.
	 */
	public CannotSendEmailException() {

		super();
	}

	/**
	 * Constructor con causa de excepción.
	 * 
	 * @param e Causa de excepción.
	 */
	public CannotSendEmailException(final Throwable e) {

		super(e);
	}

	/**
	 * Constructor con mensaje personalizado.
	 * 
	 * @param message Mensaje personalizado.
	 */
	public CannotSendEmailException(final String message) {

		super(message);
	}

	/**
	 * Constructor con causa de excepción y mensaje personalizado.
	 * 
	 * @param message Mensaje personalizado.
	 * @param cause Causa.
	 */
	public CannotSendEmailException(final String message, final Throwable cause) {

		super(message, cause);
	}
}
