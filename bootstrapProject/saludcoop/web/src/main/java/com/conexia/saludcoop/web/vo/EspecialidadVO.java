package com.conexia.saludcoop.web.vo;

import com.conexia.saludcoop.common.dto.EspecialidadDto;

/**
 * 
 * @author mortega
 *
 */
public class EspecialidadVO {
	private Integer id;
	private String codigo;
	private String descripcion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public EspecialidadVO(EspecialidadDto dto) {
		this.id = dto.getId();
		this.descripcion = dto.getDescripcion();
		this.codigo = dto.getCodigo();
	}
}
