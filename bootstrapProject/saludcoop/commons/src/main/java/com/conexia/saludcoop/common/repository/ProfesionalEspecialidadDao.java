package com.conexia.saludcoop.common.repository;

import java.util.List;

import com.conexia.saludcoop.common.entity.maestro.ProfesionalEspecialidad;

public interface ProfesionalEspecialidadDao {
	public List<ProfesionalEspecialidad> findBySedeIps(String registroMedico,
			Integer tipoDocumento, String numeroDocumento, 
			String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, Integer especialidad, Long sedeIpsId);
}
