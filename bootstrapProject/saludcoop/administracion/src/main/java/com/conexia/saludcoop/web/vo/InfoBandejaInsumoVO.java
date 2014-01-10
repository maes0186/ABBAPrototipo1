package com.conexia.saludcoop.web.vo;

import java.util.List;

public class InfoBandejaInsumoVO {
	 
    private List<InsumoPosPrevioVO> insumosAnteriores;    
    private boolean sinAlternativaPOSPrev;                              
    private String justificacionSinPOSPrev;
    private List<DescriptivoVO> adjuntos;
    private InsumoCTCVO insumoSolicitado;
    private Integer cantidadInsumoSolicitado;
    private InsumoCTCVO insumoHomologo;
    private Integer cantidadInsumoHomologo;
        
    private Integer diasTratamiento;
    
    private String tratamientoPOS;
    private Integer valorTratamiento;
    private String riesgoInminente;
    private String justificacionRiesgo;
    private String justificacionMedico;
    private String posibilidadesAgotadas;
    
    private boolean tieneFormCTC = false;
    
    public List<DescriptivoVO> getAdjuntos() {
        return adjuntos;
    }

    public void setAdjuntos(List<DescriptivoVO> adjuntos) {
        this.adjuntos = adjuntos;
    }

    public String getTratamientoPOS() {
        return tratamientoPOS;
    }

    public void setTratamientoPOS(String tratamientoPOS) {
        this.tratamientoPOS = tratamientoPOS;
    }

    public Integer getValorTratamiento() {
        return valorTratamiento;
    }

    public void setValorTratamiento(Integer valorTratamiento) {
        this.valorTratamiento = valorTratamiento;
    }

    public Integer getDiasTratamiento() {
        return diasTratamiento;
    }

    public void setDiasTratamiento(Integer diasTratamiento) {
        this.diasTratamiento = diasTratamiento;
    }

    public InsumoCTCVO getInsumoSolicitado() {
        return insumoSolicitado;
    }

    public void setInsumoSolicitado(InsumoCTCVO insumoSolicitado) {
        this.insumoSolicitado = insumoSolicitado;
    }

    public InsumoCTCVO getInsumoHomologo() {
        return insumoHomologo;
    }

    public void setInsumoHomologo(InsumoCTCVO insumoHomologo) {
        this.insumoHomologo = insumoHomologo;
    }

    public String getRiesgoInminente() {
        return riesgoInminente;
    }

    public void setRiesgoInminente(String riesgoInminente) {
        this.riesgoInminente = riesgoInminente;
    }

    public String getJustificacionRiesgo() {
        return justificacionRiesgo;
    }

    public void setJustificacionRiesgo(String justificacionRiesgo) {
        this.justificacionRiesgo = justificacionRiesgo;
    }

    public String getJustificacionMedico() {
        return justificacionMedico;
    }

    public void setJustificacionMedico(String justificacionMedico) {
        this.justificacionMedico = justificacionMedico;
    }

    public String getPosibilidadesAgotadas() {
        return posibilidadesAgotadas;
    }

    public void setPosibilidadesAgotadas(String posibilidadesAgotadas) {
        this.posibilidadesAgotadas = posibilidadesAgotadas;
    }

    public List<InsumoPosPrevioVO> getInsumosAnteriores() {
        return insumosAnteriores;
    }

    public void setInsumosAnteriores(List<InsumoPosPrevioVO> insumosAnteriores) {
        this.insumosAnteriores = insumosAnteriores;
    }

    public boolean isSinAlternativaPOSPrev() {
        return sinAlternativaPOSPrev;
    }

    public void setSinAlternativaPOSPrev(boolean sinAlternativaPOSPrev) {
        this.sinAlternativaPOSPrev = sinAlternativaPOSPrev;
    }

    public String getJustificacionSinPOSPrev() {
        return justificacionSinPOSPrev;
    }

    public void setJustificacionSinPOSPrev(String justificacionSinPOSPrev) {
        this.justificacionSinPOSPrev = justificacionSinPOSPrev;
    }

    public Integer getCantidadInsumoSolicitado() {
        return cantidadInsumoSolicitado;
    }

    public void setCantidadInsumoSolicitado(Integer cantidadInsumoSolicitado) {
        this.cantidadInsumoSolicitado = cantidadInsumoSolicitado;
    }

    public Integer getCantidadInsumoHomologo() {
        return cantidadInsumoHomologo;
    }

    public void setCantidadInsumoHomologo(Integer cantidadInsumoHomologo) {
        this.cantidadInsumoHomologo = cantidadInsumoHomologo;
    }

    public boolean isTieneFormCTC() {
        return tieneFormCTC;
    }

    public void setTieneFormCTC(boolean tieneFormCTC) {
        this.tieneFormCTC = tieneFormCTC;
    }


}
