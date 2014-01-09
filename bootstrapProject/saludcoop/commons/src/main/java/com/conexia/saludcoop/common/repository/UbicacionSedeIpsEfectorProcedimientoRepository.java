package com.conexia.saludcoop.common.repository;

import org.springframework.data.repository.CrudRepository;

import com.conexia.saludcoop.common.entity.view.UbicacionSedeIpsEfectorProcedimiento;
import com.conexia.saludcoop.common.repository.custom.ExtendedUbicacionSedeIpsEfectorProcedimientoRepository;

/**
 * DAO de acceso a datos de ubicaciones de sedes de IPS efectoras de procedimientos.
 * 
 * @author Sebastián Matienzo
 */
public interface UbicacionSedeIpsEfectorProcedimientoRepository extends CrudRepository<UbicacionSedeIpsEfectorProcedimiento, Long>, 
																				ExtendedUbicacionSedeIpsEfectorProcedimientoRepository {

	/**
	 * Tamaño de página a utilizar para la búsqueda.
	 */
	static final int TAMANIO_PAGINA = 10;
		
}
