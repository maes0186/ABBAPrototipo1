package com.conexia.saludcoop.common.dto.transaccional;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.dto.ProcedimientoDto;

public class ProcedimientoPosPrevioDto {
	private Long id;
	private ProcedimientoDto procedimiento;
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
	public DescriptivoDto getRespuestaClinicaObservada() {
		return respuestaClinicaObservada;
	}
	public void setRespuestaClinicaObservada(
			DescriptivoDto respuestaClinicaObservada) {
		this.respuestaClinicaObservada = respuestaClinicaObservada;
	}
	private DescriptivoDto respuestaClinicaObservada;
}
