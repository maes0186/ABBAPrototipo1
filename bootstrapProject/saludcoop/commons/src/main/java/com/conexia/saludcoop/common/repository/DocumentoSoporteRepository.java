package com.conexia.saludcoop.common.repository;

import org.springframework.data.repository.CrudRepository;

import com.conexia.saludcoop.common.entity.transaccional.DocumentoSoporte;

public interface DocumentoSoporteRepository extends
		CrudRepository<DocumentoSoporte, Long> {

}
