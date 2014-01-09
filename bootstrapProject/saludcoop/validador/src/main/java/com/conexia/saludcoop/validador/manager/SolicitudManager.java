package com.conexia.saludcoop.validador.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.repository.SolicitudRepository;

/**
 * Administrador de solicitudes.
 * 
 * @author Sebastián matienzo
 */
@Service
@Transactional
public class SolicitudManager {

	/**
	 * Dao de acceso a solicitudes.
	 */
	@Autowired
    private SolicitudRepository solicitudRepository;
	
	/**
	 * Verifica si una solicitud corresponde a la primera formulación del año.
	 * 
	 * @param idSolicitud Identificador de la solicitud.
	 * @return True si es la primera formulación del año; caso contrario, False.
	 */
	public boolean isPrimeraFormulacionAnio(final Long idSolicitud) {
		
		return (this.solicitudRepository.esPrimeraFormulacionAnio(idSolicitud));
	}
}
