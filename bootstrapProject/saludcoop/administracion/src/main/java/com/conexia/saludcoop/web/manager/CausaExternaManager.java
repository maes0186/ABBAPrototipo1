package com.conexia.saludcoop.web.manager;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.entity.maestro.CausaExterna;
import com.conexia.saludcoop.common.repository.CausaExternaRepository;
@Service
@Transactional
public class CausaExternaManager{
	@Autowired
	private CausaExternaRepository descRepo;
	@Autowired
	private DescriptivoManagerUtils<CausaExterna> descManager;
	public List<DescriptivoDto> getAll() {
		Iterable<CausaExterna> entidades = descRepo.findAll();
		return descManager.getDtos(entidades);
	}
}
