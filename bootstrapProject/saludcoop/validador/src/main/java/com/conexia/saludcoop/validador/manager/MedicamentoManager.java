package com.conexia.saludcoop.validador.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conexia.saludcoop.common.dto.MedicamentoDto;
import com.conexia.saludcoop.common.entity.maestro.Medicamento;
import com.conexia.saludcoop.common.repository.MedicamentoRepository;

/**
 * Administrador de medicamentos.
 * 
 * @author Sebastián Matienzo
 */
@Component
public class MedicamentoManager {
	
	@Autowired
	private MedicamentoRepository medicamentoRepository;
	
	/**
	 * Obtiene un medicamento a partir de un identificador.
	 * 
	 * @param id Identificador del medicamento.
	 * @return Medicamento obtenido.
	 */
	public MedicamentoDto getMedicamentoById(final Long id) {
		
		final Medicamento medicamento = this.medicamentoRepository.findOne(id);
		
		if (medicamento != null) {
			return (medicamento.toDto());
		}
		
		return (null);
	}
	
	/**
	 * Obtiene un medicamento a partir de un código.
	 * 
	 * @param codigo Código del medicamento.
	 * @return Medicamento obtenido.
	 */
	public MedicamentoDto getMedicamentoByCodigo(final String codigo) {
		
		final Medicamento medicamento = this.medicamentoRepository.findOneByCodigoIgnoreCase(codigo);
		
		if (medicamento != null) {
			return (medicamento.toDto());
		}
		
		return (null);
	}
}
