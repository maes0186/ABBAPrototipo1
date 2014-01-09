package com.conexia.saludcoop.web.manager;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.TipoIdentificacionDto;
import com.conexia.saludcoop.common.entity.maestro.TipoIdentificacionAportante;
import com.conexia.saludcoop.common.repository.TipoIdentificacionAportanteRepository;
@Service
@Transactional
public class TipoIdentificacionAportanteManager{
	@Autowired
	private TipoIdentificacionAportanteRepository descRepo;
	@Autowired
	private TipoIdentificacionManagerUtils<TipoIdentificacionAportante> descManager;
	
	public List<TipoIdentificacionDto> getAll() {
		Iterable<TipoIdentificacionAportante> entidades = descRepo.findAll();
		return descManager.getDtos(entidades);
	}
}
