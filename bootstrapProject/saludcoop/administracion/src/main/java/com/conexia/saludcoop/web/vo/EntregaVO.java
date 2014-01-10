package com.conexia.saludcoop.web.vo;


public class EntregaVO {
	
	private Integer nroEntrega;
	private Integer cantidadEntrega;
	private String fechaInicioVigencia;
	private String fechaFinVigencia;
	
	
	
	public EntregaVO(Integer nroEntrega, Integer cantidadEntrega, String fechaInicioVigencia,
			String fechaFinVigencia) {
		super();
		this.nroEntrega = nroEntrega;
		this.cantidadEntrega = cantidadEntrega;
		this.fechaInicioVigencia = fechaInicioVigencia;
		this.fechaFinVigencia = fechaFinVigencia;
	}
	
	public Integer getNroEntrega() {
		return nroEntrega;
	}

	public void setNroEntrega(Integer nroEntrega) {
		this.nroEntrega = nroEntrega;
	}

	public Integer getCantidadEntrega() {
		return cantidadEntrega;
	}
	public void setCantidadEntrega(Integer cantidadEntrega) {
		this.cantidadEntrega = cantidadEntrega;
	}
	public String getFechaInicioVigencia() {
		return fechaInicioVigencia;
	}
	public void setFechaInicioVigencia(String fechaInicioVigencia) {
		this.fechaInicioVigencia = fechaInicioVigencia;
	}
	public String getFechaFinVigencia() {
		return fechaFinVigencia;
	}
	public void setFechaFinVigencia(String fechaFinVigencia) {
		this.fechaFinVigencia = fechaFinVigencia;
	}

	

}
