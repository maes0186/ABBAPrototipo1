package com.conexia.saludcoop.common.repository;

import org.springframework.data.repository.CrudRepository;

import com.conexia.saludcoop.common.entity.ticket.TicketCabecera;

public interface TicketCabeceraRepository  extends CrudRepository<TicketCabecera, Integer>{
	
	TicketCabecera findOneByAutorizacionNumeroAutorizacionAndNumeroDeEntrega(Long autorizacionId, Integer numeroDeEntrega);
	
}
