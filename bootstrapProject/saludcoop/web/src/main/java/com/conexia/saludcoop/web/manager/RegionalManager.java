package com.conexia.saludcoop.web.manager;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.RegionalDto;
import com.conexia.saludcoop.common.entity.maestro.Regional;
import com.conexia.saludcoop.common.repository.RegionalRepository;
@Service
@Transactional
public class RegionalManager{
	@Autowired
	private RegionalRepository repo;
	
	public List<RegionalDto> getAll() {
		Iterable<Regional> entidades = repo.findAll();
		List<RegionalDto> regionales = new ArrayList<>();
		for (Regional regional : entidades) {
            RegionalDto dto = regional.toDto();
            regionales.add(dto);
        }
		return regionales;
	}
}
