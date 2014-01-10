package com.conexia.saludcoop.web.manager;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.entity.maestro.TipoServicio;
import com.conexia.saludcoop.common.repository.TipoServicioRepository;
@Service
@Transactional
public class TipoServicioManager{
	@Autowired
	private TipoServicioRepository descRepo;
	@Autowired
	private DescriptivoManagerUtils<TipoServicio> descManager;
	public List<DescriptivoDto> getAll() {
		Iterable<TipoServicio> entidades = descRepo.findAll();
		return descManager.getDtos(entidades);
	}
}
