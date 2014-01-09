package com.conexia.saludcoop.web.manager;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.entity.maestro.EstadoAutorizacion;
import com.conexia.saludcoop.common.repository.EstadoAutorizacionRepository;
@Service
@Transactional
public class EstadoAutorizacionManager{
	@Autowired
	private EstadoAutorizacionRepository descRepo;
	@Autowired
	private DescriptivoManagerUtils<EstadoAutorizacion> descManager;
	public List<DescriptivoDto> getAll() {
		Iterable<EstadoAutorizacion> entidades = descRepo.findAll(new Sort(Sort.Direction.ASC, "descripcion"));
		return descManager.getDtos(entidades);
	}
}
