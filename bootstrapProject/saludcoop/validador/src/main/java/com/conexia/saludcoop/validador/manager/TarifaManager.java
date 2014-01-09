package com.conexia.saludcoop.validador.manager;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.TarifaProcedimientoMedicamentoInsumoDto;
import com.conexia.saludcoop.common.entity.view.TarifaMedicamentoView;
import com.conexia.saludcoop.common.entity.view.TarifaProcedimientoView;
import com.conexia.saludcoop.common.repository.TarifaMedicamentoRepository;
import com.conexia.saludcoop.common.repository.TarifaProcedimientoRepository;
import com.conexia.saludcoop.validador.determinador.dto.TipoItemSolicitado;

/**
 * Administrador de tarifas.
 * 
 * @author Sebastián matienzo
 */
@Service
@Transactional
public class TarifaManager {

	/**
	 * Dao de acceso a tarifas de procedimientos.
	 */
	@Autowired
    private TarifaProcedimientoRepository tarifaProcedimientoRepository;

	/**
	 * Dao de acceso a tarifas de medicamentos.
	 */
	@Autowired
    private TarifaMedicamentoRepository tarifaMedicamentoRepository;
	
	/**
	 * Obtiene una tarifa para un procedimiento, medicamento, o insumo.
	 * 
	 * @param itemId Identificador del ítem.
	 * @param tipoItem Tipo del ítem.
	 * @param sedeIpsId Identificador de sede de IPS.
	 * @param especialidadId Identificador de especialidad.
	 * @param servicioId Identificador de servicio.
	 * @param fechaSolicitud Fecha de solicitud.
	 * @return Tarifa obtenida, o null si no existe.
	 */
	public TarifaProcedimientoMedicamentoInsumoDto getTarifa(final Long itemId, final TipoItemSolicitado tipoItem, 
			final Long sedeIpsId, final Integer especialidadId, final Long servicioId, final Date fechaSolicitud) {
		
		if (tipoItem == null) {
			throw new IllegalArgumentException("El tipo de ítem es inválido.");
		}
		
		switch (tipoItem) {
		
			case PROCEDIMIENTO:
				final TarifaProcedimientoView tarifaProc = this.tarifaProcedimientoRepository.getTarifaProcedimiento(sedeIpsId, 
						itemId, servicioId, especialidadId, fechaSolicitud);
				
				return ((tarifaProc != null) ? tarifaProc.toDto() : null);
				
			case MEDICAMENTO:
				final TarifaMedicamentoView tarifaMed = this.tarifaMedicamentoRepository.getTarifaMedicamento(sedeIpsId, itemId, 
						servicioId, especialidadId, fechaSolicitud);
				
				return ((tarifaMed != null) ? tarifaMed.toDto() : null);
			
			case INSUMO:
				/* Conforme a lo visto con Darío Camarro, por el momento los insumos no tiene costo alguno */
				return (null);
				
			default:
				throw new IllegalArgumentException("El tipo de ítem es inválido.");
		}
	}
}
