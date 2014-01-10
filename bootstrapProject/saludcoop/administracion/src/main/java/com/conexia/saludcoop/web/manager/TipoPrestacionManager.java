package com.conexia.saludcoop.web.manager;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.entity.maestro.TipoPrestacion;
import com.conexia.saludcoop.common.repository.TipoPrestacionRepository;
@Service
@Transactional
public class TipoPrestacionManager{
	@Autowired
	private TipoPrestacionRepository descRepo;
	@Autowired
	private DescriptivoManagerUtils<TipoPrestacion> descManager;
	public List<DescriptivoDto> getAll() {
		Iterable<TipoPrestacion> entidades = descRepo.findAll();
		return descManager.getDtos(entidades);
	}
	public DescriptivoDto findOne(Integer id) {
		return this.descRepo.findOne(id).toDto();
	}
}
