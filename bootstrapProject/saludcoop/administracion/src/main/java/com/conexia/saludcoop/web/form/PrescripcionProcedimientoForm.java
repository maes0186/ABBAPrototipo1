package com.conexia.saludcoop.web.form;

public class PrescripcionProcedimientoForm {

	private String dosis;
	private String duracion;
	private Integer lateralidad;
	private String posologia;

	public String getDosis() {
		return dosis;
	}

	public void setDosis(String dosis) {
		this.dosis = dosis;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public Integer getLateralidad() {
		return lateralidad;
	}

	public void setLateralidad(Integer lateralidad) {
		this.lateralidad = lateralidad;
	}

	public String getPosologia() {
		return posologia;
	}

	public void setPosologia(String posologia) {
		this.posologia = posologia;
	}

}
