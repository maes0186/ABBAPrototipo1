package com.conexia.saludcoop.web.vo;

import java.util.List;

public class InfoBandejaMedicamentoVO {
    
    private List<MedicamentoPosPrevioVO> medicamentosAnteriores;    
    private boolean sinAlternativaPOSPrev;                              
    private String justificacionSinPOSPrev;
    private List<DescriptivoVO> adjuntos;
    private MedicamentoCTCVO medicamentoSolicitado;
    private Integer cantidadMedicamentoSolicitado;
    private MedicamentoCTCVO medicamentoHomologo;
    private Integer cantidadMedicamentoHomologo;
    private String registroINVIMAMedicamentoSolicitado;    
    private Integer diasTratamiento;
    private String posologia;
    private String tratamientoPOS;
    private Integer valorTratamiento;
    private String riesgoInminente;
    private String justificacionRiesgo;
    private String justificacionMedico;
    private String posibilidadesAgotadas;
    private String autorizacionINVIMA;
    private boolean tieneFormCTC = false;
    
    public List<DescriptivoVO> getAdjuntos() {
        return adjuntos;
    }

    public void setAdjuntos(List<DescriptivoVO> adjuntos) {
        this.adjuntos = adjuntos;
    }

    public String getRegistroINVIMAMedicamentoSolicitado() {
        return registroINVIMAMedicamentoSolicitado;
    }

    public void setRegistroINVIMAMedicamentoSolicitado(
            String registroINVIMAMedicamentoSolicitado) {
        this.registroINVIMAMedicamentoSolicitado = registroINVIMAMedicamentoSolicitado;
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

    public MedicamentoCTCVO getMedicamentoSolicitado() {
        return medicamentoSolicitado;
    }

    public void setMedicamentoSolicitado(MedicamentoCTCVO medicamentoSolicitado) {
        this.medicamentoSolicitado = medicamentoSolicitado;
    }

    public MedicamentoCTCVO getMedicamentoHomologo() {
        return medicamentoHomologo;
    }

    public void setMedicamentoHomologo(MedicamentoCTCVO medicamentoHomologo) {
        this.medicamentoHomologo = medicamentoHomologo;
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

    public String getAutorizacionINVIMA() {
        return autorizacionINVIMA;
    }

    public void setAutorizacionINVIMA(String autorizacionINVIMA) {
        this.autorizacionINVIMA = autorizacionINVIMA;
    }

    public List<MedicamentoPosPrevioVO> getMedicamentosAnteriores() {
        return medicamentosAnteriores;
    }

    public void setMedicamentosAnteriores(List<MedicamentoPosPrevioVO> medicamentosAnteriores) {
        this.medicamentosAnteriores = medicamentosAnteriores;
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

    public Integer getCantidadMedicamentoSolicitado() {
        return cantidadMedicamentoSolicitado;
    }

    public void setCantidadMedicamentoSolicitado(Integer cantidadMedicamentoSolicitado) {
        this.cantidadMedicamentoSolicitado = cantidadMedicamentoSolicitado;
    }

    public Integer getCantidadMedicamentoHomologo() {
        return cantidadMedicamentoHomologo;
    }

    public void setCantidadMedicamentoHomologo(Integer cantidadMedicamentoHomologo) {
        this.cantidadMedicamentoHomologo = cantidadMedicamentoHomologo;
    }

    public String getPosologia() {
        return posologia;
    }

    public void setPosologia(String posologia) {
        this.posologia = posologia;
    }

    public boolean isTieneFormCTC() {
        return tieneFormCTC;
    }

    public void setTieneFormCTC(boolean tieneFormCTC) {
        this.tieneFormCTC = tieneFormCTC;
    }
}
