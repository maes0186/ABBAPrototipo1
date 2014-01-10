package com.conexia.saludcoop.web.form;


public class FiltroBandejasHistorialDto {
	
	private Long numeroSolicitud;
	private Integer estadoSolicitud;
	private String regional;
	private String numeroDocumento;
	private Integer tipoDocumento;
	private Integer actualPage;
	
	private String fechaDesde;
	
	private String fechaHasta;
	
	public Long getNumeroSolicitud() {
		return numeroSolicitud;
	}
	public void setNumeroSolicitud(Long numeroSolicitud) {
		this.numeroSolicitud = numeroSolicitud;
	}
	public Integer getEstadoSolicitud() {
		return estadoSolicitud;
	}
	public void setEstadoSolicitud(Integer estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getRegional() {
		return regional;
	}
	public void setRegional(String regional) {
		this.regional = regional;
	}
	public Integer getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(Integer tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public Integer getActualPage() {
		return actualPage;
	}
	public void setActualPage(Integer actualPage) {
		this.actualPage = actualPage;
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
