package com.conexia.saludcoop.validador.businessRules.direccionamiento;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conexia.saludcoop.common.dto.UbicacionSedeIpsEfectorProcedimientoDto;
import com.conexia.saludcoop.common.entity.maestro.Afiliado;
import com.conexia.saludcoop.common.entity.maestro.Procedimiento;
import com.conexia.saludcoop.common.entity.view.UbicacionSedeIpsEfectorProcedimiento;
import com.conexia.saludcoop.common.repository.TipoMinutaRepository;
import com.conexia.saludcoop.validador.businessRules.direccionamiento.estrategia.RedireccionProcedimientoPorMunicipio;
import com.conexia.saludcoop.validador.businessRules.direccionamiento.estrategia.RedireccionProcedimientoPorPais;
import com.conexia.saludcoop.validador.businessRules.direccionamiento.estrategia.RedireccionProcedimientoPorRegional;
import com.conexia.saludcoop.validador.businessRules.direccionamiento.estrategia.RedireccionProcedimientoPorSeccional;

/**
 * Estrategia genérica de redireccionamiento de procedimiento.
 * 
 * @author Emmanuel Barbín
 * @author Sebastián Matienzo
 */
@Component
public class RedireccionProcedimiento {

	/**
	 * Estrategia de redirección por municipio.
	 */
	@Autowired
	private RedireccionProcedimientoPorMunicipio redireccionPorMunicipio;

	/**
	 * Estrategia de redirección por seccional.
	 */
	@Autowired
	private RedireccionProcedimientoPorSeccional redireccionPorSeccional;

	/**
	 * Estrategia de redirección por regional.
	 */
	@Autowired
	private RedireccionProcedimientoPorRegional redireccionPorRegional;

	/**
	 * Estrategia de redirección por país completo.
	 */
	@Autowired
	private RedireccionProcedimientoPorPais redireccionPorPais;

	/**
	 * Obtiene posibilidades de redirección de un procedimiento, conforme a la ubicación de un afiliado.
	 * 
	 * @param procedimiento Procedimiento a redireccionar.
	 * @param afiliado Afiliado para el cual redireccionar.
	 * @param idSedeIpsExcluir Identificador de la sede de Ips a excluir (null si no hay exclusión).
	 * @param cantidadUbicacionesSolicitadas Indica la cantidad de ubicaciones a obtener.
	 * @return Lista de ubicaciones de sedes de IPS a donde se puede redireccionar un procedimiento.
	 */
	public List<UbicacionSedeIpsEfectorProcedimientoDto> redireccionar(final Procedimiento procedimiento, final Afiliado afiliado, 
			final Long idSedeIpsExcluir, final int cantidadUbicacionesSolicitadas) {
		
		/* Se usa para evitar dos veces repetir la misma sede */
		final Set<Long> idsSedesYaProcesadas = new HashSet<Long>();

		final List<UbicacionSedeIpsEfectorProcedimientoDto> entidades = new ArrayList<UbicacionSedeIpsEfectorProcedimientoDto>();
		
		for (final IEstrategiaRedireccionProcedimiento estrategia : this.getEstrategiasBusqueda()) {
			entidades.addAll(this.getUbicaciones(afiliado, procedimiento, TipoMinutaRepository.CAPITADO, idSedeIpsExcluir, 
													cantidadUbicacionesSolicitadas, false, idsSedesYaProcesadas, estrategia));
	
			entidades.addAll(this.getUbicaciones(afiliado, procedimiento, null, idSedeIpsExcluir, cantidadUbicacionesSolicitadas,
													false, idsSedesYaProcesadas, estrategia));
	
			entidades.addAll(this.getUbicaciones(afiliado, procedimiento, TipoMinutaRepository.EVENTO, idSedeIpsExcluir, 
													cantidadUbicacionesSolicitadas, true, idsSedesYaProcesadas, estrategia));
		}
			
		return (entidades);
	}

	/**
	 * Obtiene las ubicaciones de posibles redirecciones conforme a la estrategia especificada.
	 * 
	 * @param afiliado Afiliado involucrado.
	 * @param procedimiento Procedimiento a redireccionar.
	 * @param codigoTipoMinuta Código de tipo de minuta (null si ninguno).
	 * @param idSedeIpsExcluir Identificador de la sede de Ips a excluir (null si no hay exclusión).
	 * @param cantidadUbicacionesSolicitadas Cantidad de ubicaciones máximas que se deben buscar.
	 * @param debeOrdenarPorValorAjustado Indica si debe ordenar por el valor ajustado, o por el valor original.
	 * @param idsSedesYaProcesadas Identificadores de sedes ya procesadas (y que no deben, por ende, repetirse).
	 * @param estrategiaRedireccion Estrategia de redirección a utilizar.
	 * @return Lista de ubicaciones de sedes de IPS a donde se puede redireccionar un procedimiento.
	 */
	public List<UbicacionSedeIpsEfectorProcedimientoDto> getUbicaciones(final Afiliado afiliado, 
			final Procedimiento procedimiento, final String codigoTipoMinuta, final Long idSedeIpsExcluir, 
			final int cantidadUbicacionesSolicitadas, final boolean debeOrdenarPorValorAjustado, final Set<Long> idsSedesYaProcesadas,
			final IEstrategiaRedireccionProcedimiento estrategiaRedireccion) {
		
		final List<UbicacionSedeIpsEfectorProcedimientoDto> resultadosHallados = new ArrayList<UbicacionSedeIpsEfectorProcedimientoDto>();
		
		if (idsSedesYaProcesadas.size() >= cantidadUbicacionesSolicitadas) {
			return (resultadosHallados);
		}
		
		int contadorPagina = 0;
		
		int cantidadResultadosHalladasEnPagina = 0;
		
		do {
			final List<UbicacionSedeIpsEfectorProcedimiento> sedesIpsHalladas = estrategiaRedireccion.getUbicacionesConformeEstrategia(
					afiliado, procedimiento, codigoTipoMinuta, idSedeIpsExcluir, debeOrdenarPorValorAjustado, contadorPagina);
			
			cantidadResultadosHalladasEnPagina = sedesIpsHalladas.size();
			
			for (final UbicacionSedeIpsEfectorProcedimiento ubicacion : sedesIpsHalladas) {
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
	private List<IEstrategiaRedireccionProcedimiento> getEstrategiasBusqueda() {
		
		final List<IEstrategiaRedireccionProcedimiento> estrategias = new ArrayList<IEstrategiaRedireccionProcedimiento>();

		estrategias.add(this.redireccionPorMunicipio);
		estrategias.add(this.redireccionPorSeccional);
		estrategias.add(this.redireccionPorRegional);
		estrategias.add(this.redireccionPorPais);
		
		return (estrategias);
	}
}
