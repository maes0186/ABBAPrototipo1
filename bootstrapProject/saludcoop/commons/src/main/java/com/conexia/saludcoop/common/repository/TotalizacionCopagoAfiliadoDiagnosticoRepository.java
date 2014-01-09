package com.conexia.saludcoop.common.repository;

import org.springframework.data.repository.CrudRepository;

import com.conexia.saludcoop.common.entity.auxiliar.TotalizacionCopagoAfiliadoDiagnostico;

/**
 * DAO de acceso a datos de totalización de copagos de afiliados por diagnóstio.
 * 
 * @author Sebastián Matienzo
 */
public interface TotalizacionCopagoAfiliadoDiagnosticoRepository extends CrudRepository<TotalizacionCopagoAfiliadoDiagnostico, Long> {
	
	TotalizacionCopagoAfiliadoDiagnostico findByAnioAndAfiliadoIdAndDiagnosticoId(final Integer anio, 
			final Long afiliadoId, final Long diagnosticoId);
	
}
