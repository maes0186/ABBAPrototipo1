package com.conexia.saludcoop.common.repository;

import org.springframework.data.repository.CrudRepository;

import com.conexia.saludcoop.common.entity.maestro.MontosCopagoSubsidiado;

/**
 * DAO de acceso a datos de montos de copago contributivo.
 * 
 * @author Sebastián Matienzo
 */
public interface MontosCopagoSubsidiadoRepository extends CrudRepository<MontosCopagoSubsidiado, Long> {
	
}
