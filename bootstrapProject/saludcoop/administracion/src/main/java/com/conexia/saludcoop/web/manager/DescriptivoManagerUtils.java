package com.conexia.saludcoop.web.manager;

import java.util.List;
import java.util.Vector;

import org.springframework.stereotype.Component;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.entity.maestro.Descriptivo;

@Component
public class DescriptivoManagerUtils<T extends Descriptivo> {
	
	public List<DescriptivoDto> getDtos(Iterable<T> entities){
		
		List<DescriptivoDto> dtos = new Vector<DescriptivoDto>();
		
		for (T entidad : entities) {
			dtos.add(entidad.toDto());
		}
		return dtos;
		
	}
	
}
