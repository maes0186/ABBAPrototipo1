package com.conexia.saludcoop.web.manager;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.entity.maestro.RegimenAfiliacion;
import com.conexia.saludcoop.common.repository.RegimenAfiliacionRepository;
@Service
@Transactional
public class RegimenAfiliacionManager{
	@Autowired
	private RegimenAfiliacionRepository descRepo;
	@Autowired
	private DescriptivoManagerUtils<RegimenAfiliacion> descManager;
	public List<DescriptivoDto> getAll() {
		Iterable<RegimenAfiliacion> entidades = descRepo.findAll();
		return descManager.getDtos(entidades);
	}
}
