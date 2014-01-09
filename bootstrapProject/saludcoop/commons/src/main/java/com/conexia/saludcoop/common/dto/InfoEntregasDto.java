package com.conexia.saludcoop.common.dto;


/**
 * Clase con los datos para la generación de las entregas para los medicamentos
 * 
 * @author <a href="mailto:prodas@conexia.com">Pablo Rodas</a>
 * @date 20/11/2013
 * 
 */
public class InfoEntregasDto {
    private Integer diasPeriodo;
    private Integer unidadesAprobadas;
    private Integer numeroEntregas;
    private Integer dosis;
    private Integer frecuencia;
    private Integer cada;
    private boolean solicitud;
    private Long itemId;
    
    public Integer getDiasPeriodo() {
        return diasPeriodo;
    }
    public void setDiasPeriodo(Integer diasPeriodo) {
        this.diasPeriodo = diasPeriodo;
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
    public Integer getDosis() {
        return dosis;
    }
    public void setDosis(Integer dosis) {
        this.dosis = dosis;
    }
    public Integer getFrecuencia() {
        return frecuencia;
    }
    public void setFrecuencia(Integer frecuencia) {
        this.frecuencia = frecuencia;
    }
    public Long getItemId() {
        return itemId;
    }
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
    public boolean isSolicitud() {
        return solicitud;
    }
    public void setSolicitud(boolean solicitud) {
        this.solicitud = solicitud;
    }
    public Integer getCada() {
        return cada;
    }
    public void setCada(Integer cada) {
        this.cada = cada;
    }
    
}
