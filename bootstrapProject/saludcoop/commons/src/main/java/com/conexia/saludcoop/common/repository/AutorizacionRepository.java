package com.conexia.saludcoop.common.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.conexia.saludcoop.common.entity.transaccional.Autorizacion;

public interface AutorizacionRepository extends PagingAndSortingRepository<Autorizacion, Long> {

	public Autorizacion findOneByNumeroAutorizacion(String numero);
}
