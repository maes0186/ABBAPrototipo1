package com.conexia.saludcoop.common.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.conexia.saludcoop.common.entity.maestro.Procedimiento;

public interface ProcedimientoRepository extends CrudRepository<Procedimiento, Long> {

	public List<Procedimiento> findDistinctByCodigoLikeAndDescripcionLikeAllIgnoreCase(String codigo, String descripcion);
	
	public List<Procedimiento> findByCodigoLikeIgnoreCaseAndDescripcionLikeIgnoreCaseAndTipoId(String codigo, String descripcion, Integer tipoPPM);

	public Procedimiento findOneByCodigoIgnoreCase(String codigo);
	
	public Procedimiento findOneByCodigoAndDescripcionAllIgnoreCase(String codigo, String descripcion);
	
	public List<Procedimiento> findByTipoIdAndCodigoLikeIgnoreCaseAndDescripcionLikeIgnoreCase(
			Integer posId, String codigo, String descripcion);
}
