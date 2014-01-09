package com.conexia.saludcoop.common.dto;

import java.util.Set;

import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.entity.maestro.Diagnostico;
@Mappeable(mappedTo=Diagnostico.class)
public class DiagnosticoDto { 
	private Long id;
	private String codigo;
	private String descripcion;
	private DescriptivoDto tipoDiagnostico;
	private Set<ProcedimientoDto>procedimientos;

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
	public void setTipoDiagnostico(DescriptivoDto tipoDiagnostico){
		this.tipoDiagnostico = tipoDiagnostico;
	}

	public DescriptivoDto getTipoDiagnostico(){
		return this.tipoDiagnostico;
	}
	public void setProcedimientos(Set<ProcedimientoDto>procedimientos){
		this.procedimientos = procedimientos;
	}

	public Set<ProcedimientoDto>getProcedimientos(){
		return this.procedimientos;
	}
}
