package com.conexia.saludcoop.common.dto;

import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.entity.maestro.DivisionSeccional;

@Mappeable(mappedTo=DivisionSeccional.class)
public class DivisionSeccionalDto { 
	private Long id;
	private String descripcion;
	private String codigo;

	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}
	
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}

	public String getDescripcion(){
		return this.descripcion;
	}
	
    public String getCodigo() {
    	return codigo;
    }
	
    public void setCodigo(String codigo) {
    	this.codigo = codigo;
    }
}
