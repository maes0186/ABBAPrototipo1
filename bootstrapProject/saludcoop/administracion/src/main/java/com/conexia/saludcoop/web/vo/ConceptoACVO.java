package com.conexia.saludcoop.web.vo;


public class ConceptoACVO {

    private Integer periodoAprobado;
    private Integer unidadesAprobadas;
    private Integer numeroEntregas;
    private Integer diasPeriodo;
    private Integer dosisAprobada;
    private String justificacion;
    private String justificacionLdf;
    private Integer lateralidad;
    private String justificacionConexidad;
    private String numeroTutela;
    private String numeroFallo;
    private Boolean esTutelaIntegral;
    private Boolean exentaCopago; 
    
    public Integer getUnidadesAprobadas() {
        return unidadesAprobadas;
    }

    public void setUnidadesAprobadas(Integer unidadesAprobadas) {
        this.unidadesAprobadas = unidadesAprobadas;
    }

    public Integer getNumeroEntregas() {
        return numeroEntregas;
    }

    public void setNumeroEntregas(Integer numeroEntregas) {
        this.numeroEntregas = numeroEntregas;
    }

    public Integer getDiasPeriodo() {
        return diasPeriodo;
    }

    public void setDiasPeriodo(Integer diasPeriodo) {
        this.diasPeriodo = diasPeriodo;
    }

    public Integer getDosisAprobada() {
        return dosisAprobada;
    }

    public void setDosisAprobada(Integer dosisAprobada) {
        this.dosisAprobada = dosisAprobada;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }
    
    public Integer getPeriodoAprobado() {
        return periodoAprobado;
    }

    public void setPeriodoAprobado(Integer periodoAprobado) {
        this.periodoAprobado = periodoAprobado;
    }

    public Integer getLateralidad() {
        return lateralidad;
    }

    public void setLateralidad(Integer lateralidad) {
        this.lateralidad = lateralidad;
    }

    public String getJustificacionConexidad() {
        return justificacionConexidad;
    }

    public void setJustificacionConexidad(String justificacionConexidad) {
        this.justificacionConexidad = justificacionConexidad;
    }

    public Boolean getEsTutelaIntegral() {
        return esTutelaIntegral;
    }

    public void setEsTutelaIntegral(Boolean esTutelaIntegral) {
        this.esTutelaIntegral = esTutelaIntegral;
    }

    public Boolean getExentaCopago() {
        return exentaCopago;
    }

    public void setExentaCopago(Boolean exentaCopago) {
        this.exentaCopago = exentaCopago;
    }

    public String getNumeroTutela() {
        return numeroTutela;
    }

    public void setNumeroTutela(String numeroTutela) {
        this.numeroTutela = numeroTutela;
    }

    public String getJustificacionLdf() {
        return justificacionLdf;
    }

    public void setJustificacionLdf(String justificacionLdf) {
        this.justificacionLdf = justificacionLdf;
    }

	public String getNumeroFallo() {
		return numeroFallo;
	}

	public void setNumeroFallo(String numeroFallo) {
		this.numeroFallo = numeroFallo;
	}
}
