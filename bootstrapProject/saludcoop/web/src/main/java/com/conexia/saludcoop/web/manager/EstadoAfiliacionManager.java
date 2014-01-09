package com.conexia.saludcoop.web.manager;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.entity.maestro.EstadoAfiliacion;
import com.conexia.saludcoop.common.repository.EstadoAfiliacionRepository;
@Service
@Transactional
public class EstadoAfiliacionManager{
	@Autowired
	private EstadoAfiliacionRepository descRepo;
	@Autowired
	private DescriptivoManagerUtils<EstadoAfiliacion> descManager;
	public List<DescriptivoDto> getAll() {
		Iterable<EstadoAfiliacion> entidades = descRepo.findAll();
		return descManager.getDtos(entidades);
	}
}
