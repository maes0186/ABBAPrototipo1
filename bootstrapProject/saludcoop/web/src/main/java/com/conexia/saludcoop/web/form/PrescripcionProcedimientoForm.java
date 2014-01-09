package com.conexia.saludcoop.web.form;

public class PrescripcionProcedimientoForm {

	private Integer causaExterna;
	private Integer finalidad;
	private Integer tipoCatastrofico;
	private Integer lateralidad;
	private String posologia;
	private Integer origen;
	private Integer objetivo;
	private Integer tipoPrestacion;

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

	public Integer getOrigen() {
		return origen;
	}

	public void setOrigen(Integer origen) {
		this.origen = origen;
	}

	public Integer getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(Integer objetivo) {
		this.objetivo = objetivo;
	}

	public Integer getTipoPrestacion() {
		return tipoPrestacion;
	}

	public void setTipoPrestacion(Integer tipoPrestacion) {
		this.tipoPrestacion = tipoPrestacion;
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
