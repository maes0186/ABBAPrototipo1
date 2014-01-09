package com.conexia.saludcoop.web.manager;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.TipoIdentificacionDto;
import com.conexia.saludcoop.common.entity.maestro.TipoIdentificacionEps;
import com.conexia.saludcoop.common.repository.TipoIdentificacionEpsRepository;
@Service
@Transactional
public class TipoIdentificacionEpsManager{
	@Autowired
	private TipoIdentificacionEpsRepository descRepo;
	@Autowired
	private TipoIdentificacionManagerUtils<TipoIdentificacionEps> descManager;
	public List<TipoIdentificacionDto> getAll() {
		Iterable<TipoIdentificacionEps> entidades = descRepo.findAll();
		return descManager.getDtos(entidades);
	}
}
