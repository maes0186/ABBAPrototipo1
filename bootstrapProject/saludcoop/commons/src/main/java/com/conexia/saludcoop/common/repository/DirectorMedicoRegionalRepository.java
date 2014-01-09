package com.conexia.saludcoop.common.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.conexia.saludcoop.common.entity.maestro.DirectorMedicoRegional;


/**
 * @author ebarbin
 *
 */
public interface DirectorMedicoRegionalRepository extends CrudRepository<DirectorMedicoRegional, Long> {

	List<DirectorMedicoRegional> findByRegionalId(Long id);
}
