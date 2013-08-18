package org.empresa.modelo;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
@ManagedBean(name="negocio")
public class Negocio1Modelo implements Serializable {

	/**
	 * 
	 */
	private String texto;
	public String getTexto() {
		return "ejemplo";
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	private static final long serialVersionUID = -978136587888727027L;

}
