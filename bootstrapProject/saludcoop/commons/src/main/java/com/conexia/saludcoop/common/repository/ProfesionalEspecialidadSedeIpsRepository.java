package com.conexia.saludcoop.common.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.conexia.saludcoop.common.entity.maestro.ProfesionalEspecialidadSedeIps;


public interface ProfesionalEspecialidadSedeIpsRepository extends CrudRepository<ProfesionalEspecialidadSedeIps, Integer>{
	  @Query(value = "select pesips from ProfesionalEspecialidadSedeIps pesips, ProfesionalEspecialidad pe, SedeIps sips "
	            + " where pesips.profesionalEspecialidad=pe and pesips.sedeIps=sips and pe.id = ? and sips.id = ?  ")
	public ProfesionalEspecialidadSedeIps busquedaPorEspecialidadSedeIps(Long profesionalEspecialidadId, Long sedeIpsId);

}
