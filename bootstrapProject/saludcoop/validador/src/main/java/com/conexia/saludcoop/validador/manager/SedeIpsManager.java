package com.conexia.saludcoop.validador.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conexia.saludcoop.common.dto.SedeIpsDto;
import com.conexia.saludcoop.common.entity.maestro.SedeIps;
import com.conexia.saludcoop.common.repository.SedeIpsRepository;

/**
 * Administrador de sedes de Ips.
 * 
 * @author Sebastián Matienzo
 */
@Component
public class SedeIpsManager {
	
	@Autowired
	private SedeIpsRepository sedeIpsRepository;
	
	/**
	 * Obtiene una sede de Ips a partir de su identificador.
	 * 
	 * @param id Identificador de la sede de Ips.
	 * @return Sede de Ips obtenida.
	 */
	public SedeIpsDto getSedeIps(final Long sedeIpsId) {
		
		final SedeIps sedeIps = this.sedeIpsRepository.findOne(sedeIpsId);
		
		if (sedeIps != null) {
			return (sedeIps.toDto());
		}
		
		return (null);
	}
}
