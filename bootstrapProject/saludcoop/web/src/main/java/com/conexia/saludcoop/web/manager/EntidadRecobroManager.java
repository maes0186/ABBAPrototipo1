package com.conexia.saludcoop.web.manager;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.entity.maestro.EntidadRecobro;
import com.conexia.saludcoop.common.repository.EntidadRecobroRepository;
@Service
@Transactional
public class EntidadRecobroManager{
	@Autowired
	private EntidadRecobroRepository descRepo;
	@Autowired
	private DescriptivoManagerUtils<EntidadRecobro> descManager;
	public List<DescriptivoDto> getAll() {
		Iterable<EntidadRecobro> entidades = descRepo.findAll();
		return descManager.getDtos(entidades);
	}
}
