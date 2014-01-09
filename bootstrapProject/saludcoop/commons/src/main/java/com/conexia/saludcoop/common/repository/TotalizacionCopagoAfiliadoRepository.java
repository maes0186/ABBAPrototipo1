package com.conexia.saludcoop.common.repository;

import org.springframework.data.repository.CrudRepository;

import com.conexia.saludcoop.common.entity.auxiliar.TotalizacionCopagoAfiliado;

/**
 * DAO de acceso a datos de totalización de copagos de afiliados.
 * 
 * @author Sebastián Matienzo
 */
public interface TotalizacionCopagoAfiliadoRepository extends CrudRepository<TotalizacionCopagoAfiliado, Long> {
	
	TotalizacionCopagoAfiliado findByAnioAndAfiliadoId(final Integer anio, final Long afiliadoId);
	
}
