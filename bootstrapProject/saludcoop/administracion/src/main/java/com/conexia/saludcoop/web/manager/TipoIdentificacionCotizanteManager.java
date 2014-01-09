package com.conexia.saludcoop.web.manager;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.TipoIdentificacionDto;
import com.conexia.saludcoop.common.entity.maestro.TipoIdentificacionCotizante;
import com.conexia.saludcoop.common.repository.TipoIdentificacionCotizanteRepository;
@Service
@Transactional
public class TipoIdentificacionCotizanteManager{
	@Autowired
	private TipoIdentificacionCotizanteRepository descRepo;
	@Autowired
	private TipoIdentificacionManagerUtils<TipoIdentificacionCotizante> descManager;
	public List<TipoIdentificacionDto> getAll() {
		Iterable<TipoIdentificacionCotizante> entidades = descRepo.findAll();
		return descManager.getDtos(entidades);
	}
}
