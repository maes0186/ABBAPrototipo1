package com.conexia.saludcoop.web.manager;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.TipoIdentificacionDto;
import com.conexia.saludcoop.common.entity.maestro.TipoIdentificacion;
import com.conexia.saludcoop.common.repository.AutorizacionRepository;
import com.conexia.saludcoop.common.repository.TipoIdentificacionRepository;

@Repository

public class TipoIdentificacionManager {
	
	@Autowired
	private TipoIdentificacionRepository descRepo;

	@Autowired
	private AutorizacionRepository autoRepo;
	
	@Autowired
	private TipoIdentificacionManagerUtils<TipoIdentificacion> descManager;
	
	@Autowired
	private SessionFactory sessionFactory;
	@Transactional
	public List<TipoIdentificacionDto> getAll() {
		Iterable<TipoIdentificacion> entidades = descRepo.findAll();
		return descManager.getDtos(entidades);
	}

	public TipoIdentificacionDto getByCodigo(String tipoDocumento) {
		// TODO Auto-generated method stub
		return descManager.getDto(descRepo.findOneByCodigo(tipoDocumento));
	}

}
