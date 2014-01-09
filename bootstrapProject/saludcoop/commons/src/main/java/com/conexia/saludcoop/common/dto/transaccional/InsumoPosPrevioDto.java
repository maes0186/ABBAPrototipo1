package com.conexia.saludcoop.common.dto.transaccional;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.dto.InsumoDto;

public class InsumoPosPrevioDto {
	private Long id;
	private InsumoDto insumoDto;
	private Integer cantidad;
	private Integer diasTratamiento;
	private DescriptivoDto respuestaClinicaObservada;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public InsumoDto getInsumo() {
		return insumoDto;
	}
	public void setInsumo(InsumoDto insumoDto) {
		this.insumoDto = insumoDto;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Integer getDiasTratamiento() {
		return diasTratamiento;
	}
	public void setDiasTratamiento(Integer diasTratamiento) {
		this.diasTratamiento = diasTratamiento;
	}
	public DescriptivoDto getRespuestaClinicaObservada() {
		return respuestaClinicaObservada;
	}
	public void setRespuestaClinicaObservada(DescriptivoDto respuestaClinicaObservada) {
		this.respuestaClinicaObservada = respuestaClinicaObservada;
	}
	
	
	
}
