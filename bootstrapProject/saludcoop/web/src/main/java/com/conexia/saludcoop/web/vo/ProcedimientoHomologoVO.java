package com.conexia.saludcoop.web.vo;

import com.conexia.saludcoop.common.dto.transaccional.ProcedimientoHomologoDto;

public class ProcedimientoHomologoVO {
	private ProcedimientoMedicamentoVO procedimiento;
	private DescriptivoVO objetivo;
	private Integer cantidadPeriodo;
	private Integer diasDeUso;
	private Integer frecuenciaDeUso;
	
	public ProcedimientoHomologoVO(ProcedimientoMedicamentoVO procedimiento, DescriptivoVO objetivo, Integer cantidadPeriodo, Integer diasDeUso, Integer frecuenciaDeUso) {
		this.procedimiento = procedimiento;
		this.objetivo = objetivo;
		this.cantidadPeriodo = cantidadPeriodo;
		this.diasDeUso = diasDeUso;
		this.frecuenciaDeUso = frecuenciaDeUso;
	}
	public ProcedimientoHomologoVO(ProcedimientoHomologoDto dto){
		this.procedimiento = new ProcedimientoMedicamentoVO(dto.getProcedimiento());
		this.objetivo = new DescriptivoVO(dto.getObjetivoProcedimiento());
		this.cantidadPeriodo = dto.getCantidadPeriodo();
		this.diasDeUso = dto.getDiasDeUso();
		this.frecuenciaDeUso = dto.getFrecuenciaDeUso();
	}
	public ProcedimientoMedicamentoVO getProcedimiento() {
		return procedimiento;
	}
	public void setProcedimiento(ProcedimientoMedicamentoVO procedimiento) {
		this.procedimiento = procedimiento;
	}
	public DescriptivoVO getObjetivo() {
		return objetivo;
	}
	public void setObjetivo(DescriptivoVO objetivo) {
		this.objetivo = objetivo;
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

}
