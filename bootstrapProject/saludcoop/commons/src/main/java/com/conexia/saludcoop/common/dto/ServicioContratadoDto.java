package com.conexia.saludcoop.common.dto;

import java.util.Date;
import java.util.Set;

import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.entity.maestro.ServicioContratado;

@Mappeable(mappedTo=ServicioContratado.class)
public class ServicioContratadoDto { 
	private long serialVersionUID;
	private ServicioDto servicio;
	private ContratoDto contrato;
	private Date fechaVencimiento;
	private Double porcentajeNegociacion;
	private String observaciones;
	private Integer cantidadPromedio;
	private Set<EspecialidadContratadaDto>especialidadesContratadas;
	private TarifarioDto tarifarioExcepcion;

	public void setSerialVersionUID(long serialVersionUID){
		this.serialVersionUID = serialVersionUID;
	}

	public long getSerialVersionUID(){
		return this.serialVersionUID;
	}

	public ServicioDto getServicio() {
		return servicio;
	}

	public void setServicio(ServicioDto servicio) {
		this.servicio = servicio;
	}

	public ContratoDto getContrato() {
		return contrato;
	}

	public void setContrato(ContratoDto contrato) {
		this.contrato = contrato;
	}

	public void setFechaVencimiento(Date fechaVencimiento){
		this.fechaVencimiento = fechaVencimiento;
	}

	public Date getFechaVencimiento(){
		return this.fechaVencimiento;
	}
	public void setPorcentajeNegociacion(Double porcentajeNegociacion){
		this.porcentajeNegociacion = porcentajeNegociacion;
	}

	public Double getPorcentajeNegociacion(){
		return this.porcentajeNegociacion;
	}
	public void setObservaciones(String observaciones){
		this.observaciones = observaciones;
	}

	public String getObservaciones(){
		return this.observaciones;
	}
	public void setCantidadPromedio(Integer cantidadPromedio){
		this.cantidadPromedio = cantidadPromedio;
	}

	public Integer getCantidadPromedio(){
		return this.cantidadPromedio;
	}
	public void setEspecialidadesContratadas(Set<EspecialidadContratadaDto>especialidadesContratadas){
		this.especialidadesContratadas = especialidadesContratadas;
	}

	public Set<EspecialidadContratadaDto>getEspecialidadesContratadas(){
		return this.especialidadesContratadas;
	}
	public void setTarifarioExcepcion(TarifarioDto tarifarioExcepcion){
		this.tarifarioExcepcion = tarifarioExcepcion;
	}

	public TarifarioDto getTarifarioExcepcion(){
		return this.tarifarioExcepcion;
	}

}
