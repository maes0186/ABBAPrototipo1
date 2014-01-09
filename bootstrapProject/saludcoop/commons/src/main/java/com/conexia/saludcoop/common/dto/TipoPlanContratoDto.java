package com.conexia.saludcoop.common.dto;

import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.entity.maestro.TipoPlanContrato;

@Mappeable(mappedTo=TipoPlanContrato.class)
public class TipoPlanContratoDto { 
	private Long id;
	private String descripcion;

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

}
