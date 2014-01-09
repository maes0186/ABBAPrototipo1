package com.conexia.saludcoop.common.dto;

import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.entity.maestro.ProcedimientoTarifa;

@Mappeable(mappedTo=ProcedimientoTarifa.class)
public class ProcedimientoTarifaDto { 
	private Long id;
	private ProcedimientoDto procedimiento;
	private Double valor;

	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}
	public void setProcedimiento(ProcedimientoDto procedimiento){
		this.procedimiento = procedimiento;
	}

	public ProcedimientoDto getProcedimiento(){
		return this.procedimiento;
	}
	public void setValor(Double valor){
		this.valor = valor;
	}

	public Double getValor(){
		return this.valor;
	}

}
