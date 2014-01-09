package com.conexia.saludcoop.common.repository.custom;

/**
 * Interfaz para lógica personalizada de repositorio de la solicitud.
 * 
 * @author Sebastián Matienzo
 */
public interface ExtendedSolicitudRepository {
	
	/**
	 * Verifica si una solicitud es la primera formulación del año.
	 * 
	 * @param solicitudId Identificador de la solicitud.
	 * @return True si es la primera formulación del año.; caso contrario, False.
	 */
	public boolean esPrimeraFormulacionAnio(final Long solicitudId);
	
}
