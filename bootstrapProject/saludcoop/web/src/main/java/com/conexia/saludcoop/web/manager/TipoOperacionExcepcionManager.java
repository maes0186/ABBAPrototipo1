package com.conexia.saludcoop.web.manager;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.entity.maestro.TipoOperacionExcepcion;
import com.conexia.saludcoop.common.repository.TipoOperacionExcepcionRepository;
@Service
@Transactional
public class TipoOperacionExcepcionManager{
	@Autowired
	private TipoOperacionExcepcionRepository descRepo;
	@Autowired
	private DescriptivoManagerUtils<TipoOperacionExcepcion> descManager;
	public List<DescriptivoDto> getAll() {
		Iterable<TipoOperacionExcepcion> entidades = descRepo.findAll();
		return descManager.getDtos(entidades);
	}
}
