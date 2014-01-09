package com.conexia.saludcoop.common.repository;

import org.springframework.data.repository.CrudRepository;

import com.conexia.saludcoop.common.entity.view.UbicacionSedeIpsProveedorMedicamento;
import com.conexia.saludcoop.common.repository.custom.ExtendedUbicacionSedeIpsProveedorMedicamentoRepository;

/**
 * DAO de acceso a datos de ubicaciones de sedes de IPS proveedoras de medicamentos.
 * 
 * @author Sebastián Matienzo
 */
public interface UbicacionSedeIpsProveedorMedicamentoRepository extends CrudRepository<UbicacionSedeIpsProveedorMedicamento, Long>, 
																				ExtendedUbicacionSedeIpsProveedorMedicamentoRepository {

	/**
	 * Tamaño de página a utilizar para la búsqueda.
	 */
	static final int TAMANIO_PAGINA = 10;
	
}
