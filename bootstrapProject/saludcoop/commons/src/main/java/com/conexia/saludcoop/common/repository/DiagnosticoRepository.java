package com.conexia.saludcoop.common.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.conexia.saludcoop.common.entity.maestro.Diagnostico;

public interface DiagnosticoRepository extends PagingAndSortingRepository<Diagnostico, Long> {

	public Page<Diagnostico> findByCodigoLikeAndDescripcionLikeAllIgnoreCase(String codigo, String descripcion, Pageable p);
	public Diagnostico findOneByCodigoIgnoreCase(String codigo);
	
}
