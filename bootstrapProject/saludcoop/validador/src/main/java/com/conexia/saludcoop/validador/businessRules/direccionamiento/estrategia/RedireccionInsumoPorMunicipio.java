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
 * Redirecciona un insumo por IPS dentro del MUNICIPIO de residencia del afiliado.
 * 
 * @author Leonardo Cruz
 * 
 */
@Component
public class RedireccionInsumoPorMunicipio  implements IEstrategiaRedireccionInsumo {
    
    /**
     * Repositorio de acceso a datos de la estrategia.
     */
    @Autowired
    private UbicacionSedeIpsProveedorInsumoRepository repository;


    @Override
    public List<UbicacionSedeIpsProveedorInsumo> getUbicacionesConformeEstrategia(Afiliado afiliado, Insumo insumo, Long idSedeIpsExcluir,
            int numeroPagina) {
        final Long idMunicipio;
        
        if (afiliado.getSedeIpsAfiliacion() != null) {
            idMunicipio = afiliado.getSedeIpsAfiliacion().getMunicipio().getId();
        } else {
            idMunicipio = afiliado.getMunicipioResidencia().getId();
        }
        
        final Long epsId = afiliado.getEps().getId();
        final Long insumooId = insumo.getId();
        
        return (this.repository.getSedesIpsPorMunicipio(epsId, insumooId, idSedeIpsExcluir, idMunicipio, numeroPagina));
    }
}
