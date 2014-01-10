package com.conexia.saludcoop.web.manager;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conexia.saludcoop.common.dto.ticket.TicketCabeceraDto;
import com.conexia.saludcoop.common.entity.ticket.TicketCabecera;
import com.conexia.saludcoop.common.repository.TicketCabeceraRepository;

@Component
public class TicketManager extends GeneralManager {

	@Autowired
	private TicketCabeceraRepository repo;
	
	public TicketCabeceraDto getTicketByAutorizacionIdNroEntrega(Long autorizacionId, Integer nroEntrega){
		return repo.findOneByAutorizacionNumeroAutorizacionAndNumeroDeEntrega(autorizacionId, nroEntrega).toDto();
	}
	
	private List<TicketCabeceraDto> listToDtos(List<TicketCabecera> tickets){
		List<TicketCabeceraDto> ticketsDtos = CollectionUtils.isEmpty(tickets)?null:new Vector<TicketCabeceraDto>(tickets.size());
		for (TicketCabecera ticketCabecera : tickets) {
			ticketsDtos.add(ticketCabecera.toDto());
		} 
		return ticketsDtos;
	}

	public void updateDatosImpresion(TicketCabeceraDto ticket) {
		TicketCabecera t = repo.findOne(ticket.getId());
		if (t.getFechaImpresion() == null)
			t.setFechaImpresion(new Date());
		t.setCantidadCopias(t.getCantidadCopias()+1);
		repo.save(t);
	}
	
}
