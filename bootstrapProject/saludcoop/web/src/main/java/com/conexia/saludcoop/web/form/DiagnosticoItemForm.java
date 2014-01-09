package com.conexia.saludcoop.web.form;



public class DiagnosticoItemForm {
	
	private Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	private String codigo;
	private String descripcion;
	private boolean esPrincipal;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public boolean isEsPrincipal() {
		return esPrincipal;
	}
	public void setEsPrincipal(boolean esPrincipal) {
		this.esPrincipal = esPrincipal;
	}
	

	
}
