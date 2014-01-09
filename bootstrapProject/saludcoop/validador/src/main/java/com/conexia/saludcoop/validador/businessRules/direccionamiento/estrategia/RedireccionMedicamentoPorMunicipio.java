package com.conexia.saludcoop.validador.businessRules.direccionamiento.estrategia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conexia.saludcoop.common.entity.maestro.Afiliado;
import com.conexia.saludcoop.common.entity.maestro.Medicamento;
import com.conexia.saludcoop.common.entity.view.UbicacionSedeIpsProveedorMedicamento;
import com.conexia.saludcoop.common.repository.UbicacionSedeIpsProveedorMedicamentoRepository;
import com.conexia.saludcoop.validador.businessRules.direccionamiento.IEstrategiaRedireccionMedicamento;

/**
 * Redirecciona un medicamento por IPS dentro del MUNICIPIO de residencia del afiliado.
 * 
 * @author Emmanuel Barbín
 * @author Sebastián Matienzo
 */
@Component
public class RedireccionMedicamentoPorMunicipio implements IEstrategiaRedireccionMedicamento {
	
	/**
	 * Repositorio de acceso a datos de la estrategia.
	 */
	@Autowired
	private UbicacionSedeIpsProveedorMedicamentoRepository repository;

	@Override
	public List<UbicacionSedeIpsProveedorMedicamento> getUbicacionesConformeEstrategia(
			final Afiliado afiliado, final Medicamento medicamento, final Long idSedeIpsExcluir, final int numeroPagina) {
		
		final Long idMunicipio;
		
		if (afiliado.getSedeIpsAfiliacion() != null) {
			idMunicipio = afiliado.getSedeIpsAfiliacion().getMunicipio().getId();
		} else {
			idMunicipio = afiliado.getMunicipioResidencia().getId();
		}
		
		final Long epsId = afiliado.getEps().getId();
		final Long medicamentoId = medicamento.getId();
		
		return (this.repository.getSedesIpsPorMunicipio(epsId, medicamentoId, idSedeIpsExcluir, idMunicipio, numeroPagina));
	}
}
