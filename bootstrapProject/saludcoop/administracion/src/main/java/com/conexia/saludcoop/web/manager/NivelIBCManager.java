package com.conexia.saludcoop.web.manager;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.entity.maestro.NivelIBC;
import com.conexia.saludcoop.common.repository.NivelIBCRepository;
@Service
@Transactional
public class NivelIBCManager{
	@Autowired
	private NivelIBCRepository descRepo;
	@Autowired
	private DescriptivoManagerUtils<NivelIBC> descManager;
	public List<DescriptivoDto> getAll() {
		Iterable<NivelIBC> entidades = descRepo.findAll();
		return descManager.getDtos(entidades);
	}
}
