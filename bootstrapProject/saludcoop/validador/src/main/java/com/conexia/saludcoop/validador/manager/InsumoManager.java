package com.conexia.saludcoop.validador.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conexia.saludcoop.common.dto.InsumoDto;
import com.conexia.saludcoop.common.entity.maestro.Insumo;
import com.conexia.saludcoop.common.repository.InsumoRepository;

/**
 * Administrador de insumos.
 * 
 * @author Sebastián Matienzo
 */
@Component
public class InsumoManager {
	
	@Autowired
	private InsumoRepository insumoRepository;
	
	/**
	 * Obtiene un insumo a partir de un identificador.
	 * 
	 * @param id Identificador del insumo.
	 * @return Insumo obtenido.
	 */
	public InsumoDto getInsumoById(final Long id) {
		
		final Insumo insumo = this.insumoRepository.findOne(id);
		
		if (insumo != null) {
			return (insumo.toDto());
		}
		
		return (null);
	}
	
	/**
	 * Obtiene un insumo a partir de un código.
	 * 
	 * @param codigo Código del insumo.
	 * @return Insumo obtenido.
	 */
	public InsumoDto getInsumoByCodigo(final String codigo) {
		
		final Insumo insumo = this.insumoRepository.findOneByCodigoIgnoreCase(codigo);
		
		if (insumo != null) {
			return (insumo.toDto());
		}
		
		return (null);
	}
}
