package com.conexia.saludcoop.common.dto;

import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.entity.maestro.ProfesionalEspecialidad;

@Mappeable(mappedTo=ProfesionalEspecialidad.class)
public class ProfesionalEspecialidadIdDto { 
	private long serialVersionUID;
	private ProfesionalDto profesional;
	private EspecialidadDto especialidad;

	public void setSerialVersionUID(long serialVersionUID){
		this.serialVersionUID = serialVersionUID;
	}

	public long getSerialVersionUID(){
		return this.serialVersionUID;
	}
	public void setProfesional(ProfesionalDto profesional){
		this.profesional = profesional;
	}

	public ProfesionalDto getProfesional(){
		return this.profesional;
	}
	public void setEspecialidad(EspecialidadDto especialidad){
		this.especialidad = especialidad;
	}

	public EspecialidadDto getEspecialidad(){
		return this.especialidad;
	}

}
