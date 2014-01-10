package com.conexia.saludcoop.web.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
	public List<DepartamentoDto> findAll(PageRequest pageRequest) {
		List<Departamento> vals = repo.findAll(pageRequest).getContent();
		
		List<DepartamentoDto> dtos= new ArrayList<DepartamentoDto>();
		for (Departamento departamento : vals) {
			departamento.setMunicipios(null);
			dtos.add(departamento.toDto());
		}
		return dtos;
	}
	
	public List<DepartamentoDto> findAll() {
		Iterable<Departamento> vals=repo.findAll(new Sort(Sort.Direction.ASC, "descripcion"));
		List<DepartamentoDto> dtos= new ArrayList<DepartamentoDto>();
		for (Departamento departamento : vals) {
			departamento.setMunicipios(null);
			dtos.add(departamento.toDto());
		}
		return dtos;
	}
	
	public List<DepartamentoDto> findByDescripcionLike(String descripcion, PageRequest pageRequest){
		Page<Departamento> vals = repo.findByDescripcionContaining(descripcion, pageRequest);
		List<DepartamentoDto> dtos= new ArrayList<DepartamentoDto>();
		for (Departamento departamento : vals) {
			departamento.setMunicipios(null);
			dtos.add(departamento.toDto());
		}
		return dtos;
	}
	
	public long countAllrows() {		
		return repo.count();
	}
}
