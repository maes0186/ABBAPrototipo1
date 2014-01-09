package com.conexia.saludcoop.web.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.DepartamentoDto;
import com.conexia.saludcoop.common.entity.maestro.Departamento;
import com.conexia.saludcoop.common.repository.DepartamentoRepository;
/**
 * Manager de Departamentos
 * @author mortega
 *
 */
@Service
@Transactional
public class DepartamentoManager {
	@Autowired
	private DepartamentoRepository repo;
	/**
	 * Metodo para entoncontrar todos los elementos
	 * @return
	 */
	public List<DepartamentoDto> findAll() {
		Iterable<Departamento> vals=repo.findAll();
		List<DepartamentoDto> dtos= new ArrayList<DepartamentoDto>();
		for (Departamento departamento : vals) {
			dtos.add(departamento.toDto());
		}
		return dtos;
	}
}
