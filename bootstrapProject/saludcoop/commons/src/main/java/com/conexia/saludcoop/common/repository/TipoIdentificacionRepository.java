package com.conexia.saludcoop.common.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.conexia.saludcoop.common.entity.maestro.TipoIdentificacion;

public interface TipoIdentificacionRepository extends	CrudRepository<TipoIdentificacion, Integer> {

	public TipoIdentificacion findOneById(Integer id);
	public TipoIdentificacion findOneByCodigo(String codigo);
	public TipoIdentificacion findOneByDescripcion(String codigo);

	public List<TipoIdentificacion> findByCodigoLike(String codigo);
	public List<TipoIdentificacion> findByDescripcionLike(String codigo);
	
}
