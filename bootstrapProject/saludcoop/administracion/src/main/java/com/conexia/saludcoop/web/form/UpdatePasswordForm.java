/**
 * 
 */
package com.conexia.saludcoop.web.form;

/**
 * @author nobregon
 *
 */
public class UpdatePasswordForm {
	private String password;
	private String repeatedPassword;
	/**
	 * Devuelve el valor de password.
	 *
	 * @return El valor de password.
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Asigna un nuevo valor a password.
	 *
	 * @param password El valor a asignar a password.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Devuelve el valor de repeatedPassword.
	 *
	 * @return El valor de repeatedPassword.
	 */
	public String getRepeatedPassword() {
		return repeatedPassword;
	}
	/**
	 * Asigna un nuevo valor a repeatedPassword.
	 *
	 * @param repeatedPassword El valor a asignar a repeatedPassword.
	 */
	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}

	
}
