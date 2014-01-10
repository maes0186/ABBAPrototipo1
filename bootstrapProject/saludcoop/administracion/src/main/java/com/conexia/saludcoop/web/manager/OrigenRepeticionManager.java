package com.conexia.saludcoop.web.manager;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.entity.maestro.OrigenRepeticion;
import com.conexia.saludcoop.common.repository.OrigenRepeticionRepository;
@Service
@Transactional
public class OrigenRepeticionManager{
	@Autowired
	private OrigenRepeticionRepository descRepo;
	@Autowired
	private DescriptivoManagerUtils<OrigenRepeticion> descManager;
	public List<DescriptivoDto> getAll() {
		Iterable<OrigenRepeticion> entidades = descRepo.findAll();
		return descManager.getDtos(entidades);
	}
	public DescriptivoDto findOne(Integer id) {
		return this.descRepo.findOne(id).toDto();
	}
}
