package com.conexia.saludcoop.common.repository;

import org.springframework.data.repository.CrudRepository;

import com.conexia.saludcoop.common.entity.view.TarifaProcedimientoView;
import com.conexia.saludcoop.common.repository.custom.ExtendedTarifaProcedimientoRepository;

/**
 * DAO de acceso a datos de tarifas de procedimientos.
 * 
 * @author Sebastián Matienzo
 */
public interface TarifaProcedimientoRepository extends CrudRepository<TarifaProcedimientoView, Long>, 
															ExtendedTarifaProcedimientoRepository {
	
}
