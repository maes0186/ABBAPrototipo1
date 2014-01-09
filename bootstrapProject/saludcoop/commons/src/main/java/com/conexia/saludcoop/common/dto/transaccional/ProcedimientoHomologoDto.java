package com.conexia.saludcoop.common.dto.transaccional;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.dto.ProcedimientoDto;

public class ProcedimientoHomologoDto {
	
	private Long id;
	private ProcedimientoDto procedimiento;
	private Integer cantidadPeriodo;
	private Integer diasDeUso;
	private Integer frecuenciaDeUso;
	private DescriptivoDto objetivoProcedimiento;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ProcedimientoDto getProcedimiento() {
		return procedimiento;
	}
	public void setProcedimiento(ProcedimientoDto procedimiento) {
		this.procedimiento = procedimiento;
	}
	public Integer getCantidadPeriodo() {
		return cantidadPeriodo;
	}
	public void setCantidadPeriodo(Integer cantidadPeriodo) {
		this.cantidadPeriodo = cantidadPeriodo;
	}
	public Integer getDiasDeUso() {
		return diasDeUso;
	}
	public void setDiasDeUso(Integer diasDeUso) {
		this.diasDeUso = diasDeUso;
	}
	public Integer getFrecuenciaDeUso() {
		return frecuenciaDeUso;
	}
	public void setFrecuenciaDeUso(Integer frecuenciaDeUso) {
		this.frecuenciaDeUso = frecuenciaDeUso;
	}
	public DescriptivoDto getObjetivoProcedimiento() {
		return objetivoProcedimiento;
	}
	public void setObjetivoProcedimiento(DescriptivoDto objetivoProcedimiento) {
		this.objetivoProcedimiento = objetivoProcedimiento;
	}
	
}
