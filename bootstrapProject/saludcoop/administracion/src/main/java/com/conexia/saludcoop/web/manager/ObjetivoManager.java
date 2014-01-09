package com.conexia.saludcoop.web.manager;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.entity.maestro.Objetivo;
import com.conexia.saludcoop.common.repository.ObjetivoRepository;
@Service
@Transactional
public class ObjetivoManager{
	@Autowired
	private ObjetivoRepository descRepo;
	@Autowired
	private DescriptivoManagerUtils<Objetivo> descManager;
	public List<DescriptivoDto> getAll() {
		Iterable<Objetivo> entidades = descRepo.findAll();
		return descManager.getDtos(entidades);
	}
}
