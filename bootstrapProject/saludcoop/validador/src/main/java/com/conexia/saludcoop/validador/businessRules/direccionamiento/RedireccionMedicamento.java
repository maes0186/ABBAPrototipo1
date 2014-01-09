package com.conexia.saludcoop.validador.businessRules.direccionamiento;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conexia.saludcoop.common.dto.UbicacionSedeIpsProveedorMedicamentoDto;
import com.conexia.saludcoop.common.entity.maestro.Afiliado;
import com.conexia.saludcoop.common.entity.maestro.Medicamento;
import com.conexia.saludcoop.common.entity.view.UbicacionSedeIpsProveedorMedicamento;
import com.conexia.saludcoop.validador.businessRules.direccionamiento.estrategia.RedireccionMedicamentoPorMunicipio;
import com.conexia.saludcoop.validador.businessRules.direccionamiento.estrategia.RedireccionMedicamentoPorPais;
import com.conexia.saludcoop.validador.businessRules.direccionamiento.estrategia.RedireccionMedicamentoPorRegional;
import com.conexia.saludcoop.validador.businessRules.direccionamiento.estrategia.RedireccionMedicamentoPorSeccional;

/**
 * Estrategia genérica de redireccionamiento de medicamento.
 * 
 * @author Emmanuel Barbín
 * @author Sebastián Matienzo
 */
@Component
public class RedireccionMedicamento {

	/**
	 * Estrategia de redirección por municipio.
	 */
	@Autowired
	private RedireccionMedicamentoPorMunicipio redireccionPorMunicipio;

	/**
	 * Estrategia de redirección por seccional.
	 */
	@Autowired
	private RedireccionMedicamentoPorSeccional redireccionPorSeccional;

	/**
	 * Estrategia de redirección por regional.
	 */
	@Autowired
	private RedireccionMedicamentoPorRegional redireccionPorRegional;

	/**
	 * Estrategia de redirección por país completo.
	 */
	@Autowired
	private RedireccionMedicamentoPorPais redireccionPorPais;

	/**
	 * Obtiene posibilidades de redirección de un medicamento, conforme a la ubicación de un afiliado.
	 * 
	 * @param procedimiento Medicamento a redireccionar.
	 * @param afiliado Afiliado para el cual redireccionar.
	 * @param idSedeIpsExcluir Identificador de la sede de Ips a excluir (null si no hay exclusión).
	 * @param cantidadUbicacionesSolicitadas Indica la cantidad de ubicaciones a obtener.
	 * @return Lista de ubicaciones de sedes de IPS a donde se puede redireccionar un procedimiento.
	 */
	public List<UbicacionSedeIpsProveedorMedicamentoDto> redireccionar(final Medicamento medicamento, final Afiliado afiliado, 
			final Long idSedeIpsExcluir, final int cantidadUbicacionesSolicitadas) {
		
		/* Se usa para evitar dos veces repetir la misma sede */
		final Set<Long> idsSedesYaProcesadas = new HashSet<Long>();

		final List<UbicacionSedeIpsProveedorMedicamentoDto> entidades = new ArrayList<UbicacionSedeIpsProveedorMedicamentoDto>();
		
		for (final IEstrategiaRedireccionMedicamento estrategia : this.getEstrategiasBusqueda()) {
			entidades.addAll(this.getUbicaciones(afiliado, medicamento, null, idSedeIpsExcluir, cantidadUbicacionesSolicitadas,
					idsSedesYaProcesadas, estrategia));
		}

		return (entidades);
	}

	/**
	 * Obtiene las ubicaciones de posibles redirecciones conforme a la estrategia especificada.
	 * 
	 * @param afiliado Afiliado involucrado.
	 * @param medicamento Medicamento a redireccionar.
	 * @param codigoTipoMinuta Código de tipo de minuta (null si ninguno).
	 * @param idSedeIpsExcluir Identificador de la sede de Ips a excluir (null si no hay exclusión).
	 * @param cantidadUbicacionesSolicitadas Cantidad de ubicaciones máximas que se deben buscar.
	 * @param idsSedesYaProcesadas Identificadores de sedes ya procesadas (y que no deben, por ende, repetirse).
	 * @param estrategiaRedireccion Estrategia de redirección a utilizar.
	 * @return Lista de ubicaciones de sedes de IPS a donde se puede redireccionar un procedimiento.
	 */
	public List<UbicacionSedeIpsProveedorMedicamentoDto> getUbicaciones(final Afiliado afiliado, 
			final Medicamento medicamento, final String codigoTipoMinuta, final Long idSedeIpsExcluir, 
			final int cantidadUbicacionesSolicitadas, final Set<Long> idsSedesYaProcesadas, 
			final IEstrategiaRedireccionMedicamento estrategiaRedireccion) {
		
		final List<UbicacionSedeIpsProveedorMedicamentoDto> resultadosHallados = new ArrayList<UbicacionSedeIpsProveedorMedicamentoDto>();
		
		if (idsSedesYaProcesadas.size() >= cantidadUbicacionesSolicitadas) {
			return (resultadosHallados);
		}
		
		int contadorPagina = 0;
		
		int cantidadResultadosHalladasEnPagina = 0;
		
		do {
			final List<UbicacionSedeIpsProveedorMedicamento> sedesIpsHalladas = estrategiaRedireccion.getUbicacionesConformeEstrategia(
					afiliado, medicamento, idSedeIpsExcluir, contadorPagina);
			
			cantidadResultadosHalladasEnPagina = sedesIpsHalladas.size();
			
			for (final UbicacionSedeIpsProveedorMedicamento ubicacion : sedesIpsHalladas) {
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
	private List<IEstrategiaRedireccionMedicamento> getEstrategiasBusqueda() {
		
		final List<IEstrategiaRedireccionMedicamento> estrategias = new ArrayList<IEstrategiaRedireccionMedicamento>();

		estrategias.add(this.redireccionPorMunicipio);
		estrategias.add(this.redireccionPorSeccional);
		estrategias.add(this.redireccionPorRegional);
		estrategias.add(this.redireccionPorPais);
		
		return (estrategias);
	}
}
