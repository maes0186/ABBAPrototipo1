package com.conexia.saludcoop.common.repository;

import com.conexia.saludcoop.common.entity.maestro.TipoPPM;

public interface TipoPPMRepository extends DescriptivoRepository<TipoPPM>{

	TipoPPM findOneByDescripcion(String tipoPPM);
	TipoPPM findOneByCodigo(String codigo);
}
