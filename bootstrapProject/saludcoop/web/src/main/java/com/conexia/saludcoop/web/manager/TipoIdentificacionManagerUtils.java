package com.conexia.saludcoop.web.manager;

import java.util.List;
import java.util.Vector;

import org.springframework.stereotype.Component;

import com.conexia.saludcoop.common.dto.TipoIdentificacionDto;
import com.conexia.saludcoop.common.entity.maestro.TipoIdentificacion;

@Component
public class TipoIdentificacionManagerUtils<T extends TipoIdentificacion> {
	
	public List<TipoIdentificacionDto> getDtos(Iterable<T> entities){
		
		List<TipoIdentificacionDto> dtos = new Vector<TipoIdentificacionDto>();
		
		for (T entidad : entities) {
			dtos.add(entidad.toDto());
		}
		return dtos;
		
	}

	public TipoIdentificacionDto getDto(T entidad) {
		return entidad.toDto();
	}
	
}
