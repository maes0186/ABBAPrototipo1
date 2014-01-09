package com.conexia.saludcoop.common.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.conexia.saludcoop.common.entity.maestro.Insumo;

public interface InsumoRepository extends CrudRepository<Insumo, Long> {

	public List<Insumo> findByCodigoLikeAndDescripcionLikeAllIgnoreCase(String codigo, String descripcion);

	public List<Insumo> findByTipoIdAndCodigoLikeIgnoreCaseAndDescripcionLikeIgnoreCase(int posId, String likeParameter, String likeParameter2);
	
	public Insumo findOneByCodigoIgnoreCase(String codigo);
}
