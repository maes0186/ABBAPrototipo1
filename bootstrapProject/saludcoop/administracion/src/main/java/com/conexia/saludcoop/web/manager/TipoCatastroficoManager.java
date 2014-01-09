package com.conexia.saludcoop.web.manager;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.entity.maestro.TipoCatastrofico;
import com.conexia.saludcoop.common.repository.TipoCatastroficoRepository;
@Service
@Transactional
public class TipoCatastroficoManager{
	@Autowired
	private TipoCatastroficoRepository descRepo;
	@Autowired
	private DescriptivoManagerUtils<TipoCatastrofico> descManager;
	public List<DescriptivoDto> getAll() {
		Iterable<TipoCatastrofico> entidades = descRepo.findAll();
		return descManager.getDtos(entidades);
	}
}
