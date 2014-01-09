package com.conexia.saludcoop.common.dto;

import ar.com.conexia.generic.annotations.Mappeable;

@Mappeable(mappedTo=ProcedimientoContratadoId.class)
public class ProcedimientoContratadoIdDto {
	private long serialVersionUID;
	private EspecialidadContratadaDto especialidadContratada;
	private ProcedimientoDto procedimiento;
	
	public long getSerialVersionUID() {
		return serialVersionUID;
	}
	public void setSerialVersionUID(long serialVersionUID) {
		this.serialVersionUID = serialVersionUID;
	}
	public EspecialidadContratadaDto getEspecialidadContratada() {
		return especialidadContratada;
	}
	public void setEspecialidadContratada(EspecialidadContratadaDto especialidadContratada) {
		this.especialidadContratada = especialidadContratada;
	}
	public ProcedimientoDto getProcedimiento() {
		return procedimiento;
	}
	public void setProcedimiento(ProcedimientoDto procedimiento) {
		this.procedimiento = procedimiento;
	}



}
