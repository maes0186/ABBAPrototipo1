package com.conexia.saludcoop.common.dto;

import java.util.Set;

import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.entity.maestro.Regional;

@Mappeable(mappedTo=Regional.class)
public class RegionalDto {
	
	private Long id;
	private String codigo;
	private String descripcion;
	private Set<DepartamentoDto> departamentos;
	private DivisionSeccionalDto divisionSeccional;

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

	public Set<DepartamentoDto> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(Set<DepartamentoDto> departamentos) {
		this.departamentos = departamentos;
	}

	
    public DivisionSeccionalDto getDivisionSeccional() {
    
    	return divisionSeccional;
    }

	
    public void setDivisionSeccional(DivisionSeccionalDto divisionSeccional) {
    
    	this.divisionSeccional = divisionSeccional;
    }
}
