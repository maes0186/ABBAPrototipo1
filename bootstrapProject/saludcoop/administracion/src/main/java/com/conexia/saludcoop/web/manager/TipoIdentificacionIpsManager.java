package com.conexia.saludcoop.web.manager;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.TipoIdentificacionDto;
import com.conexia.saludcoop.common.entity.maestro.TipoIdentificacionIps;
import com.conexia.saludcoop.common.repository.TipoIdentificacionIpsRepository;
@Service
@Transactional
public class TipoIdentificacionIpsManager{
	@Autowired
	private TipoIdentificacionIpsRepository descRepo;
	@Autowired
	private TipoIdentificacionManagerUtils<TipoIdentificacionIps> descManager;
	public List<TipoIdentificacionDto> getAll() {
		Iterable<TipoIdentificacionIps> entidades = descRepo.findAll();
		return descManager.getDtos(entidades);
	}
}
