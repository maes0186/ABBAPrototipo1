package com.conexia.saludcoop.web.vo;


public class ConceptoCSVO {

    private Integer periodoAprobado;
    private Integer unidadesAprobadas;
    private Integer numeroEntregas;
    private Integer diasPeriodo;
    private Integer dosisAprobada;
    private String justificacion;
    private Integer lateralidad;
    
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

}
