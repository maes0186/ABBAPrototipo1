package com.conexia.saludcoop.common.dto;

import java.util.Set;

import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.entity.maestro.Ips;
@Mappeable(mappedTo=Ips.class)
public class IpsDto { 
	private Long id;
	private String direccion;
	private String telefono;
	private String razonSocial;
	private TipoIdentificacionDto tipoIdentificacion;
	private String tipoId;
	private String numeroIdentificacion;
	private Set<SedeIpsDto>sedes;

	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}
	public void setDireccion(String direccion){
		this.direccion = direccion;
	}

	public String getDireccion(){
		return this.direccion;
	}
	public void setTelefono(String telefono){
		this.telefono = telefono;
	}

	public String getTelefono(){
		return this.telefono;
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
	public void setSedes(Set<SedeIpsDto>sedes){
		this.sedes = sedes;
	}

	public Set<SedeIpsDto>getSedes(){
		return this.sedes;
	}

	public String getTipoId() {
		return tipoId;
	}

	public void setTipoId(String tipoId) {
		this.tipoId = tipoId;
	}
}
