package com.conexia.saludcoop.common.repository.custom;

import java.util.List;

import com.conexia.saludcoop.common.entity.view.UbicacionSedeIpsProveedorInsumo;

/**
 * Interfaz para lógica personalizada de repositorio de ubicacionSedeIpsProveedorInsumo.
 * 
 * @author Leoanrdo Cruz
 */
public interface ExtendedUbicacionSedeIpsProveedorInsumoRepository {
    /**
     * Obtiene las sedes de IPS existentes en un municipio, que proveen el insumo.
     * 
     * @param epsId Identificador de la EPS correspondiente.
     * @param insumoId Identificador del insumo.
     * @param sedeIpsExcluidaId Identificador de la sede de IPS a excluir de los resultados (o null si debe evaluar todas).
     * @param municipioId Identificador del municipio en el cual deben estar las IPS.
     * @param numeroPagina Número de página de resultados (pagina de a 10 registros automáticamente). Primera página = 0.
     * @return Lista de sedes de IPS efectoras, con el detalle del contrato correspondiente.
     */
    public List<UbicacionSedeIpsProveedorInsumo> getSedesIpsPorMunicipio(final Long epsId, final Long insumoId,
            final Long sedeIpsExcluidaId, final Long municipioId, final int numeroPagina);
    
    /**
     * Obtiene las sedes de IPS existentes en una división seccional, que proveen el insumo.
     * 
     * @param epsId Identificador de la EPS correspondiente.
     * @param insumoId Identificador del insumo.
     * @param sedeIpsExcluidaId Identificador de la sede de IPS a excluir de los resultados (o null si debe evaluar todas).
     * @param divisionesSeccionalesId Lista de ids de divisiones de seccional en las cuales deben estar las IPS.
     * @param numeroPagina Número de página de resultados (pagina de a 10 registros automáticamente). Primera página = 0.
     * @return Lista de sedes de IPS efectoras, con el detalle del contrato correspondiente.
     */
    public List<UbicacionSedeIpsProveedorInsumo> getSedesIpsPorDivisionSeccional(final Long epsId, final Long insumoId,
            final Long sedeIpsExcluidaId, final List<Long> divisionesSeccionalesId, final int numeroPagina);
    
    /**
     * Obtiene las sedes de IPS existentes en una región, que proveen el insumo.
     * 
     * @param epsId Identificador de la EPS correspondiente.
     * @param insumoId Identificador del insumo.
     * @param sedeIpsExcluidaId Identificador de la sede de IPS a excluir de los resultados (o null si debe evaluar todas).
     * @param regionalesId Lista de ids de regionales en las cuales deben estar las IPS.
     * @param numeroPagina Número de página de resultados (pagina de a 10 registros automáticamente). Primera página = 0.
     * @return Lista de sedes de IPS efectoras, con el detalle del contrato correspondiente.
     */
    public List<UbicacionSedeIpsProveedorInsumo> getSedesIpsPorRegional(final Long epsId, final Long insumoId,
            final Long sedeIpsExcluidaId, final List<Long> regionalesId, final int numeroPagina);
    
    /**
     * Obtiene las sedes de IPS existentes en todo el país, que proveen el insumo.
     * 
     * @param epsId Identificador de la EPS correspondiente.
     * @param insumoId Identificador del insumo.
     * @param sedeIpsExcluidaId Identificador de la sede de IPS a excluir de los resultados (o null si debe evaluar todas).
     * @param numeroPagina Número de página de resultados (pagina de a 10 registros automáticamente). Primera página = 0.
     * @return Lista de sedes de IPS efectoras, con el detalle del contrato correspondiente.
     */
    public List<UbicacionSedeIpsProveedorInsumo> getSedesIpsNivelNacional(final Long epsId, final Long insumoId,
            final Long sedeIpsExcluidaId, final int numeroPagina);

}
