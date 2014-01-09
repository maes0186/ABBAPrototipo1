package com.conexia.saludcoop.common.dto;

import java.util.Date;
import java.util.Set;

import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.entity.maestro.EspecialidadContratada;
@Mappeable(mappedTo=EspecialidadContratada.class)
public class EspecialidadContratadaDto { 
	private long serialVersionUID;

	private Date fechaVencimiento;
	private Double porcentajeNegociacion;
	private Set<ProcedimientoContratadoDto> procedimientosContratados;
	private TarifarioDto tarifarioExcepcion;
	
	private ServicioContratadoDto servicioContratado;
	
	private EspecialidadDto especialidad;

	public long getSerialVersionUID() {
		return serialVersionUID;
	}

	public void setSerialVersionUID(long serialVersionUID) {
		this.serialVersionUID = serialVersionUID;
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

	public Set<ProcedimientoContratadoDto> getProcedimientosContratados() {
		return procedimientosContratados;
	}

	public void setProcedimientosContratados(Set<ProcedimientoContratadoDto> procedimientosContratados) {
		this.procedimientosContratados = procedimientosContratados;
	}

	public TarifarioDto getTarifarioExcepcion() {
		return tarifarioExcepcion;
	}

	public void setTarifarioExcepcion(TarifarioDto tarifarioExcepcion) {
		this.tarifarioExcepcion = tarifarioExcepcion;
	}

	public ServicioContratadoDto getServicioContratado() {
		return servicioContratado;
	}

	public void setServicioContratado(ServicioContratadoDto servicioContratado) {
		this.servicioContratado = servicioContratado;
	}

	public EspecialidadDto getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(EspecialidadDto especialidad) {
		this.especialidad = especialidad;
	}
	


}
