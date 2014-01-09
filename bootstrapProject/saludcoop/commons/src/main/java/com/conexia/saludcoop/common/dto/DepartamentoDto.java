package com.conexia.saludcoop.common.dto;

import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.entity.maestro.Departamento;
@Mappeable(mappedTo=Departamento.class)
public class DepartamentoDto { 
	private Long id;
	private String codigo;
	private String descripcion;
	private RegionalDto regional;

	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
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
	
    public RegionalDto getRegional() {
    	return regional;
    }

    public void setRegional(RegionalDto regional) {
    	this.regional = regional;
    }
}
