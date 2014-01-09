package com.conexia.saludcoop.common.dto;

public class UsuarioEntidadDto {

	private Long id;
	private Long usuarioId;
	private SedeIpsDto sedeIps;
	private ProfesionalEspecialidadDto profesionalEspecialidad;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public SedeIpsDto getSedeIps() {
		return sedeIps;
	}

	public void setSedeIps(SedeIpsDto sedeIps) {
		this.sedeIps = sedeIps;
	}

	public ProfesionalEspecialidadDto getProfesionalEspecialidad() {
		return profesionalEspecialidad;
	}

	public void setProfesionalEspecialidad(ProfesionalEspecialidadDto profesionalEspecialidad) {
		this.profesionalEspecialidad = profesionalEspecialidad;
	}

}