package com.conexia.saludcoop.common.dto;

import java.util.Date;

public class ProcedimientoContratadoDto {
	private long serialVersionUID;
	private ProcedimientoContratadoIdDto pk;
	private Date fechaVencimiento;
	private Double porcentajeNegociacion;
	private TarifarioDto tarifario;
	public long getSerialVersionUID() {
		return serialVersionUID;
	}
	public void setSerialVersionUID(long serialVersionUID) {
		this.serialVersionUID = serialVersionUID;
	}
	public ProcedimientoContratadoIdDto getPk() {
		return pk;
	}
	public void setPk(ProcedimientoContratadoIdDto pk) {
		this.pk = pk;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public Double getPorcentajeNegociacion() {
		return porcentajeNegociacion;
	}
	public void setPorcentajeNegociacion(Double porcentajeNegociacion) {
		this.porcentajeNegociacion = porcentajeNegociacion;
	}
	public TarifarioDto getTarifario() {
		return tarifario;
	}
	public void setTarifario(TarifarioDto tarifario) {
		this.tarifario = tarifario;
	}
}
