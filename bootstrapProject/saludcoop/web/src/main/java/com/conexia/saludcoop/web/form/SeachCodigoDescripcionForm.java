package com.conexia.saludcoop.web.form;

public class SeachCodigoDescripcionForm {

	private String codigo;
	private String descripcion;
	// Se agrega para validar las busquedas de medicamentos Comerciales o no 
	private boolean comercial;
	
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
	
    public boolean isComercial() {
        return comercial;
    }
    public void setComercial(boolean comercial) {
        this.comercial = comercial;
    }
	
}
