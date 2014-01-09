/**
 * 
 */
package com.conexia.saludcoop.web.form;

/**
 * @author nobregon
 *
 */
public class IdentificacionForm {

	private String tipoDocumento;
	private String numeroDocumento;
	private String target;
	/**
	 * Devuelve el valor de tipoDocumento.
	 *
	 * @return El valor de tipoDocumento.
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	/**
	 * Asigna un nuevo valor a tipoDocumento.
	 *
	 * @param tipoDocumento El valor a asignar a tipoDocumento.
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	/**
	 * Devuelve el valor de numeroDocumento.
	 *
	 * @return El valor de numeroDocumento.
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	/**
	 * Asigna un nuevo valor a numeroDocumento.
	 *
	 * @param numeroDocumento El valor a asignar a numeroDocumento.
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	
}
