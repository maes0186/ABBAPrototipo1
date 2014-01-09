

package com.conexia.saludcoop.common.dto;

import java.util.Set;

import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.entity.maestro.Servicio;

@Mappeable(mappedTo=Servicio.class)
public class ServicioDto { 
	private long serialVersionUID;
	private Long id;
	private String descripcion;
	private String codigoMinisterioSalud;
	private Integer nivelDeAtencion;
	private Short hospitalario;
	private Set<EspecialidadDto>especialidades;

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
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}

	public String getDescripcion(){
		return this.descripcion;
	}
	public void setCodigoMinisterioSalud(String codigoMinisterioSalud){
		this.codigoMinisterioSalud = codigoMinisterioSalud;
	}

	public String getCodigoMinisterioSalud(){
		return this.codigoMinisterioSalud;
	}
	public void setNivelDeAtencion(Integer nivelDeAtencion){
		this.nivelDeAtencion = nivelDeAtencion;
	}

	public Integer getNivelDeAtencion(){
		return this.nivelDeAtencion;
	}
	public void setHospitalario(Short hospitalario){
		this.hospitalario = hospitalario;
	}

	public Short getHospitalario(){
		return this.hospitalario;
	}
	public void setEspecialidades(Set<EspecialidadDto>especialidades){
		this.especialidades = especialidades;
	}

	public Set<EspecialidadDto>getEspecialidades(){
		return this.especialidades;
	}
}