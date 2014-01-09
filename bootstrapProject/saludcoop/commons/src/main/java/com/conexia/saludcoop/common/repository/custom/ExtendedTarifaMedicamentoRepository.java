package com.conexia.saludcoop.common.repository.custom;

import java.util.Date;

import com.conexia.saludcoop.common.entity.view.TarifaMedicamentoView;

/**
 * Interfaz para lógica personalizada de repositorio de tarifa de medicamento.
 * 
 * @author Sebastián Matienzo
 */
public interface ExtendedTarifaMedicamentoRepository {
	
	/**
	 * Obtiene la tarifa de un medicamento (sea el valor, o los datos para calcularla).
	 * 
	 * @param sedeIpsId Identificador de la sede de Ips.
	 * @param medicamentoId Identificador del medicamento.
	 * @param servicioId Identificador del servicio.
	 * @param especialidadId Identificador de la especialidad.
	 * @param fechaSolicitud Fecha de la solicitud.
	 * @return Tarifa obtenida.
	 */
	public TarifaMedicamentoView getTarifaMedicamento(final Long sedeIpsId, final Long medicamentoId,
			final Long servicioId, final Integer especialidadId, final Date fechaSolicitud);
	
}
