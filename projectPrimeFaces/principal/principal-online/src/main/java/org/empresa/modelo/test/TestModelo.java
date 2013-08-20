package org.empresa.modelo.test;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
@ManagedBean
@SessionScoped
public class TestModelo implements Serializable {

	/**
	 * 
	 */
	private String texto;
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	private static final long serialVersionUID = -978136587888727027L;

}
