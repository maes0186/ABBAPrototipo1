package com.conexia.saludcoop.web.vo;

import java.util.List;

/** 
 * Clase usada para mostrar en pantalla la información de devolución de solicitudes
 * 
 * @author <a href="mailto:prodas@conexia.com">Pablo Rodas</a>
 * @date 22/10/2013
 * 
 */
public class InfoDevolucionVO {
    private String justificacion;
    private String causalDevolucion;
    private String causalAnulacion;
    private Integer concepto;
    private List<String> criteriosNegacion;
    private Integer periodoAprobado;
    private Integer unidadesAprobadas;
    private Integer numeroEntregas;
    private Integer diasPeriodo;
    private Integer dosisAprobada;
    private String lateralidad;
    
    public String getJustificacion() {
        return justificacion;
    }
    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }
    public String getCausalDevolucion() {
        return causalDevolucion;
    }
    public void setCausalDevolucion(String causalDevolucion) {
        this.causalDevolucion = causalDevolucion;
    }
    public String getCausalAnulacion() {
        return causalAnulacion;
    }
    public void setCausalAnulacion(String causalAnulacion) {
        this.causalAnulacion = causalAnulacion;
    }
    public Integer getConcepto() {
        return concepto;
    }
    public void setConcepto(Integer concepto) {
        this.concepto = concepto;
    }
    public List<String> getCriteriosNegacion() {
        return criteriosNegacion;
    }
    public void setCriteriosNegacion(List<String> criteriosNegacion) {
        this.criteriosNegacion = criteriosNegacion;
    }
    public Integer getPeriodoAprobado() {
        return periodoAprobado;
    }
    public void setPeriodoAprobado(Integer periodoAprobado) {
        this.periodoAprobado = periodoAprobado;
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
    public String getLateralidad() {
        return lateralidad;
    }
    public void setLateralidad(String lateralidad) {
        this.lateralidad = lateralidad;
    }
    
}
