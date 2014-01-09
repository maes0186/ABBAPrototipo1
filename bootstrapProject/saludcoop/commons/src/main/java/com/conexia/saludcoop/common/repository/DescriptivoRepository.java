package com.conexia.saludcoop.common.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.conexia.saludcoop.common.entity.maestro.Descriptivo;

public interface DescriptivoRepository<T extends Descriptivo> extends
		PagingAndSortingRepository<T, Integer> {
	public T findOneByCodigo(String codigo);
	public T findOneByDescripcion(String codigo);

	public List<T> findByCodigoLike(String codigo);
	public List<T> findByDescripcionLike(String codigo);
	
}
