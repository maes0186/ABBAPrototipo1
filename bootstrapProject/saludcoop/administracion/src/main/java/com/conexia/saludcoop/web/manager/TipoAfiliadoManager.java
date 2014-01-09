package com.conexia.saludcoop.web.manager;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.entity.maestro.TipoAfiliado;
import com.conexia.saludcoop.common.repository.TipoAfiliadoRepository;
@Service
@Transactional
public class TipoAfiliadoManager{
	@Autowired
	private TipoAfiliadoRepository descRepo;
	@Autowired
	private DescriptivoManagerUtils<TipoAfiliado> descManager;
	public List<DescriptivoDto> getAll() {
		Iterable<TipoAfiliado> entidades = descRepo.findAll();
		return descManager.getDtos(entidades);
	}
}
