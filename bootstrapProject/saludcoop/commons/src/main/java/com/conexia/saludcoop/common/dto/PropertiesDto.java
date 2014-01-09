package com.conexia.saludcoop.common.dto;

import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.entity.configuration.Properties;

@Mappeable(mappedTo=Properties.class)
public class PropertiesDto { 
	private Long id;
	private String clave;
	private String valor;
	private String aplicacion;

	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}
	public void setClave(String clave){
		this.clave = clave;
	}

	public String getClave(){
		return this.clave;
	}
	public void setValor(String valor){
		this.valor = valor;
	}

	public String getValor(){
		return this.valor;
	}
	public void setAplicacion(String aplicacion){
		this.aplicacion = aplicacion;
	}

	public String getAplicacion(){
		return this.aplicacion;
	}

}
