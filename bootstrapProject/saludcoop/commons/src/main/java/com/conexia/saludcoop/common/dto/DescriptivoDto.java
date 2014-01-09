package com.conexia.saludcoop.common.dto;



public class DescriptivoDto { 
	private Integer id;
	private String codigo;
	private String descripcion;

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
