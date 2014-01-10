package com.conexia.saludcoop.web.manager;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.entity.maestro.Zona;
import com.conexia.saludcoop.common.repository.ZonaRepository;
@Service
@Transactional
public class ZonaManager{
	@Autowired
	private ZonaRepository descRepo;
	@Autowired
	private DescriptivoManagerUtils<Zona> descManager;
	public List<DescriptivoDto> getAll() {
		Iterable<Zona> entidades = descRepo.findAll();
		return descManager.getDtos(entidades);
	}
}
