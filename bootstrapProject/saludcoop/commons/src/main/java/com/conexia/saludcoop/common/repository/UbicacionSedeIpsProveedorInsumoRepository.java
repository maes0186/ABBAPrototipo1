package com.conexia.saludcoop.common.repository;

import org.springframework.data.repository.CrudRepository;

import com.conexia.saludcoop.common.entity.view.UbicacionSedeIpsProveedorInsumo;
import com.conexia.saludcoop.common.repository.custom.ExtendedUbicacionSedeIpsProveedorInsumoRepository;

/**
 * DAO de acceso a datos de ubicaciones de sedes de IPS proveedoras de insumos.
 * 
 * @author Leonardo Cruz
 */
public interface UbicacionSedeIpsProveedorInsumoRepository extends CrudRepository<UbicacionSedeIpsProveedorInsumo, Long>, 
                                                                                 ExtendedUbicacionSedeIpsProveedorInsumoRepository {

    /**
     * Tamaño de página a utilizar para la búsqueda.
     */
    static final int TAMANIO_PAGINA = 10;
    
    
}
