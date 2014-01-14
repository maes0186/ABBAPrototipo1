package com.conexia.saludcoop.common.dto;

import java.util.Date;
import java.util.Set;

import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.entity.maestro.Tarifario;
import com.conexia.saludcoop.common.enumerator.TipoTarifario;

@Mappeable(mappedTo=Tarifario.class)
public class TarifarioDto { 
	private long serialVersionUID;
	private Long id;
	private Double porcentajeAjuste;
	private Set<ProcedimientoDto>procedimientos;
	private Date fechaFinalizacion;
	private Date fechaInicio;
	
	public Date getFechaFinalizacion() {
		return fechaFinalizacion;
	}

	public void setFechaFinalizacion(Date fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * Tipo de tarifario.
	 */
	private TipoTarifario tipoTarifario;

	public void setSerialVersionUID(long serialVersionUID){
		this.serialVersionUID = serialVersionUID;
	}

	public long getSerialVersionUID(){
		return this.serialVersionUID;
	}
	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}
	public void setPorcentajeAjuste(Double porcentajeAjuste){
		this.porcentajeAjuste = porcentajeAjuste;
	}

	public Double getPorcentajeAjuste(){
		return this.porcentajeAjuste;
	}

	public void setProcedimientos(Set<ProcedimientoDto>procedimientos){
		this.procedimientos = procedimientos;
	}

	public Set<ProcedimientoDto>getProcedimientos(){
		return this.procedimientos;
	}

	/**
	 * Obtiene el tipo de tarifario.
	 * 
	 * @return Tipo de tarifario.
	 */
	public TipoTarifario getTipoTarifario() {
		
		return (this.tipoTarifario);
	}

	/**
	 * Asigna el tipo de tarifario.
	 * 
	 * @param tipoTarifario Tipo de tarifario.
	 */
	public void setTipoTarifario(final TipoTarifario tipoTarifario) {
		
		this.tipoTarifario = tipoTarifario;
	}
}
