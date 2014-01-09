package com.conexia.saludcoop.common.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.conexia.saludcoop.common.entity.transaccional.Entrega;
/**
 * 
 * @author prodas
 *
 */
public interface EntregaRepository extends CrudRepository<Entrega, Long> {
	
	List<Entrega> findBySolicitudMedicamentoSolicitudItemNroItem(Long nroItem);	
	
}
