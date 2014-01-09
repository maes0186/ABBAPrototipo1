package com.conexia.saludcoop.common.dto.ticket;


public class TicketItemDto { 
	private Integer id;
	private Integer ticketCabeceraId;
	private String codigo;
	private String descripcion;
	private String cantidad;
	private String finalidad;
	private String causaExterna;
	private String lateralidad;
	private String observaciones;
	private String tipoCatastrofico;

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}
	
	public Integer getTicketCabeceraId() {
		return ticketCabeceraId;
	}

	public void setTicketCabeceraId(Integer ticketCabeceraId) {
		this.ticketCabeceraId = ticketCabeceraId;
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
	public void setCantidad(String cantidad){
		this.cantidad = cantidad;
	}

	public String getCantidad(){
		return this.cantidad;
	}
	public void setFinalidad(String finalidad){
		this.finalidad = finalidad;
	}

	public String getFinalidad(){
		return this.finalidad;
	}
	public void setCausaExterna(String causaExterna){
		this.causaExterna = causaExterna;
	}

	public String getCausaExterna(){
		return this.causaExterna;
	}
	public void setLateralidad(String lateralidad){
		this.lateralidad = lateralidad;
	}

	public String getLateralidad(){
		return this.lateralidad;
	}
	public void setObservaciones(String observaciones){
		this.observaciones = observaciones;
	}

	public String getObservaciones(){
		return this.observaciones;
	}
	public void setTipoCatastrofico(String tipoCatastrofico){
		this.tipoCatastrofico = tipoCatastrofico;
	}

	public String getTipoCatastrofico(){
		return this.tipoCatastrofico;
	}

}
