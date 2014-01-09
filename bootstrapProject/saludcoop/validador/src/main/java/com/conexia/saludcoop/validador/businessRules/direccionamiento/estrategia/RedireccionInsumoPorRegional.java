package com.conexia.saludcoop.validador.businessRules.direccionamiento.estrategia;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conexia.saludcoop.common.entity.maestro.Afiliado;
import com.conexia.saludcoop.common.entity.maestro.DepartamentoRegional;
import com.conexia.saludcoop.common.entity.maestro.Insumo;
import com.conexia.saludcoop.common.entity.maestro.Municipio;
import com.conexia.saludcoop.common.entity.view.UbicacionSedeIpsProveedorInsumo;
import com.conexia.saludcoop.common.repository.UbicacionSedeIpsProveedorInsumoRepository;
import com.conexia.saludcoop.validador.businessRules.direccionamiento.IEstrategiaRedireccionInsumo;

/**
 * Redirecciona un insumo por IPS dentro de la REGIONAL del municipio de residencia del afiliado.
 * 
 * @author Leonardo Cruz
 */
@Component
public class RedireccionInsumoPorRegional implements IEstrategiaRedireccionInsumo {
    
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
        
        final Municipio municipio;
        
        if (afiliado.getSedeIpsAfiliacion() != null) {
            municipio = afiliado.getSedeIpsAfiliacion().getMunicipio();
        } else {
            municipio = afiliado.getMunicipioResidencia();
        }
        
        final List<Long> regionalesId = new ArrayList<Long>();
        
        for (DepartamentoRegional regional : municipio.getDepartamento().getRegionales()) {
            regionalesId.add(regional.getRegional().getId());
        }
        
        return (this.repository.getSedesIpsPorRegional(epsId, insumoId, idSedeIpsExcluir, regionalesId, numeroPagina));
    }
}

