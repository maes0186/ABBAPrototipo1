package com.conexia.saludcoop.web.form;

public class PrescripcionMedicamentoForm {

	private Integer causaExterna;
	private Integer finalidad;
	private Integer tipoCatastrofico;
	private Integer dosis;
	private Integer frecuencia;
	private Integer cada;
	private Integer duracion;
	private Integer viaAdministracion;
	private String posologia;
	private String efectosAdversos;
	

	public Integer getDosis() {
		return dosis;
	}
	public void setDosis(Integer dosis) {
		this.dosis = dosis;
	}
	public Integer getFrecuencia() {
		return frecuencia;
	}
	public void setFrecuencia(Integer frecuencia) {
		this.frecuencia = frecuencia;
	}
	public Integer getDuracion() {
		return duracion;
	}
	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}
	public Integer getViaAdministracion() {
		return viaAdministracion;
	}
	public void setViaAdministracion(Integer viaAdministracion) {
		this.viaAdministracion = viaAdministracion;
	}
	public String getPosologia() {
		return posologia;
	}
	public void setPosologia(String posologia) {
		this.posologia = posologia;
	}
	public String getEfectosAdversos() {
		return efectosAdversos;
	}
	public void setEfectosAdversos(String efectosAdversos) {
		this.efectosAdversos = efectosAdversos;
	}
    public Integer getCada() {
        return cada;
    }
    public void setCada(Integer cada) {
        this.cada = cada;
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
