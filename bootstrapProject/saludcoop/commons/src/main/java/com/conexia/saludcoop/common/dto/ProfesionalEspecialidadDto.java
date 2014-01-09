package com.conexia.saludcoop.common.dto;

import java.util.List;

import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.entity.maestro.ProfesionalEspecialidad;
@Mappeable(mappedTo=ProfesionalEspecialidad.class)
public class ProfesionalEspecialidadDto { 
	private Long id;
	private ProfesionalDto profesional;
	private DescriptivoDto especialidad;
	private List<SedeIpsDto> sedes;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ProfesionalDto getProfesional() {
		return profesional;
	}
	public void setProfesional(ProfesionalDto profesional) {
		this.profesional = profesional;
	}
	public DescriptivoDto getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(DescriptivoDto especialidad) {
		this.especialidad = especialidad;
	}
	public List<SedeIpsDto> getSedes() {
		return sedes;
	}
	public void setSedes(List<SedeIpsDto> sedes) {
		this.sedes = sedes;
	}
	
	
	
}
