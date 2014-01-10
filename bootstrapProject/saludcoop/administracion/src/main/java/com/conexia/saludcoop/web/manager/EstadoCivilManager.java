package com.conexia.saludcoop.web.manager;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.entity.maestro.EstadoCivil;
import com.conexia.saludcoop.common.repository.EstadoCivilRepository;
@Service
@Transactional
public class EstadoCivilManager{
	@Autowired
	private EstadoCivilRepository descRepo;
	@Autowired
	private DescriptivoManagerUtils<EstadoCivil> descManager;
	public List<DescriptivoDto> getAll() {
		Iterable<EstadoCivil> entidades = descRepo.findAll();
		return descManager.getDtos(entidades);
	}
}
