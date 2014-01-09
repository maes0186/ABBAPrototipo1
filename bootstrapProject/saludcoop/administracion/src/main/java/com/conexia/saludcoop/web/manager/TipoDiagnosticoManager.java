package com.conexia.saludcoop.web.manager;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.entity.maestro.TipoDiagnostico;
import com.conexia.saludcoop.common.repository.TipoDiagnosticoRepository;
@Service
@Transactional
public class TipoDiagnosticoManager{
	@Autowired
	private TipoDiagnosticoRepository descRepo;
	@Autowired
	private DescriptivoManagerUtils<TipoDiagnostico> descManager;
	public List<DescriptivoDto> getAll() {
		Iterable<TipoDiagnostico> entidades = descRepo.findAll();
		return descManager.getDtos(entidades);
	}
}
