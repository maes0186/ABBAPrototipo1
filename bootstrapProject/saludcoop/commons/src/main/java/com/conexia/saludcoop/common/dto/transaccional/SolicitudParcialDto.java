package com.conexia.saludcoop.common.dto.transaccional;

import java.util.Date;

import com.conexia.saludcoop.common.dto.SedeIpsDto;
import com.conexia.saludcoop.common.dto.TipoIdentificacionDto;


public class SolicitudParcialDto { 
	private Integer id;
	private Integer nroSolicitud;
	private SedeIpsDto sedeIps;
	private TipoIdentificacionDto tipoIdentificacionAfiliado;
	private String numeroIdentificacionAfiliado;
	private Date fechaCreacion;
	private Date fechaUpdate;
	private String user;
	private String formData;
	private String nombreCompletoAfiliado;
	
	public Integer getNroSolicitud() {
		return nroSolicitud;
	}
	public void setNroSolicitud(Integer nroSolicitud) {
		this.nroSolicitud = nroSolicitud;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public SedeIpsDto getSedeIps() {
		return sedeIps;
	}
	public void setSedeIps(SedeIpsDto sedeIps) {
		this.sedeIps = sedeIps;
	}
	public TipoIdentificacionDto getTipoIdentificacionAfiliado() {
		return tipoIdentificacionAfiliado;
	}
	public void setTipoIdentificacionAfiliado(TipoIdentificacionDto tipoIdentificacionAfiliado) {
		this.tipoIdentificacionAfiliado = tipoIdentificacionAfiliado;
	}
	public String getNumeroIdentificacionAfiliado() {
		return numeroIdentificacionAfiliado;
	}
	public void setNumeroIdentificacionAfiliado(String numeroIdentificacionAfiliado) {
		this.numeroIdentificacionAfiliado = numeroIdentificacionAfiliado;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Date getFechaUpdate() {
		return fechaUpdate;
	}
	public void setFechaUpdate(Date fechaUpdate) {
		this.fechaUpdate = fechaUpdate;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getFormData() {
		return formData;
	}
	public void setFormData(String formData) {
		this.formData = formData;
	}
    public String getNombreCompletoAfiliado() {
    	return nombreCompletoAfiliado;
    }
    public void setNombreCompletoAfiliado(String nombreCompletoAfiliado) {
    	this.nombreCompletoAfiliado = nombreCompletoAfiliado;
    }
}
