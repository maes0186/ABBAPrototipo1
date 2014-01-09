package com.conexia.saludcoop.validador.businessRules.direccionamiento.estrategia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conexia.saludcoop.common.entity.maestro.Afiliado;
import com.conexia.saludcoop.common.entity.maestro.Procedimiento;
import com.conexia.saludcoop.common.entity.view.UbicacionSedeIpsEfectorProcedimiento;
import com.conexia.saludcoop.common.repository.UbicacionSedeIpsEfectorProcedimientoRepository;
import com.conexia.saludcoop.validador.businessRules.direccionamiento.IEstrategiaRedireccionProcedimiento;

/**
 * Redirecciona un procedimiento por IPS dentro del país.
 * 
 * @author Emmanuel Barbín
 * @author Sebastián Matienzo
 */
@Component
public class RedireccionProcedimientoPorPais implements IEstrategiaRedireccionProcedimiento {
	
	/**
	 * Repositorio de acceso a datos de la estrategia.
	 */
	@Autowired
	private UbicacionSedeIpsEfectorProcedimientoRepository repository;

	@Override
	public List<UbicacionSedeIpsEfectorProcedimiento> getUbicacionesConformeEstrategia(
			final Afiliado afiliado, final Procedimiento procedimiento, final String codigoTipoMinuta, final Long idSedeIpsExcluir,
			final boolean debeOrdenarPorValorAjustado, final int numeroPagina) {
		
		final Long epsId = afiliado.getEps().getId();
		final Long procedimientoId = procedimiento.getId();
		
		return (this.repository.getSedesIpsNivelNacional(epsId, procedimientoId, codigoTipoMinuta, idSedeIpsExcluir, 
															debeOrdenarPorValorAjustado, numeroPagina));
	}
}
