package com.conexia.saludcoop.common.repository;

import org.springframework.data.repository.CrudRepository;

import com.conexia.saludcoop.common.entity.maestro.MontosCopagoContributivo;

/**
 * DAO de acceso a datos de montos de copago contributivo.
 * 
 * @author Sebastián Matienzo
 */
public interface MontosCopagoContributivoRepository extends CrudRepository<MontosCopagoContributivo, Long> {
	
}
