package com.conexia.saludcoop.web.manager;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.entity.maestro.RazonEstadoAfiliacion;
import com.conexia.saludcoop.common.repository.RazonEstadoAfiliacionRepository;
@Service
@Transactional
public class RazonEstadoAfiliacionManager{
	@Autowired
	private RazonEstadoAfiliacionRepository descRepo;
	@Autowired
	private DescriptivoManagerUtils<RazonEstadoAfiliacion> descManager;
	public List<DescriptivoDto> getAll() {
		Iterable<RazonEstadoAfiliacion> entidades = descRepo.findAll();
		return descManager.getDtos(entidades);
	}
}
