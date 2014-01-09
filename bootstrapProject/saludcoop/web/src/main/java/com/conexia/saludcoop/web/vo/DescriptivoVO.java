package com.conexia.saludcoop.web.vo;

import com.conexia.saludcoop.common.dto.DescriptivoDto;

public class DescriptivoVO {
	
	private Integer id;
	private String codigo;
	private String descripcion;
	
	public DescriptivoVO() {}
	
	public DescriptivoVO(Integer id, String codigo, String descripcion) {
		this.id = id;
		this.codigo = codigo ;
		this.descripcion = descripcion;
	}
	public DescriptivoVO(DescriptivoDto dto){
		this.id=dto.getId();
		this.codigo = dto.getCodigo() ;
		this.descripcion = dto.getDescripcion();
	}


	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}
	public void setCodigo(String codigo){
		this.codigo = codigo;
	}

	public String getCodigo(){
		return this.codigo;
	}
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}

	public String getDescripcion(){
		return this.descripcion;
	}

	
	

}
