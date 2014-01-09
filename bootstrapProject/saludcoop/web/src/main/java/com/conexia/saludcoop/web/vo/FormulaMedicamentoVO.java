package com.conexia.saludcoop.web.vo;

import com.conexia.saludcoop.common.dto.transaccional.FormulaMedicamentoDto;

public class FormulaMedicamentoVO {
    private Long id;
    private Integer dosis;
    private Integer frecuencia;
    private Integer duracion;
    private DescriptivoVO viaAdministracion;
    private String posologia;
    private String efectosAdversos;
    
    public FormulaMedicamentoVO(FormulaMedicamentoDto dto){
        this.id = dto.getId();
        this.dosis = dto.getDosis();
        this.frecuencia = dto.getFrecuencia();
        this.duracion = dto.getDuracion();
        this.viaAdministracion = new DescriptivoVO(dto.getViaAdministracion());
        this.posologia = dto.getPosologia();
        this.efectosAdversos = dto.getEfectosAdversos();
    }
    public String getEfectosAdversos() {
        return efectosAdversos;
    }

    public void setEfectosAdversos(String efectosAdversos) {
        this.efectosAdversos = efectosAdversos;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }
    public void setDosis(Integer dosis){
        this.dosis = dosis;
    }

    public Integer getDosis(){
        return this.dosis;
    }
    public void setFrecuencia(Integer frecuencia){
        this.frecuencia = frecuencia;
    }

    public Integer getFrecuencia(){
        return this.frecuencia;
    }
    public void setDuracion(Integer duracion){
        this.duracion = duracion;
    }

    public Integer getDuracion(){
        return this.duracion;
    }
    public void setViaAdministracion(DescriptivoVO viaAdministracion){
        this.viaAdministracion = viaAdministracion;
    }

    public DescriptivoVO getViaAdministracion(){
        return this.viaAdministracion;
    }
    public void setPosologia(String posologia){
        this.posologia = posologia;
    }

    public String getPosologia(){
        return this.posologia;
    }
}
