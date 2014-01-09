package com.conexia.saludcoop.web.form;

import java.util.Vector;

public class FormularioCTCMedicamentoForm {

	private String efectosAdversos;
	private Integer causaExterna;
	private Integer finalidad;
	private Integer tipoCatastrofico;
	private boolean sinAlternativaPos;
	private Vector<MedicamentoPosForm> posPrevios;
	private MedicamentoPosForm posHomologo;
	private boolean riesgoInminente;
	private String justificacion;
	private boolean posibilidadesPOSAgotadas;
	private String autorizadoINVIMA;

	public String getEfectosAdversos() {
		return efectosAdversos;
	}

	public void setEfectosAdversos(String efectosAdversos) {
		this.efectosAdversos = efectosAdversos;
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

	public Vector<MedicamentoPosForm> getPosPrevios() {
		return posPrevios;
	}

	public void setPosPrevios(Vector<MedicamentoPosForm> posPrevios) {
		this.posPrevios = posPrevios;
	}

	public MedicamentoPosForm getPosHomologo() {
		return posHomologo;
	}

	public void setPosHomologo(MedicamentoPosForm posHomologo) {
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
