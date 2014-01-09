package com.conexia.saludcoop.web.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.TipoIdentificacionDto;
import com.conexia.saludcoop.common.entity.maestro.TipoIdentificacionDocumento;
import com.conexia.saludcoop.common.repository.TipoIdentificacionAfiliadoRepository;

@Service
@Transactional
public class TipoIdentificacionAfiliadoManager{
	
	@Autowired
	private TipoIdentificacionAfiliadoRepository descRepo;

	@Autowired
	private TipoIdentificacionManagerUtils<TipoIdentificacionDocumento> descManager;
	
	public List<TipoIdentificacionDto> getAll() {
		Iterable<TipoIdentificacionDocumento> entidades = descRepo.findAll();
		return descManager.getDtos(entidades);
	}

	public TipoIdentificacionDocumento getByCodigo(String tipoDocumento) {
		// TODO Auto-generated method stub
		return null;
	}

}
