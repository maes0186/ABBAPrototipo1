package com.conexia.saludcoop.web.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.entity.maestro.TipoPPM;
import com.conexia.saludcoop.common.repository.TipoPPMRepository;

@Component
public class TipoPPMManager {
	@Autowired
	private TipoPPMRepository descRepo;
	@Autowired
	private DescriptivoManagerUtils<TipoPPM> descManager;
	public List<DescriptivoDto> getAll() {
		Iterable<TipoPPM> entidades = descRepo.findAll();
		return descManager.getDtos(entidades);
	}
	public DescriptivoDto findOne(Integer arg0) {
		return descRepo.findOne(arg0).toDto();
	}
	public DescriptivoDto getByDescripcion(String tipoPPM) {

		return descRepo.findOneByDescripcion(tipoPPM).toDto();
	}
	public DescriptivoDto getByCodigo(String codigo) {
	    return descRepo.findOneByCodigo(codigo).toDto();
	}
}
