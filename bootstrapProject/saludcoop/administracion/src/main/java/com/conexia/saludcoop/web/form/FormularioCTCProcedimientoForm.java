package com.conexia.saludcoop.web.form;

import java.util.Vector;

public class FormularioCTCProcedimientoForm {


	private Integer objetivo;
	private Integer tipoPrestacion;
	private Integer origen;
	private Integer causaExterna;
	private Integer finalidad;
	private Integer tipoCatastrofico;
	private boolean sinAlternativaPos;
	private Vector<ProcedimientoPosForm> posPrevios;
	private String justificacionSinAlternativaPOS;
	private Integer respClinicaObservadas;
	private ProcedimientoPosForm posHomologo;
	private boolean riesgoInminente;
	private String justificacion;
	private boolean posibilidadesPOSAgotadas;
	private String autorizadoINVIMA;

	
	
	public String getJustificacionSinAlternativaPOS() {
		return justificacionSinAlternativaPOS;
	}

	public void setJustificacionSinAlternativaPOS(
			String justificacionSinAlternativaPOS) {
		this.justificacionSinAlternativaPOS = justificacionSinAlternativaPOS;
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

	public Integer getOrigen() {
		return origen;
	}

	public void setOrigen(Integer origen) {
		this.origen = origen;
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

	public Vector<ProcedimientoPosForm> getPosPrevios() {
		return posPrevios;
	}

	public void setPosPrevios(Vector<ProcedimientoPosForm> posPrevios) {
		this.posPrevios = posPrevios;
	}

	public Integer getRespClinicaObservadas() {
		return respClinicaObservadas;
	}

	public void setRespClinicaObservadas(Integer respClinicaObservadas) {
		this.respClinicaObservadas = respClinicaObservadas;
	}

	public ProcedimientoPosForm getPosHomologo() {
		return posHomologo;
	}

	public void setPosHomologo(ProcedimientoPosForm posHomologo) {
		this.posHomologo = posHomologo;
	}

	public boolean isRiesgoInminente() {
		return riesgoInminente;
	}

	public void setRiesgoInminente(boolean riesgoInminente) {
		this.riesgoInminente = riesgoInminente;
	}

	public String getJustificacion() {
		return justificacion;
	}

	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
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
}
