package com.conexia.saludcoop.web.vo;

import java.util.List;


public class BandejaItemVO {

	private String numeroSolicitud;
	private String fechaCreacionSolicitud;
	private String fechaModificacionSolicitud;
	private String tipoIdentificacionAfiliado;
	private String numeroIdentificacionAfiliado;
	private String nombreAfiliado;
	private String eps;
	private String regional;
	private String profesionalSolicitante;
	private String programa;
	
	private List<BandejaSubItemVO> subitems;

	
	
	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public String getNumeroSolicitud() {
		return numeroSolicitud;
	}

	public void setNumeroSolicitud(String numeroSolicitud) {
		this.numeroSolicitud = numeroSolicitud;
	}

	public String getFechaCreacionSolicitud() {
		return fechaCreacionSolicitud;
	}

	public void setFechaCreacionSolicitud(String fechaCreacionSolicitud) {
		this.fechaCreacionSolicitud = fechaCreacionSolicitud;
	}

	public String getFechaModificacionSolicitud() {
		return fechaModificacionSolicitud;
	}

	public void setFechaModificacionSolicitud(String fechaModificacionSolicitud) {
		this.fechaModificacionSolicitud = fechaModificacionSolicitud;
	}

	public String getTipoIdentificacionAfiliado() {
		return tipoIdentificacionAfiliado;
	}

	public void setTipoIdentificacionAfiliado(String tipoIdentificacionAfiliado) {
		this.tipoIdentificacionAfiliado = tipoIdentificacionAfiliado;
	}

	public String getNumeroIdentificacionAfiliado() {
		return numeroIdentificacionAfiliado;
	}

	public void setNumeroIdentificacionAfiliado(String numeroIdentificacionAfiliado) {
		this.numeroIdentificacionAfiliado = numeroIdentificacionAfiliado;
	}

	public String getNombreAfiliado() {
		return nombreAfiliado;
	}

	public void setNombreAfiliado(String nombreAfiliado) {
		this.nombreAfiliado = nombreAfiliado;
	}

	public String getEps() {
		return eps;
	}

	public void setEps(String eps) {
		this.eps = eps;
	}

	public String getRegional() {
		return regional;
	}

	public void setRegional(String regional) {
		this.regional = regional;
	}

	public String getProfesionalSolicitante() {
		return profesionalSolicitante;
	}

	public void setProfesionalSolicitante(String profesionalSolicitante) {
		this.profesionalSolicitante = profesionalSolicitante;
	}

	public List<BandejaSubItemVO> getSubitems() {
		return subitems;
	}

	public void setSubitems(List<BandejaSubItemVO> subitems) {
		this.subitems = subitems;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		return this.numeroSolicitud.equals(((BandejaItemVO)obj).numeroSolicitud);
	}
	
	
}
