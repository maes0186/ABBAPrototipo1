package com.conexia.saludcoop.common.repository.custom;

import java.util.List;

import com.conexia.saludcoop.common.entity.maestro.HistorialVariacionIPC;

/**
 * Interfaz para lógica personalizada de repositorio del afiliado.
 * 
 * @author Sebastián Matienzo
 */
public interface ExtentedHistorialVariacionIPCRepository {
	
	/**
	 * Obtiene todas las variaciones de IPC a partir de un año dado.
	 * 
	 * @param anio Año a partir del cual buscar (inclusive).
	 * @return Todas las variaciones halladas.
	 */
	public List<HistorialVariacionIPC> getVariacionesDesdeAnio(final Integer anio);
	
}
