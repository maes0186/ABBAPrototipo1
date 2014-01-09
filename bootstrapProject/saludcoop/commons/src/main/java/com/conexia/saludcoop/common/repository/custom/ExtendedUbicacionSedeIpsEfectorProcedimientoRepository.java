package com.conexia.saludcoop.common.repository.custom;

import java.util.List;

import com.conexia.saludcoop.common.entity.view.UbicacionSedeIpsEfectorProcedimiento;

/**
 * Interfaz para lógica personalizada de repositorio de ubicacionSedeIpsEfectorProcedimiento.
 * 
 * @author Sebastián Matienzo
 */
public interface ExtendedUbicacionSedeIpsEfectorProcedimientoRepository {
	
	/**
	 * Obtiene las sedes de IPS existentes en un municipio, que proveen el servicio.
	 * 
	 * @param epsId Identificador de la EPS correspondiente.
	 * @param procedimientoId Identificador del procedimiento.
	 * @param tipoMinutaCodigo Código del tipo de minuta (o null si ninguno).
	 * @param sedeIpsExcluidaId Identificador de la sede de IPS a excluir de los resultados (o null si debe evaluar todas).
	 * @param municipioId Identificador del municipio en el cual deben estar las IPS.
	 * @param debeOrdenarPorValorAjustado Indica si debe ordenar por el valor ajustado, o por el valor original.
	 * @param numeroPagina Número de página de resultados (pagina de a 10 registros automáticamente). Primera página = 0.
	 * @return Lista de sedes de IPS efectoras, con el detalle del contrato correspondiente.
	 */
	public List<UbicacionSedeIpsEfectorProcedimiento> getSedesIpsPorMunicipio(final Long epsId, final Long procedimientoId,
			final String tipoMinutaCodigo, final Long sedeIpsExcluidaId, final Long municipioId, 
			final boolean debeOrdenarPorValorAjustado, final int numeroPagina);
	
	/**
	 * Obtiene las sedes de IPS existentes en una división seccional, que proveen el servicio.
	 * 
	 * @param epsId Identificador de la EPS correspondiente.
	 * @param procedimientoId Identificador del procedimiento.
	 * @param tipoMinutaCodigo Código del tipo de minuta (o null si ninguno).
	 * @param sedeIpsExcluidaId Identificador de la sede de IPS a excluir de los resultados (o null si debe evaluar todas).
	 * @param divisionesSeccionalesId Lista de ids de divisiones de seccional en las cuales deben estar las IPS.
	 * @param debeOrdenarPorValorAjustado Indica si debe ordenar por el valor ajustado, o por el valor original.
	 * @param numeroPagina Número de página de resultados (pagina de a 10 registros automáticamente). Primera página = 0.
	 * @return Lista de sedes de IPS efectoras, con el detalle del contrato correspondiente.
	 */
	public List<UbicacionSedeIpsEfectorProcedimiento> getSedesIpsPorDivisionSeccional(final Long epsId, final Long procedimientoId,
			final String tipoMinutaCodigo, final Long sedeIpsExcluidaId, final List<Long> divisionesSeccionalesId, 
			final boolean debeOrdenarPorValorAjustado, final int numeroPagina);
	
	/**
	 * Obtiene las sedes de IPS existentes en una región, que proveen el servicio.
	 * 
	 * @param epsId Identificador de la EPS correspondiente.
	 * @param procedimientoId Identificador del procedimiento.
	 * @param tipoMinutaCodigo Código del tipo de minuta (o null si ninguno).
	 * @param sedeIpsExcluidaId Identificador de la sede de IPS a excluir de los resultados (o null si debe evaluar todas).
	 * @param regionalesId Lista de ids de regionales en las cuales deben estar las IPS.
	 * @param debeOrdenarPorValorAjustado Indica si debe ordenar por el valor ajustado, o por el valor original.
	 * @param numeroPagina Número de página de resultados (pagina de a 10 registros automáticamente). Primera página = 0.
	 * @return Lista de sedes de IPS efectoras, con el detalle del contrato correspondiente.
	 */
	public List<UbicacionSedeIpsEfectorProcedimiento> getSedesIpsPorRegional(final Long epsId, final Long procedimientoId,
			final String tipoMinutaCodigo, final Long sedeIpsExcluidaId, final List<Long> regionalesId, 
			final boolean debeOrdenarPorValorAjustado, final int numeroPagina);
	
	/**
	 * Obtiene las sedes de IPS existentes en todo el país, que proveen el servicio.
	 * 
	 * @param epsId Identificador de la EPS correspondiente.
	 * @param procedimientoId Identificador del procedimiento.
	 * @param tipoMinutaCodigo Código del tipo de minuta (o null si ninguno).
	 * @param sedeIpsExcluidaId Identificador de la sede de IPS a excluir de los resultados (o null si debe evaluar todas).
	 * @param debeOrdenarPorValorAjustado Indica si debe ordenar por el valor ajustado, o por el valor original.
	 * @param numeroPagina Número de página de resultados (pagina de a 10 registros automáticamente). Primera página = 0.
	 * @return Lista de sedes de IPS efectoras, con el detalle del contrato correspondiente.
	 */
	public List<UbicacionSedeIpsEfectorProcedimiento> getSedesIpsNivelNacional(final Long epsId, final Long procedimientoId,
			final String tipoMinutaCodigo, final Long sedeIpsExcluidaId, final boolean debeOrdenarPorValorAjustado,
			final int numeroPagina);

}
