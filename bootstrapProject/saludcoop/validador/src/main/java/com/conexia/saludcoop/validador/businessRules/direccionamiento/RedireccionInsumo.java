package com.conexia.saludcoop.validador.businessRules.direccionamiento;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conexia.saludcoop.common.dto.UbicacionSedeIpsProveedorInsumoDto;
import com.conexia.saludcoop.common.entity.maestro.Afiliado;
import com.conexia.saludcoop.common.entity.maestro.Insumo;
import com.conexia.saludcoop.common.entity.view.UbicacionSedeIpsProveedorInsumo;
import com.conexia.saludcoop.validador.businessRules.direccionamiento.estrategia.RedireccionInsumoPorMunicipio;
import com.conexia.saludcoop.validador.businessRules.direccionamiento.estrategia.RedireccionInsumoPorPais;
import com.conexia.saludcoop.validador.businessRules.direccionamiento.estrategia.RedireccionInsumoPorRegional;
import com.conexia.saludcoop.validador.businessRules.direccionamiento.estrategia.RedireccionInsumoPorSeccional;

/**
 * Estrategia genérica de redireccionamiento de insumo.
 * 
 * @author Leonardo Cruz
 */
@Component
public class RedireccionInsumo  {

    /**
     * Estrategia de redirección por municipio.
     */
    @Autowired
    private RedireccionInsumoPorMunicipio redireccionPorMunicipio;

    /**
     * Estrategia de redirección por seccional.
     */
    @Autowired
    private RedireccionInsumoPorSeccional redireccionPorSeccional;

    /**
     * Estrategia de redirección por regional.
     */
    @Autowired
    private RedireccionInsumoPorRegional redireccionPorRegional;

    /**
     * Estrategia de redirección por país completo.
     */
    @Autowired
    private RedireccionInsumoPorPais redireccionPorPais;

    /**
     * Obtiene posibilidades de redirección de un medicamento, conforme a la ubicación de un afiliado.
     * 
     * @param procedimiento Medicamento a redireccionar.
     * @param afiliado Afiliado para el cual redireccionar.
     * @param idSedeIpsExcluir Identificador de la sede de Ips a excluir (null si no hay exclusión).
     * @param cantidadUbicacionesSolicitadas Indica la cantidad de ubicaciones a obtener.
     * @return Lista de ubicaciones de sedes de IPS a donde se puede redireccionar un procedimiento.
     */
    public List<UbicacionSedeIpsProveedorInsumoDto> redireccionar(final Insumo insumo, final Afiliado afiliado, 
            final Long idSedeIpsExcluir, final int cantidadUbicacionesSolicitadas) {
        
        /* Se usa para evitar dos veces repetir la misma sede */
        final Set<Long> idsSedesYaProcesadas = new HashSet<Long>();

        final List<UbicacionSedeIpsProveedorInsumoDto> entidades = new ArrayList<UbicacionSedeIpsProveedorInsumoDto>();
        
        for (final IEstrategiaRedireccionInsumo estrategia : this.getEstrategiasBusqueda()) {
            entidades.addAll(this.getUbicaciones(afiliado, insumo, null, idSedeIpsExcluir, cantidadUbicacionesSolicitadas,
                    idsSedesYaProcesadas, estrategia));
        }

        return (entidades);
    }

    /**
     * Obtiene las ubicaciones de posibles redirecciones conforme a la estrategia especificada.
     * 
     * @param afiliado Afiliado involucrado.
     * @param insumo Insumo a redireccionar.
     * @param codigoTipoMinuta Código de tipo de minuta (null si ninguno).
     * @param idSedeIpsExcluir Identificador de la sede de Ips a excluir (null si no hay exclusión).
     * @param cantidadUbicacionesSolicitadas Cantidad de ubicaciones máximas que se deben buscar.
     * @param idsSedesYaProcesadas Identificadores de sedes ya procesadas (y que no deben, por ende, repetirse).
     * @param estrategiaRedireccion Estrategia de redirección a utilizar.
     * @return Lista de ubicaciones de sedes de IPS a donde se puede redireccionar un procedimiento.
     */
    public List<UbicacionSedeIpsProveedorInsumoDto> getUbicaciones(final Afiliado afiliado, 
            final Insumo insumo, final String codigoTipoMinuta, final Long idSedeIpsExcluir, 
            final int cantidadUbicacionesSolicitadas, final Set<Long> idsSedesYaProcesadas, 
            final IEstrategiaRedireccionInsumo estrategiaRedireccion) {
        
        final List<UbicacionSedeIpsProveedorInsumoDto> resultadosHallados = new ArrayList<UbicacionSedeIpsProveedorInsumoDto>();
        
        if (idsSedesYaProcesadas.size() >= cantidadUbicacionesSolicitadas) {
            return (resultadosHallados);
        }
        
        int contadorPagina = 0;
        
        int cantidadResultadosHalladasEnPagina = 0;
        
        do {
            final List<UbicacionSedeIpsProveedorInsumo> sedesIpsHalladas = estrategiaRedireccion.getUbicacionesConformeEstrategia(
                    afiliado, insumo, idSedeIpsExcluir, contadorPagina);
            
            cantidadResultadosHalladasEnPagina = sedesIpsHalladas.size();
            
            for (final UbicacionSedeIpsProveedorInsumo ubicacion : sedesIpsHalladas) {
                if (idsSedesYaProcesadas.size() < cantidadUbicacionesSolicitadas 
                        && !idsSedesYaProcesadas.contains(ubicacion.getSedeIpsId())) {
                    resultadosHallados.add(ubicacion.toDto());
                    
                    idsSedesYaProcesadas.add(ubicacion.getSedeIpsId());
                }
            }
            
            contadorPagina++;
            
        } while (cantidadResultadosHalladasEnPagina > 0 && idsSedesYaProcesadas.size() < cantidadUbicacionesSolicitadas);
        
        return (resultadosHallados);
    }

    /**
     * Obtiene la lista de estrategias de búsqueda.
     * 
     * @return Lista de estrategias de búsqueda.
     */
    private List<IEstrategiaRedireccionInsumo> getEstrategiasBusqueda() {
        
        final List<IEstrategiaRedireccionInsumo> estrategias = new ArrayList<IEstrategiaRedireccionInsumo>();

        estrategias.add(this.redireccionPorMunicipio);
        estrategias.add(this.redireccionPorSeccional);
        estrategias.add(this.redireccionPorRegional);
        estrategias.add(this.redireccionPorPais);
        
        return (estrategias);
    }
}

