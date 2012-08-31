package org.util;

import java.util.List;

public class Nodo {
	private String tipo;
	private String value;
	private String name;
	private Nodo padre;
	private List<Nodo> childs;
	public Nodo getPadre() {
		return padre;
	}
	public void setPadre(Nodo padre) {
		this.padre = padre;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public List<Nodo> getChilds() {
		return childs;
	}
	public void setChilds(List<Nodo> childs) {
		this.childs = childs;
	}

}
