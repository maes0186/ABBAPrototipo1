package com.conexia.saludcoop.common.dto;

import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.entity.maestro.Eps;

@Mappeable(mappedTo=Eps.class)
public class EpsDto { 
	private Long id;
	private String razonSocial;
	private TipoIdentificacionDto tipoIdentificacion;
	private String numeroIdentificacion;

	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}
	public void setRazonSocial(String razonSocial){
		this.razonSocial = razonSocial;
	}

	public String getRazonSocial(){
		return this.razonSocial;
	}
	public void setTipoIdentificacion(TipoIdentificacionDto tipoIdentificacionDto){
		this.tipoIdentificacion = tipoIdentificacionDto;
	}

	public TipoIdentificacionDto getTipoIdentificacion(){
		return this.tipoIdentificacion;
	}
	public void setNumeroIdentificacion(String numeroIdentificacion){
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getNumeroIdentificacion(){
		return this.numeroIdentificacion;
	}

}
