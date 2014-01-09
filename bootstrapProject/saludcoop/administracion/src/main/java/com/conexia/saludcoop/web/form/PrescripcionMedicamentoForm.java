package com.conexia.saludcoop.web.form;

public class PrescripcionMedicamentoForm {

	private String dosis;
	private String frecuencia;
	private String duracion;
	private Integer viasAdministracion;
	private String posologia;
	
	public String getDosis() {
		return dosis;
	}
	public void setDosis(String dosis) {
		this.dosis = dosis;
	}
	public String getFrecuencia() {
		return frecuencia;
	}
	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
	}
	public String getDuracion() {
		return duracion;
	}
	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}
	public Integer getViasAdministracion() {
		return viasAdministracion;
	}
	public void setViasAdministracion(Integer viasAdministracion) {
		this.viasAdministracion = viasAdministracion;
	}
	public String getPosologia() {
		return posologia;
	}
	public void setPosologia(String posologia) {
		this.posologia = posologia;
	}

}
