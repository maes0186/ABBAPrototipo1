package com.conexia.saludcoop.common.dto.transaccional;

import java.util.HashSet;
import java.util.Set;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.dto.InsumoDto;

public class FormularioCTCInsumoDto { 
	private Long id;
//	private Set<InsumoPosPrevioDto> insumosAnteriores = new HashSet<>();
	private InsumoDto insumoHomologo;
	private String autorizadoINVIMA;
	private DescriptivoDto causaExterna;
	private DescriptivoDto finalidad;
	private String justificacionRiesgoInminente;
	private DescriptivoDto tipoCatastrofico;
	private Boolean existeRiesgoInminente = false;
	private Boolean posibilidadesPosAgotadas = false;
	private Boolean sinAlternativaPos = false;
	private String justificacionSinPosPrevio;
	private String justificacionMedico;
	private String resumenHistoriaClinica;

	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}


	
//	public Set<InsumoPosPrevioDto> getInsumosAnteriores() {
//		return insumosAnteriores;
//	}
//
	public void setInsumoHomologo(InsumoDto insumoHomologo){
		this.insumoHomologo = insumoHomologo;
	}

	public InsumoDto getInsumoHomologo(){
		return this.insumoHomologo;
	}
	public void setAutorizadoINVIMA(String autorizadoINVIMA){
		this.autorizadoINVIMA = autorizadoINVIMA;
	}

	public String getAutorizadoINVIMA(){
		return this.autorizadoINVIMA;
	}
	public void setCausaExterna(DescriptivoDto causaExterna){
		this.causaExterna = causaExterna;
	}

	public DescriptivoDto getCausaExterna(){
		return this.causaExterna;
	}

	public void setFinalidad(DescriptivoDto finalidad){
		this.finalidad = finalidad;
	}

	public DescriptivoDto getFinalidad(){
		return this.finalidad;
	}

	public String getJustificacionRiesgoInminente() {
		return justificacionRiesgoInminente;
	}

	public void setJustificacionRiesgoInminente(String justificacionRiesgoInminente) {
		this.justificacionRiesgoInminente = justificacionRiesgoInminente;
	}

	public String getJustificacionMedico() {
		return justificacionMedico;
	}

	public void setJustificacionMedico(String justificacionMedico) {
		this.justificacionMedico = justificacionMedico;
	}

//	public void setInsumosAnteriores(
//			Set<InsumoPosPrevioDto> insumosAnteriores) {
//		this.insumosAnteriores = insumosAnteriores;
//	}

	public void setTipoCatastrofico(DescriptivoDto tipoCatastrofico){
		this.tipoCatastrofico = tipoCatastrofico;
	}

	public DescriptivoDto getTipoCatastrofico(){
		return this.tipoCatastrofico;
	}
	public void setExisteRiesgoInminente(Boolean existeRiesgoInminente){
		this.existeRiesgoInminente = existeRiesgoInminente;
	}

	public Boolean getExisteRiesgoInminente(){
		return this.existeRiesgoInminente;
	}
	public void setPosibilidadesPosAgotadas(Boolean posibilidadesPosAgotadas){
		this.posibilidadesPosAgotadas = posibilidadesPosAgotadas;
	}

	public Boolean getPosibilidadesPosAgotadas(){
		return this.posibilidadesPosAgotadas;
	}
	public void setSinAlternativaPos(boolean sinAlternativaPos){
		this.sinAlternativaPos = sinAlternativaPos;
	}

	public boolean getSinAlternativaPos(){
		return this.sinAlternativaPos;
	}
	public void setJustificacionSinPosPrevio(String justificacionSinPosPrevio){
		this.justificacionSinPosPrevio = justificacionSinPosPrevio;
	}

	public String getJustificacionSinPosPrevio(){
		return this.justificacionSinPosPrevio;
	}

	public String getResumenHistoriaClinica() {
		return resumenHistoriaClinica;
	}

	public void setResumenHistoriaClinica(String resumenHistoriaClinica) {
		this.resumenHistoriaClinica = resumenHistoriaClinica;
	}

}
