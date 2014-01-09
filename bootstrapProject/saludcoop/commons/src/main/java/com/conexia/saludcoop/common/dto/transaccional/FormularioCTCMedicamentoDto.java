package com.conexia.saludcoop.common.dto.transaccional;

import java.util.HashSet;
import java.util.Set;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.dto.MedicamentoDto;

public class FormularioCTCMedicamentoDto { 
	private Long id;
	private Set<MedicamentoPosPrevioDto> medicamentosAnteriores = new HashSet<>();
	private MedicamentoDto medicamentoHomologo;
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


	
	public Set<MedicamentoPosPrevioDto> getMedicamentosAnteriores() {
		return medicamentosAnteriores;
	}

	public void setMedicamentoHomologo(MedicamentoDto medicamentoHomologo){
		this.medicamentoHomologo = medicamentoHomologo;
	}

	public MedicamentoDto getMedicamentoHomologo(){
		return this.medicamentoHomologo;
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

	public void setMedicamentosAnteriores(
			Set<MedicamentoPosPrevioDto> medicamentosAnteriores) {
		this.medicamentosAnteriores = medicamentosAnteriores;
	}

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
