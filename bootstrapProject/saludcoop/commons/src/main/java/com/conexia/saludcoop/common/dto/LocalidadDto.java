package com.conexia.saludcoop.common.dto;

import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.entity.maestro.Localidad;



@Mappeable(mappedTo=Localidad.class)
public class LocalidadDto {
	private Long id;
	private Integer clientePk;
	private String descripcion;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getClientePk() {
		return clientePk;
	}
	public void setClientePk(Integer clientePk) {
		this.clientePk = clientePk;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
