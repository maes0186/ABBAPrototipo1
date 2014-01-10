package com.conexia.saludcoop.web.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.entity.maestro.TipoDocumentoSoporte;
import com.conexia.saludcoop.common.repository.TipoDocumentoSoporteRepository;

@Component
public class TipoDocumentoSoporteManager {
	@Autowired
	private TipoDocumentoSoporteRepository descRepo;
	@Autowired
	private DescriptivoManagerUtils<TipoDocumentoSoporte> descManager;

	public List<DescriptivoDto> getAll() {
		Iterable<TipoDocumentoSoporte> entidades = descRepo.findAll();
		return descManager.getDtos(entidades);
	}

	public DescriptivoDto findOne(Integer id) {
		return this.descRepo.findOne(id).toDto();
	}

	public DescriptivoDto getByCodigo(String codigo) {
		return this.descRepo.findOneByCodigo(codigo).toDto();
	}
	
}
