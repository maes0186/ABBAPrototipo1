package com.conexia.saludcoop.common.repository;

import org.springframework.data.repository.CrudRepository;

import com.conexia.saludcoop.common.entity.view.TarifaMedicamentoView;
import com.conexia.saludcoop.common.repository.custom.ExtendedTarifaMedicamentoRepository;

/**
 * DAO de acceso a datos de tarifas de medicamentos.
 * 
 * @author Sebastián Matienzo
 */
public interface TarifaMedicamentoRepository extends CrudRepository<TarifaMedicamentoView, Long>,
														ExtendedTarifaMedicamentoRepository {
	
}
