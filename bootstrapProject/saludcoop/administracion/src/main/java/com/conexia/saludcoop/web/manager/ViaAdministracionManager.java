package com.conexia.saludcoop.web.manager;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.entity.maestro.ViaAdministracion;
import com.conexia.saludcoop.common.repository.ViaAdministracionRepository;
@Service
@Transactional
public class ViaAdministracionManager{
	@Autowired
	private ViaAdministracionRepository descRepo;
	@Autowired
	private DescriptivoManagerUtils<ViaAdministracion> descManager;
	public List<DescriptivoDto> getAll() {
		Iterable<ViaAdministracion> entidades = descRepo.findAll();
		return descManager.getDtos(entidades);
	}
}
