package com.conexia.saludcoop.validador.businessRules.direccionamiento.estrategia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conexia.saludcoop.common.entity.maestro.Afiliado;
import com.conexia.saludcoop.common.entity.maestro.Insumo;
import com.conexia.saludcoop.common.entity.view.UbicacionSedeIpsProveedorInsumo;
import com.conexia.saludcoop.common.repository.UbicacionSedeIpsProveedorInsumoRepository;
import com.conexia.saludcoop.validador.businessRules.direccionamiento.IEstrategiaRedireccionInsumo;

/**
 * Redirecciona un insumo por IPS dentro del país.
 * 
 * @author Leonardo Cruz
 */
@Component
public class RedireccionInsumoPorPais implements IEstrategiaRedireccionInsumo {
    
    /**
     * Repositorio de acceso a datos de la estrategia.
     */
    @Autowired
    private UbicacionSedeIpsProveedorInsumoRepository repository;

    @Override
    public List<UbicacionSedeIpsProveedorInsumo> getUbicacionesConformeEstrategia(Afiliado afiliado, Insumo insumo, Long idSedeIpsExcluir,
            int numeroPagina) {
        final Long epsId = afiliado.getEps().getId();
        final Long insumoId = insumo.getId();
        
        return (this.repository.getSedesIpsNivelNacional(epsId, insumoId, idSedeIpsExcluir, numeroPagina));
    }
}
