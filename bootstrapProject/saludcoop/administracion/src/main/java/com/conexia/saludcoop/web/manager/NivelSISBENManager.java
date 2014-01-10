package com.conexia.saludcoop.web.manager;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.entity.maestro.NivelSISBEN;
import com.conexia.saludcoop.common.repository.NivelSISBENRepository;
@Service
@Transactional
public class NivelSISBENManager{
	@Autowired
	private NivelSISBENRepository descRepo;
	@Autowired
	private DescriptivoManagerUtils<NivelSISBEN> descManager;
	public List<DescriptivoDto> getAll() {
		Iterable<NivelSISBEN> entidades = descRepo.findAll();
		return descManager.getDtos(entidades);
	}
}
