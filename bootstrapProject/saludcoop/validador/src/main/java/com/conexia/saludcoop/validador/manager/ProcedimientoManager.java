package com.conexia.saludcoop.validador.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conexia.saludcoop.common.dto.ProcedimientoDto;
import com.conexia.saludcoop.common.entity.maestro.Procedimiento;
import com.conexia.saludcoop.common.repository.ProcedimientoRepository;

/**
 * Administrador de procedimientos.
 * 
 * @author Sebastián Matienzo
 */
@Component
public class ProcedimientoManager {
	
	@Autowired
	private ProcedimientoRepository procedimientoRepository;
	
	/**
	 * Obtiene un procedimiento a partir de un identificador.
	 * 
	 * @param id Identificador del procedimiento.
	 * @return Procedimiento obtenido.
	 */
	public ProcedimientoDto getProcedimientoById(final Long id) {
		
		final Procedimiento procedimiento = this.procedimientoRepository.findOne(id);
		
		if (procedimiento != null) {
			return (procedimiento.toDto());
		}
		
		return (null);
	}
	
	/**
	 * Obtiene un procedimiento a partir de un código.
	 * 
	 * @param codigo Código del procedimiento.
	 * @return Procedimiento obtenido.
	 */
	public ProcedimientoDto getProcedimientoByCodigo(final String codigo) {
		
		final Procedimiento procedimiento = this.procedimientoRepository.findOneByCodigoIgnoreCase(codigo);
		
		if (procedimiento != null) {
			return (procedimiento.toDto());
		}
		
		return (null);
	}
}
