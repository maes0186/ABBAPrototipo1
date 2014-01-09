package com.conexia.saludcoop.validador.businessRules.direccionamiento.estrategia;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.conexia.saludcoop.common.entity.maestro.Afiliado;
import com.conexia.saludcoop.common.entity.maestro.DepartamentoRegional;
import com.conexia.saludcoop.common.entity.maestro.Medicamento;
import com.conexia.saludcoop.common.entity.maestro.Municipio;
import com.conexia.saludcoop.common.entity.view.UbicacionSedeIpsProveedorMedicamento;
import com.conexia.saludcoop.common.repository.UbicacionSedeIpsProveedorMedicamentoRepository;
import com.conexia.saludcoop.validador.businessRules.direccionamiento.IEstrategiaRedireccionMedicamento;

/**
 * Redirecciona un medicamento por IPS dentro de la SECCIONAL del municipio de residencia del afiliado.
 * 
 * @author Emmanuel Barbín
 * @author Sebastián Matienzo
 */
@Component
public class RedireccionMedicamentoPorSeccional implements IEstrategiaRedireccionMedicamento {
	
	/**
	 * Repositorio de acceso a datos de la estrategia.
	 */
	@Autowired
	private UbicacionSedeIpsProveedorMedicamentoRepository repository;

	@Override
	public List<UbicacionSedeIpsProveedorMedicamento> getUbicacionesConformeEstrategia(
			final Afiliado afiliado, final Medicamento medicamento, final Long idSedeIpsExcluir, final int numeroPagina) {
		
		final Long epsId = afiliado.getEps().getId();
		final Long medicamentoId = medicamento.getId();
		
		final Municipio municipio;
		
		if (afiliado.getSedeIpsAfiliacion() != null) {
			municipio = afiliado.getSedeIpsAfiliacion().getMunicipio();
		} else {
			municipio = afiliado.getMunicipioResidencia();
		}
		
		final List<Long> divisionesSeccionalesId = new ArrayList<Long>();
		
		for (DepartamentoRegional regional : municipio.getDepartamento().getRegionales()) {
			divisionesSeccionalesId.add(regional.getRegional().getDivisionSeccional().getId());
		}
		
		return (this.repository.getSedesIpsPorDivisionSeccional(epsId, medicamentoId, idSedeIpsExcluir, 
																	divisionesSeccionalesId, numeroPagina));
	}
}
