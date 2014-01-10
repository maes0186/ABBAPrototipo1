package com.conexia.saludcoop.web.manager;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.entity.maestro.Parentesco;
import com.conexia.saludcoop.common.repository.ParentescoRepository;
@Service
@Transactional
public class ParentescoManager{
	@Autowired
	private ParentescoRepository descRepo;
	@Autowired
	private DescriptivoManagerUtils<Parentesco> descManager;
	public List<DescriptivoDto> getAll() {
		Iterable<Parentesco> entidades = descRepo.findAll();
		return descManager.getDtos(entidades);
	}
}
