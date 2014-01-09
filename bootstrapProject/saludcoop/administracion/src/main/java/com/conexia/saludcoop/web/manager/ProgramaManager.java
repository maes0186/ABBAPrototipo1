package com.conexia.saludcoop.web.manager;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.entity.maestro.Programa;
import com.conexia.saludcoop.common.repository.ProgramaRepository;
@Service
@Transactional
public class ProgramaManager{
	@Autowired
	private ProgramaRepository descRepo;
	@Autowired
	private DescriptivoManagerUtils<Programa> descManager;
	public List<DescriptivoDto> getAll() {
		Iterable<Programa> entidades = descRepo.findAll();
		return descManager.getDtos(entidades);
	}
}
