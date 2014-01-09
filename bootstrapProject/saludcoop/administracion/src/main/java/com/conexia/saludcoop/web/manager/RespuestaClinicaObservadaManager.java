package com.conexia.saludcoop.web.manager;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.entity.maestro.RespuestaClinicaObservada;
import com.conexia.saludcoop.common.repository.RespuestaClinicaObservadaRepository;
@Service
@Transactional
public class RespuestaClinicaObservadaManager{
	@Autowired
	private RespuestaClinicaObservadaRepository descRepo;
	@Autowired
	private DescriptivoManagerUtils<RespuestaClinicaObservada> descManager;
	public List<DescriptivoDto> getAll() {
		Iterable<RespuestaClinicaObservada> entidades = descRepo.findAll();
		return descManager.getDtos(entidades);
	}
}
