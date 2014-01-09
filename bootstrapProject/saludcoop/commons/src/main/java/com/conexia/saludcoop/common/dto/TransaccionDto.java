package com.conexia.saludcoop.common.dto;

import java.util.Date;

import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.entity.maestro.Transaccion;

@Mappeable(mappedTo=Transaccion.class)
public class TransaccionDto { 
	private Long id;
	private String tipoTransaccion;
	private AfiliadoDto afiliado;
	private EpsDto eps;
	private String origen;
	private Date fechaCreacion;
	private String codigoMensajeRespuesta;
	private String codigoRespuesta;

	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}
	public void setTipoTransaccion(String tipoTransaccion){
		this.tipoTransaccion = tipoTransaccion;
	}

	public String getTipoTransaccion(){
		return this.tipoTransaccion;
	}
	public void setAfiliado(AfiliadoDto afiliado){
		this.afiliado = afiliado;
	}

	public AfiliadoDto getAfiliado(){
		return this.afiliado;
	}
	public void setEps(EpsDto eps){
		this.eps = eps;
	}

	public EpsDto getEps(){
		return this.eps;
	}
	public void setOrigen(String origen){
		this.origen = origen;
	}

	public String getOrigen(){
		return this.origen;
	}
	public void setFechaCreacion(Date fechaCreacion){
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaCreacion(){
		return this.fechaCreacion;
	}
	public void setCodigoMensajeRespuesta(String codigoMensajeRespuesta){
		this.codigoMensajeRespuesta = codigoMensajeRespuesta;
	}

	public String getCodigoMensajeRespuesta(){
		return this.codigoMensajeRespuesta;
	}
	public void setCodigoRespuesta(String codigoRespuesta){
		this.codigoRespuesta = codigoRespuesta;
	}

	public String getCodigoRespuesta(){
		return this.codigoRespuesta;
	}

}
