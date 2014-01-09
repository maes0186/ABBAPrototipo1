package com.conexia.saludcoop.web.manager;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.entity.maestro.Finalidad;
import com.conexia.saludcoop.common.repository.FinalidadRepository;
@Service
@Transactional
public class FinalidadManager{
	@Autowired
	private FinalidadRepository descRepo;
	@Autowired
	private DescriptivoManagerUtils<Finalidad> descManager;
	public List<DescriptivoDto> getAll() {
		Iterable<Finalidad> entidades = descRepo.findAll();
		return descManager.getDtos(entidades);
	}
}
