package com.conexia.saludcoop.web.manager;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.entity.maestro.ObjetivoProcedimiento;
import com.conexia.saludcoop.common.repository.ObjetivoProcedimientoRepository;
@Service
@Transactional
public class ObjetivoProcedimientoManager{
	@Autowired
	private ObjetivoProcedimientoRepository descRepo;
	@Autowired
	private DescriptivoManagerUtils<ObjetivoProcedimiento> descManager;
	public List<DescriptivoDto> getAll() {
		Iterable<ObjetivoProcedimiento> entidades = descRepo.findAll();
		return descManager.getDtos(entidades);
	}
	public DescriptivoDto findOne(Integer arg0) {
		return descRepo.findOne(arg0).toDto();
	}
}
