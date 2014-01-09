package com.conexia.saludcoop.common.repository;

import org.springframework.data.repository.CrudRepository;

import com.conexia.saludcoop.common.entity.maestro.TipoMinuta;


/**
 * @author ebarbin
 *
 */
public interface TipoMinutaRepository extends CrudRepository<TipoMinuta, Long> {
	public static final String CAPITADO = "CA";
	public static final String EVENTO = "EV";
}
