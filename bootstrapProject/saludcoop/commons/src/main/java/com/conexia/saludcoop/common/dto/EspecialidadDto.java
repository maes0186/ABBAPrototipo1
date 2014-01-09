package com.conexia.saludcoop.common.dto;

import java.util.Set;

import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.entity.maestro.Especialidad;

@Mappeable(mappedTo=Especialidad.class)
public class EspecialidadDto { 
	private Integer id;
	private String codigo;
	private String descripcion;
	private Set<ProcedimientoDto>procedimientos;

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}

	public String getDescripcion(){
		return this.descripcion;
	}

	public Set<ProcedimientoDto>getProcedimientos() {
		return procedimientos;
	}

	public void setProcedimientos(Set<ProcedimientoDto>procedimientos) {
		this.procedimientos = procedimientos;
	}

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
