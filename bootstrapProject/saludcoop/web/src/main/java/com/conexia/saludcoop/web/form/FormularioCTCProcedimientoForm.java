package com.conexia.saludcoop.web.form;

import java.util.Vector;

public class FormularioCTCProcedimientoForm {

	private String resumenHistoriaClinica;
	private Integer causaExterna;
	private Integer finalidad;
	private Integer tipoCatastrofico;
	private boolean sinAlternativaPos = false;
	private String justificacionSinHomologo;
	private Vector<ProcedimientoPosPrevioForm> procedimientosPosPrevios = new Vector<>();
	private String justificacionSinPosPrevio;
	private ProcedimientoPosHomologoForm procedimientoPosHomologo;
	private boolean riesgoInminente = false;
	private String justificacionRiesgoInminente;
	private boolean posibilidadesPOSAgotadas = false;
	private String autorizadoINVIMA;
	private String justificacionMedico;
	
	
	
	public String getJustificacionSinHomologo() {
		return justificacionSinHomologo;
	}
	public void setJustificacionSinHomologo(String justificacionSinHomologo) {
		this.justificacionSinHomologo = justificacionSinHomologo;
	}
	public String getResumenHistoriaClinica() {
		return resumenHistoriaClinica;
	}
	public void setResumenHistoriaClinica(String resumenHistoriaClinica) {
		this.resumenHistoriaClinica = resumenHistoriaClinica;
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
	public boolean isSinAlternativaPos() {
		return sinAlternativaPos;
	}
	public void setSinAlternativaPos(boolean sinAlternativaPos) {
		this.sinAlternativaPos = sinAlternativaPos;
	}
	public Vector<ProcedimientoPosPrevioForm> getProcedimientosPosPrevios() {
		return procedimientosPosPrevios;
	}
	public void setProcedimientosPosPrevios(
			Vector<ProcedimientoPosPrevioForm> procedimientosPosPrevios) {
		this.procedimientosPosPrevios = procedimientosPosPrevios;
	}
	public String getJustificacionSinPosPrevio() {
		return justificacionSinPosPrevio;
	}
	public void setJustificacionSinPosPrevio(String justificacionSinPosPrevio) {
		this.justificacionSinPosPrevio = justificacionSinPosPrevio;
	}
	public ProcedimientoPosHomologoForm getProcedimientoPosHomologo() {
		return procedimientoPosHomologo;
	}
	public void setProcedimientoPosHomologo(
			ProcedimientoPosHomologoForm procedimientoPosHomologo) {
		this.procedimientoPosHomologo = procedimientoPosHomologo;
	}
	public boolean isRiesgoInminente() {
		return riesgoInminente;
	}
	public void setRiesgoInminente(boolean riesgoInminente) {
		this.riesgoInminente = riesgoInminente;
	}
	
	public String getJustificacionRiesgoInminente() {
		return justificacionRiesgoInminente;
	}
	public void setJustificacionRiesgoInminente(String justificacionRiesgoInminente) {
		this.justificacionRiesgoInminente = justificacionRiesgoInminente;
	}
	public boolean isPosibilidadesPOSAgotadas() {
		return posibilidadesPOSAgotadas;
	}
	public void setPosibilidadesPOSAgotadas(boolean posibilidadesPOSAgotadas) {
		this.posibilidadesPOSAgotadas = posibilidadesPOSAgotadas;
	}
	public String getAutorizadoINVIMA() {
		return autorizadoINVIMA;
	}
	public void setAutorizadoINVIMA(String autorizadoINVIMA) {
		this.autorizadoINVIMA = autorizadoINVIMA;
	}
	public String getJustificacionMedico() {
		return justificacionMedico;
	}
	public void setJustificacionMedico(String justificacionMedico) {
		this.justificacionMedico = justificacionMedico;
	}
	
	
	
}
	