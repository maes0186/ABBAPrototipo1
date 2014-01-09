package com.conexia.saludcoop.validador.manager;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conexia.saludcoop.common.entity.maestro.HistorialVariacionIPC;
import com.conexia.saludcoop.common.repository.HistorialVariacionIPCRepository;

/**
 * Administrador de historial de variación de IPC.
 * 
 * @author Sebastián Matienzo
 */
@Component
public class HistorialVariacionIPCManager {
	
	/**
	 * Repositorio de histórico de SMLDV.
	 */
	@Autowired
	private HistorialVariacionIPCRepository historialVariacionIPCRepository;
	
	/**
	 * Obtiene un mapa con las variaciones por año de IPC.
	 * 
	 * @param anio Año para el cual buscar.
	 * @return Mapa con variaciones.
	 */
	public Map<Integer, BigDecimal> getVariacionesDesdeAnio(final Integer anio) {
		
		final Map<Integer, BigDecimal> dtos = new HashMap<Integer, BigDecimal>();
		
		final List<HistorialVariacionIPC> historial = this.historialVariacionIPCRepository.getVariacionesDesdeAnio(anio);
		
		if (historial == null) {
			throw new IllegalArgumentException("No existe el valor del salario SMLDV para el año " + anio + ".");
		}
		
		for (final HistorialVariacionIPC item : historial) {
			 dtos.put(item.getAnio(), new BigDecimal(item.getValor()));
		}
		
		return (dtos);
	}
}
