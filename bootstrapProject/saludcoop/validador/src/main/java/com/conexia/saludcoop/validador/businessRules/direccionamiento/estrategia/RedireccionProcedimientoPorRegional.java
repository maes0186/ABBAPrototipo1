package com.conexia.saludcoop.validador.businessRules.direccionamiento.estrategia;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.conexia.saludcoop.common.entity.maestro.Afiliado;
import com.conexia.saludcoop.common.entity.maestro.DepartamentoRegional;
import com.conexia.saludcoop.common.entity.maestro.Municipio;
import com.conexia.saludcoop.common.entity.maestro.Procedimiento;
import com.conexia.saludcoop.common.entity.view.UbicacionSedeIpsEfectorProcedimiento;
import com.conexia.saludcoop.common.repository.UbicacionSedeIpsEfectorProcedimientoRepository;
import com.conexia.saludcoop.validador.businessRules.direccionamiento.IEstrategiaRedireccionProcedimiento;

/**
 * Redirecciona un procedimiento por IPS dentro de la REGIONAL del municipio de residencia del afiliado.
 * 
 * @author Emmanuel Barbín
 * @author Sebastián Matienzo
 */
@Component
public class RedireccionProcedimientoPorRegional implements IEstrategiaRedireccionProcedimiento {
	
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
		
		return (this.repository.getSedesIpsPorRegional(epsId, procedimientoId, codigoTipoMinuta, 
				idSedeIpsExcluir, regionalesId, debeOrdenarPorValorAjustado, numeroPagina));
	}
}
