package com.conexia.saludcoop.web.form;


public class SearchSolicitudParcialForm {
	private String 	codigoMinisterioSalud;
	private String 	nombreSedeIps;
	private Integer tipoIdentificacionAfiliado;
	private String	numeroIdentificacionAfiliado;
	private String 	fechaDesde;
	private String 	fechaHasta;
	private Integer nroSolicitud;
	
	
	public Integer getNroSolicitud() {
		return nroSolicitud;
	}
	public void setNroSolicitud(Integer nroSolicitud) {
		this.nroSolicitud = nroSolicitud;
	}
	public String getCodigoMinisterioSalud() {
		return codigoMinisterioSalud;
	}
	public void setCodigoMinisterioSalud(String codigoMinisterioSalud) {
		this.codigoMinisterioSalud = codigoMinisterioSalud;
	}
	public String getNombreSedeIps() {
		return nombreSedeIps;
	}
	public void setNombreSedeIps(String nombreSedeIps) {
		this.nombreSedeIps = nombreSedeIps;
	}
	public Integer getTipoIdentificacionAfiliado() {
		return tipoIdentificacionAfiliado;
	}
	public void setTipoIdentificacionAfiliado(Integer tipoIdentificacionAfiliado) {
		this.tipoIdentificacionAfiliado = tipoIdentificacionAfiliado;
	}
	public String getNumeroIdentificacionAfiliado() {
		return numeroIdentificacionAfiliado;
	}
	public void setNumeroIdentificacionAfiliado(String numeroIdentificacionAfiliado) {
		this.numeroIdentificacionAfiliado = numeroIdentificacionAfiliado;
	}
	public String getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public String getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	
	
	
}
