package com.conexia.saludcoop.web.form;

public class PasswordRecoveryForm {
	private String nombre;
	private String mail;
	private String jcaptchaText;
	
	public String getJcaptchaText() {
		return jcaptchaText;
	}
	public void setJcaptchaText(String jcaptchaText) {
		this.jcaptchaText = jcaptchaText;
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
