package com.conexia.saludcoop.web.form;

public class PasswordRecoveryForm {
	private String nombre;
	private String mail;
	private String recaptcha_response_field;
	

	public String getRecaptcha_response_field() {
		return recaptcha_response_field;
	}
	public void setRecaptcha_response_field(String recaptcha_response_field) {
		this.recaptcha_response_field = recaptcha_response_field;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}

}
