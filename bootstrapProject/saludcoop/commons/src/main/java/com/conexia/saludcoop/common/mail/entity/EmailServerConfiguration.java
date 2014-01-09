package com.conexia.saludcoop.common.mail.entity;

/**
 * Configuración de servidor de Email.
 * 
 * @author Sebastián Matienzo
 */
public class EmailServerConfiguration {

	/**
	 * Usuario de la conexión.
	 */
	private String username;

	/**
	 * Clave de la conexión.
	 */
	private String password;

	/**
	 * Dirección IP del servidor.
	 */
	private String hostAddress;

	/**
	 * Puerto del servidor.
	 */
	private Integer port;

	/**
	 * Puerto del servidor.
	 */
	private boolean sslRequired;

	/**
	 * Devuelve el valor de username.
	 * 
	 * @return El valor de username.
	 */
	public final String getUsername() {

		return (this.username);
	}

	/**
	 * Asigna un nuevo valor a username.
	 * 
	 * @param username El valor a asignar a username.
	 */
	public final void setUsername(final String username) {

		this.username = username;
	}

	/**
	 * Devuelve el valor de password.
	 * 
	 * @return El valor de password.
	 */
	public final String getPassword() {

		return (this.password);
	}

	/**
	 * Asigna un nuevo valor a password.
	 * 
	 * @param password El valor a asignar a password.
	 */
	public final void setPassword(final String password) {

		this.password = password;
	}

	/**
	 * Devuelve el valor de hostAddress.
	 * 
	 * @return El valor de hostAddress.
	 */
	public final String getHostAddress() {

		return (this.hostAddress);
	}

	/**
	 * Asigna un nuevo valor a hostAddress.
	 * 
	 * @param hostAddress El valor a asignar a hostAddress.
	 */
	public final void setHostAddress(final String hostAddress) {

		this.hostAddress = hostAddress;
	}

	/**
	 * Devuelve el valor de port.
	 * 
	 * @return El valor de port.
	 */
	public final Integer getPort() {

		return (this.port);
	}

	/**
	 * Asigna un nuevo valor a port.
	 * 
	 * @param port El valor a asignar a port.
	 */
	public final void setPort(final Integer port) {

		this.port = port;
	}

	/**
	 * Devuelve el valor de sslRequired.
	 * 
	 * @return El valor de sslRequired.
	 */
	public final boolean isSslRequired() {

		return (this.sslRequired);
	}

	/**
	 * Asigna un nuevo valor a sslRequired.
	 * 
	 * @param sslRequired El valor a asignar a sslRequired.
	 */
	public final void setSslRequired(final boolean sslRequired) {

		this.sslRequired = sslRequired;
	}
}
