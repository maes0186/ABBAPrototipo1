package com.conexia.saludcoop.web.form;

public class PrescripcionInsumoForm {

	private Integer causaExterna;
	private Integer finalidad;
	private Integer tipoCatastrofico;
	private Integer cantidad;
	private Integer duracion;

	

	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public Integer getDuracion() {
		return duracion;
	}
	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}
	
	public Integer getCausaExterna() {
		return causaExterna;
	}
	public void setCausaExterna(Integer causaExterna) {
		this.causaExterna = causaExterna;
	}
	public Integer getFinalidad() {
		return finalidad;
	}
	public void setFinalidad(Integer finalidad) {
		this.finalidad = finalidad;
	}
	public Integer getTipoCatastrofico() {
		return tipoCatastrofico;
	}
	public void setTipoCatastrofico(Integer tipoCatastrofico) {
		this.tipoCatastrofico = tipoCatastrofico;
	}
    

}
