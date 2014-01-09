package com.conexia.saludcoop.web.manager;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.entity.maestro.Lateralidad;
import com.conexia.saludcoop.common.repository.LateralidadRepository;
@Service
@Transactional
public class LateralidadManager{
	@Autowired
	private LateralidadRepository descRepo;
	@Autowired
	private DescriptivoManagerUtils<Lateralidad> descManager;
	public List<DescriptivoDto> getAll() {
		Iterable<Lateralidad> entidades = descRepo.findAll();
		return descManager.getDtos(entidades);
	}
}
