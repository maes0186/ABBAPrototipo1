package com.conexia.saludcoop.web.manager;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.entity.maestro.Genero;
import com.conexia.saludcoop.common.repository.GeneroRepository;
@Service
@Transactional
public class GeneroManager{
	@Autowired
	private GeneroRepository descRepo;
	@Autowired
	private DescriptivoManagerUtils<Genero> descManager;
	public List<DescriptivoDto> getAll() {
		Iterable<Genero> entidades = descRepo.findAll();
		return descManager.getDtos(entidades);
	}
}
