package com.conexia.saludcoop.common.repository.custom;

import java.util.Date;

import com.conexia.saludcoop.common.entity.view.TarifaProcedimientoView;

/**
 * Interfaz para lógica personalizada de repositorio de tarifa de procedimiento.
 * 
 * @author Sebastián Matienzo
 */
public interface ExtendedTarifaProcedimientoRepository {
	
	/**
	 * Obtiene la tarifa de un procedimiento (sea el valor, o los datos para calcularla).
	 * 
	 * @param sedeIpsId Identificador de la sede de Ips.
	 * @param procedimientoId Identificador del procedimiento.
	 * @param servicioId Identificador del servicio.
	 * @param especialidadId Identificador de la especialidad.
	 * @param fechaSolicitud Fecha de la solicitud.
	 * @return Tarifa obtenida.
	 */
	public TarifaProcedimientoView getTarifaProcedimiento(final Long sedeIpsId, final Long procedimientoId,
			final Long servicioId, final Integer especialidadId, final Date fechaSolicitud);
	
}
