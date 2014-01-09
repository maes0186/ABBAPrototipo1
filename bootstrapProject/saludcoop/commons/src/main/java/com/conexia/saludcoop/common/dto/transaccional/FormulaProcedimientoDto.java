package com.conexia.saludcoop.common.dto.transaccional;

import com.conexia.saludcoop.common.dto.DescriptivoDto;


public class FormulaProcedimientoDto { 
	private Long id;
	private Integer causaExterna;
	private Integer finalidad;
	private Integer tipoCatastrofico;
	private DescriptivoDto lateralidad;
	private String posologia;
	private DescriptivoDto origenRepeticion;
	private DescriptivoDto objetivo;
	private DescriptivoDto tipoPrestacion;
	
	public DescriptivoDto getOrigenRepeticion() {
		return origenRepeticion;
	}

	public void setOrigenRepeticion(DescriptivoDto origenRepeticion) {
		this.origenRepeticion = origenRepeticion;
	}

	public DescriptivoDto getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(DescriptivoDto objetivo) {
		this.objetivo = objetivo;
	}

	public DescriptivoDto getTipoPrestacion() {
		return tipoPrestacion;
	}

	public void setTipoPrestacion(DescriptivoDto tipoPrestacion) {
		this.tipoPrestacion = tipoPrestacion;
	}

	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setLateralidad(DescriptivoDto lateralidad){
		this.lateralidad = lateralidad;
	}

	public DescriptivoDto getLateralidad(){
		return this.lateralidad;
	}
	public void setPosologia(String posologia){
		this.posologia = posologia;
	}

	public String getPosologia(){
		return this.posologia;
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
