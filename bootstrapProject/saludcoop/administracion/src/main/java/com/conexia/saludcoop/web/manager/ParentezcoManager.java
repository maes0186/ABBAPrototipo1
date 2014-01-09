package com.conexia.saludcoop.web.manager;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.entity.maestro.Parentezco;
import com.conexia.saludcoop.common.repository.ParentezcoRepository;
@Service
@Transactional
public class ParentezcoManager{
	@Autowired
	private ParentezcoRepository descRepo;
	@Autowired
	private DescriptivoManagerUtils<Parentezco> descManager;
	public List<DescriptivoDto> getAll() {
		Iterable<Parentezco> entidades = descRepo.findAll();
		return descManager.getDtos(entidades);
	}
}
