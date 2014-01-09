package com.conexia.saludcoop.web.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.EpsDto;
import com.conexia.saludcoop.common.entity.maestro.Eps;
import com.conexia.saludcoop.common.repository.EpsRepository;
/**
 * Manager de la entidad EPS
 * @author mortega
 *
 */
@Service
@Transactional
public class EpsManager {

	@Autowired
	private EpsRepository repo;
	/**
	 * Metodo para entoncontrar todos los elementos
	 * @return
	 */
	public List<EpsDto> findAll() {
		Iterable<Eps> vals=repo.findAll();
		List<EpsDto> dtos= new ArrayList<EpsDto>();
		for (Eps eps : vals) {
			dtos.add(eps.toDto());
		}
		return dtos;
	}

}
