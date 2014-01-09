package com.conexia.saludcoop.web.crud.util;

import java.util.List;
import java.util.Vector;

import org.springframework.ui.ModelMap;

import com.conexia.saludcoop.common.dto.TipoIdentificacionDto;
import com.conexia.saludcoop.web.vo.TipoIdentificacionVO;

public class CrudUtil {
	public static void cargarElementosTipoIdentificacion(String elemento,ModelMap map, List<TipoIdentificacionDto> tipoIdentificacionDtos){
		Vector<TipoIdentificacionVO> elementos = new Vector<TipoIdentificacionVO>();
		for (TipoIdentificacionDto enumItem : tipoIdentificacionDtos){
			elementos.add(new TipoIdentificacionVO(enumItem));
		}
		map.put(elemento, elementos);
	}
}
