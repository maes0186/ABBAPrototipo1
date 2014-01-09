package com.conexia.saludcoop.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.conexia.saludcoop.common.entity.security.UsuarioEntidad;

public interface UsuarioEntidadRepository extends CrudRepository<UsuarioEntidad, Long> {

	
	public UsuarioEntidad findOneByUsuarioId(Long usuarioId);
	
	@Query
	public List<UsuarioEntidad> findEntidadsById(Long usuarioId);
}
