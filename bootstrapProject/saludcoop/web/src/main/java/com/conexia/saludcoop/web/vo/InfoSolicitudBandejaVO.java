package com.conexia.saludcoop.web.vo;


public class InfoSolicitudBandejaVO {

    private Integer causaExternaSeleccionada;
    private Integer finalidadSeleccionada;
    private Integer tipoCatastroficoSeleccionado;
    private Integer entidadSeleccionada;
    private String descripcionEntidad;
    private String descHistClinica;

    public String getDescripcionEntidad() {
        return descripcionEntidad;
    }

    public void setDescripcionEntidad(String descripcionEntidad) {
        this.descripcionEntidad = descripcionEntidad;
    }

    public String getDescHistClinica() {
        return descHistClinica;
    }

    public void setDescHistClinica(String descHistClinica) {
        this.descHistClinica = descHistClinica;
    }

    public Integer getCausaExternaSeleccionada() {
        return causaExternaSeleccionada;
    }

    public void setCausaExternaSeleccionada(Integer causaExternaSeleccionada) {
        this.causaExternaSeleccionada = causaExternaSeleccionada;
    }

    public Integer getFinalidadSeleccionada() {
        return finalidadSeleccionada;
    }

    public void setFinalidadSeleccionada(Integer finalidadSeleccionada) {
        this.finalidadSeleccionada = finalidadSeleccionada;
    }

    public Integer getTipoCatastroficoSeleccionado() {
        return tipoCatastroficoSeleccionado;
    }

    public void setTipoCatastroficoSeleccionado(Integer tipoCatastroficoSeleccionado) {
        this.tipoCatastroficoSeleccionado = tipoCatastroficoSeleccionado;
    }

    public Integer getEntidadSeleccionada() {
        return entidadSeleccionada;
    }

    public void setEntidadSeleccionada(Integer entidadSeleccionada) {
        this.entidadSeleccionada = entidadSeleccionada;
    }

}
