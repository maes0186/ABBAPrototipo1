package com.conexia.saludcoop.common.dto.transaccional;

import java.util.HashSet;
import java.util.Set;

import com.conexia.saludcoop.common.dto.DescriptivoDto;

public class FormularioCTCProcedimientoDto { 
	private Long id;
	private Set<ProcedimientoPosPrevioDto> procedimientosAnteriores = new HashSet<>();
	private ProcedimientoHomologoDto procedimientoHomologo;
	private DescriptivoDto causaExterna;
	private DescriptivoDto finalidad;
	private DescriptivoDto tipoCatastrofico;
	private Boolean existeRiesgoInminente = false;
	private String justificacionRiesgoInminente;
	private Boolean posibilidadesPosAgotadas = false;
	private String autorizadoINVIMA;
	private String justificacionSinPosPrevio;
	private Boolean sinAlternativaPos = false;
	
	private String justificacionSinHomologo;
	private String justificacionMedico;
	private String resumenHistoriaClinica;
	
		public String getJustificacionSinHomologo() {
		return justificacionSinHomologo;
	}
	public void setJustificacionSinHomologo(String justificacionSinHomologo) {
		this.justificacionSinHomologo = justificacionSinHomologo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Set<ProcedimientoPosPrevioDto> getProcedimientosAnteriores() {
		return procedimientosAnteriores;
	}
	public void setProcedimientosAnteriores(
			Set<ProcedimientoPosPrevioDto> procedimientosAnteriores) {
		this.procedimientosAnteriores = procedimientosAnteriores;
	}
	public ProcedimientoHomologoDto getProcedimientoHomologo() {
		return procedimientoHomologo;
	}
	public void setProcedimientoHomologo(
			ProcedimientoHomologoDto procedimientoHomologo) {
		this.procedimientoHomologo = procedimientoHomologo;
	}
	public DescriptivoDto getCausaExterna() {
		return causaExterna;
	}
	public void setCausaExterna(DescriptivoDto causaExterna) {
		this.causaExterna = causaExterna;
	}
	public DescriptivoDto getFinalidad() {
		return finalidad;
	}
	public void setFinalidad(DescriptivoDto finalidad) {
		this.finalidad = finalidad;
	}
	public DescriptivoDto getTipoCatastrofico() {
		return tipoCatastrofico;
	}
	public void setTipoCatastrofico(DescriptivoDto tipoCatastrofico) {
		this.tipoCatastrofico = tipoCatastrofico;
	}
	public Boolean getExisteRiesgoInminente() {
		return existeRiesgoInminente;
	}
	public void setExisteRiesgoInminente(Boolean existeRiesgoInminente) {
		this.existeRiesgoInminente = existeRiesgoInminente;
	}
	public String getJustificacionRiesgoInminente() {
		return justificacionRiesgoInminente;
	}
	public void setJustificacionRiesgoInminente(String justificacionRiesgoInminente) {
		this.justificacionRiesgoInminente = justificacionRiesgoInminente;
	}
	public Boolean getPosibilidadesPosAgotadas() {
		return posibilidadesPosAgotadas;
	}
	public void setPosibilidadesPosAgotadas(Boolean posibilidadesPosAgotadas) {
		this.posibilidadesPosAgotadas = posibilidadesPosAgotadas;
	}
	public String getAutorizadoINVIMA() {
		return autorizadoINVIMA;
	}
	public void setAutorizadoINVIMA(String autorizadoINVIMA) {
		this.autorizadoINVIMA = autorizadoINVIMA;
	}
	public String getJustificacionSinPosPrevio() {
		return justificacionSinPosPrevio;
	}
	public void setJustificacionSinPosPrevio(String justificacionSinPosPrevio) {
		this.justificacionSinPosPrevio = justificacionSinPosPrevio;
	}
	public boolean isSinAlternativaPos() {
		return sinAlternativaPos;
	}
	public void setSinAlternativaPos(boolean sinAlternativaPos) {
		this.sinAlternativaPos = sinAlternativaPos;
	}
	public String getJustificacionMedico() {
		return justificacionMedico;
	}
	public void setJustificacionMedico(String justificacionMedico) {
		this.justificacionMedico = justificacionMedico;
	}
	public String getResumenHistoriaClinica() {
		return resumenHistoriaClinica;
	}
	public void setResumenHistoriaClinica(String resumenHistoriaClinica) {
		this.resumenHistoriaClinica = resumenHistoriaClinica;
	}


}
