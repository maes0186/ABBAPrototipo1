package com.conexia.saludcoop.web.vo;


public class ConceptoCTCVO {

    private String tipoRespuestaCTC;
    private Integer periodoAprobado;
    private Integer unidadesAprobadas;
    private Integer numeroEntregas;
    private Integer diasPeriodo;
    private Integer dosisAprobada;
    private String justificacion;

    public String getTipoRespuestaCTC() {
        return tipoRespuestaCTC;
    }

    public void setTipoRespuestaCTC(String tipoRespuestaCTC) {
        this.tipoRespuestaCTC = tipoRespuestaCTC;
    }

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

}
