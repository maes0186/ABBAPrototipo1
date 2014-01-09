package com.conexia.saludcoop.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.conexia.saludcoop.common.entity.maestro.Especialidad;

public interface EspecialidadRepository extends PagingAndSortingRepository<Especialidad, Integer> {
	
	public List<Especialidad> findDistinctByCodigoLikeAndDescripcionLikeAllIgnoreCase(String codigo, String descripcion);
	
	@Query("select pe.especialidad from ProfesionalEspecialidad pe JOIN pe.profesional p where p.id = ? ")
	List<Especialidad> findByProfesional(Long idProfesional);
	
	public List<Especialidad> findByCodigo(String codigo);

}
