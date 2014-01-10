package com.conexia.saludcoop.web.vo;

public class MedicamentoPosVO {

    String codigo;
    String descripcion;
    Integer dosis;
    Integer diasTratamiento;
    String respuestaClinicaObservada;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRespuestaClinicaObservada() {
        return respuestaClinicaObservada;
    }

    public void setRespuestaClinicaObservada(String respuestaClinicaObservada) {
        this.respuestaClinicaObservada = respuestaClinicaObservada;
    }

    public Integer getDiasTratamiento() {
        return diasTratamiento;
    }

    public void setDiasTratamiento(Integer diasTratamiento) {
        this.diasTratamiento = diasTratamiento;
    }

    public Integer getDosis() {
        return dosis;
    }

    public void setDosis(Integer dosis) {
        this.dosis = dosis;
    }
}
