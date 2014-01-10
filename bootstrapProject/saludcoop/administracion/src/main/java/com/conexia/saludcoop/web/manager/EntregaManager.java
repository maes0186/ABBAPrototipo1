package com.conexia.saludcoop.web.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.conexia.saludcoop.common.entity.transaccional.Entrega;
import com.conexia.saludcoop.common.repository.EntregaRepository;

@Service
public class EntregaManager extends GeneralManager {
	
	@Autowired
	private EntregaRepository entregaRepo;
	
	public List<Entrega> checkEntregas(Long nroItem){
		return entregaRepo.findBySolicitudMedicamentoSolicitudItemNroItem(nroItem);
	}
}
