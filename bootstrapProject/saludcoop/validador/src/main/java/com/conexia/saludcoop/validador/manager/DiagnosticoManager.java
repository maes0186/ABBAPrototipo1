package com.conexia.saludcoop.validador.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conexia.saludcoop.common.dto.DiagnosticoDto;
import com.conexia.saludcoop.common.entity.maestro.Diagnostico;
import com.conexia.saludcoop.common.repository.DiagnosticoRepository;

/**
 * Administrador de diagnósticos.
 * 
 * @author Sebastián Matienzo
 */
@Component
public class DiagnosticoManager {
	
	/**
	 * Repositorio de diagnósticos.
	 */
	@Autowired
	private DiagnosticoRepository diagnosticoRepository;
	
	/**
	 * Obtiene un diagnóstico a partir de su código.
	 * 
	 * @param codigo Código.
	 * @return Diagnóstico obtenido, o null si no existe.
	 */
	public DiagnosticoDto getDiagnosticoByCodigo(final String codigo) {
		
		final Diagnostico diagnostico = this.diagnosticoRepository.findOneByCodigoIgnoreCase(codigo);
		
		if (diagnostico != null) {
			return (diagnostico.toDto());
		}
		
		return (null);
	}
}
