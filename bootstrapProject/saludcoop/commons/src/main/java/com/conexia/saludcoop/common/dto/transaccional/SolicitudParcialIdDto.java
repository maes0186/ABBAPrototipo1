package com.conexia.saludcoop.common.dto.transaccional;

import java.util.Date;

import com.conexia.saludcoop.common.dto.IpsDto;
import com.conexia.saludcoop.common.dto.TipoIdentificacionDto;

public class SolicitudParcialIdDto { 
	private long serialVersionUID;
	private String nombre;
	private IpsDto ips;
	private TipoIdentificacionDto tipoIdentificacionAfiliado;
	private String numeroDocumento;
	private Date fechaCreacion;

	public void setSerialVersionUID(long serialVersionUID){
		this.serialVersionUID = serialVersionUID;
	}

	public long getSerialVersionUID(){
		return this.serialVersionUID;
	}
	public void setNombre(String nombre){
		this.nombre = nombre;
	}

	public String getNombre(){
		return this.nombre;
	}
	public void setIps(IpsDto ips){
		this.ips = ips;
	}

	public IpsDto getIps(){
		return this.ips;
	}
	public void setTipoIdentificacionAfiliado(TipoIdentificacionDto tipoIdentificacionAfiliado){
		this.tipoIdentificacionAfiliado = tipoIdentificacionAfiliado;
	}

	public TipoIdentificacionDto getTipoIdentificacionAfiliado(){
		return this.tipoIdentificacionAfiliado;
	}
	public void setNumeroDocumento(String numeroDocumento){
		this.numeroDocumento = numeroDocumento;
	}

	public String getNumeroDocumento(){
		return this.numeroDocumento;
	}
	public void setFechaCreacion(Date fechaCreacion){
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaCreacion(){
		return this.fechaCreacion;
	}

}
