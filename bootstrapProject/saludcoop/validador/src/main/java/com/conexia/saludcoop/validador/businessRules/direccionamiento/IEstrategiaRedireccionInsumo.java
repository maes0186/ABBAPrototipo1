package com.conexia.saludcoop.validador.businessRules.direccionamiento;

import java.util.List;

import com.conexia.saludcoop.common.entity.maestro.Afiliado;
import com.conexia.saludcoop.common.entity.maestro.Insumo;
import com.conexia.saludcoop.common.entity.view.UbicacionSedeIpsProveedorInsumo;

/**
 * Interfaz de estrategia de redireccionamiento de insumo.
 * 
 * @author Leonardo Cruz
 */
public interface IEstrategiaRedireccionInsumo {
    
    /**
     * Obtiene las ubicaciones de posibles redirecciones.
     * 
     * @param afiliado Afiliado involucrado.
     * @param insumo Insumo a redireccionar.
     * @param idSedeIpsExcluir Identificador de la sede de Ips a excluir (null si no hay exclusión).
     * @param numeroPagina Número de página de resultados (pagina de a 10 registros automáticamente). Primera página = 0.
     * @return Lista de ubicaciones de sedes de IPS a donde se puede redireccionar un insumo.
     */
    List<UbicacionSedeIpsProveedorInsumo> getUbicacionesConformeEstrategia(final Afiliado afiliado, 
            final Insumo insumo, final Long idSedeIpsExcluir, final int numeroPagina);
    

}
