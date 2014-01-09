package com.conexia.saludcoop.web.vo;

import com.conexia.saludcoop.common.dto.TipoIdentificacionDto;

public class TipoIdentificacionVO {

	private Integer id;
	private String codigo;
	private String descripcion;
	private Boolean esAlfanumerico;
	private Integer maxLength;
	
	public TipoIdentificacionVO() {}
	
	public TipoIdentificacionVO(Integer id, String codigo, String descripcion, Boolean esAlfa, Integer maxLength) {
		this.id = id;
		this.codigo = codigo ;
		this.descripcion = descripcion;
		this.esAlfanumerico = esAlfa;
		this.maxLength = maxLength;
	}
	public TipoIdentificacionVO(TipoIdentificacionDto dto){
		this.id = dto.getId();
		this.codigo = dto.getCodigo() ;
		this.descripcion = dto.getDescripcion();
		this.esAlfanumerico = dto.getEsAlfanumerico();
		this.maxLength = dto.getMaxLength();
	}
	
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

	public Boolean getEsAlfanumerico() {
		return esAlfanumerico;
	}

	public void setEsAlfanumerico(Boolean esAlfanumerico) {
		this.esAlfanumerico = esAlfanumerico;
	}

	public Integer getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}
	
	

	
	
	
}
