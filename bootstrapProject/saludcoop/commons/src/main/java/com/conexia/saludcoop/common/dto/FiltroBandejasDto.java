package com.conexia.saludcoop.common.dto;

public class FiltroBandejasDto {
	
	private Integer numeroSolicitud;
	private Long numeroAutorizacion;	
	private Integer estadoSolicitud;
	private Integer tecnologia;
	private Integer regional;
	private Long eps;
	private String numeroDocumento;
	private Integer tipoDocumento;
	private Integer tipoSolicitud;
	private Integer actualPage;
	private Long ipsId;
	private Integer profesionalId;
    private Boolean esTutela;
    private String bandeja;
	
    public Long getIpsId() {
		return ipsId;
	}
	public void setIpsId(Long ipsId) {
		this.ipsId = ipsId;
	}
	public Integer getProfesionalId() {
		return profesionalId;
	}
	public void setProfesionalId(Integer profesionalId) {
		this.profesionalId = profesionalId;
	}
	public Integer getNumeroSolicitud() {
        return numeroSolicitud;
    }
    public void setNumeroSolicitud(Integer numeroSolicitud) {
        this.numeroSolicitud = numeroSolicitud;
    }
    public Long getNumeroAutorizacion() {
		return numeroAutorizacion;
	}
	public void setNumeroAutorizacion(Long numeroAutorizacion) {
		this.numeroAutorizacion = numeroAutorizacion;
	}
	public Integer getEstadoSolicitud() {
        return estadoSolicitud;
    }
    public void setEstadoSolicitud(Integer estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }
    public Integer getTecnologia() {
        return tecnologia;
    }
    public void setTecnologia(Integer tecnologia) {
        this.tecnologia = tecnologia;
    }
    public Integer getRegional() {
        return regional;
    }
    public void setRegional(Integer regional) {
        this.regional = regional;
    }
    public Long getEps() {
        return eps;
    }
    public void setEps(Long eps) {
        this.eps = eps;
    }
    public String getNumeroDocumento() {
        return numeroDocumento;
    }
    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }
    public Integer getTipoDocumento() {
        return tipoDocumento;
    }
    public void setTipoDocumento(Integer tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    public Integer getTipoSolicitud() {
        return tipoSolicitud;
    }
    public void setTipoSolicitud(Integer tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }
    public Integer getActualPage() {
        return actualPage;
    }
    public void setActualPage(Integer actualPage) {
        this.actualPage = actualPage;
    }
    public Boolean getEsTutela() {
        return esTutela;
    }
    public void setEsTutela(Boolean esTutela) {
        this.esTutela = esTutela;
    }
	public String getBandeja() {
		return bandeja;
	}
	public void setBandeja(String bandeja) {
		this.bandeja = bandeja;
	}
}
