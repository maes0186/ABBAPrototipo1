package com.conexia.saludcoop.common.dto;

import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.entity.maestro.InsumoTope;

@Mappeable(mappedTo=InsumoTope.class)
public class InsumoTopeDto {

    private Long id;
    
    private InsumoDto insumo;
    
    private Integer tope;
    
    private Integer periodicidadDias;
    
    private Long tipoInsumoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InsumoDto getInsumo() {
        return insumo;
    }

    public void setInsumo(InsumoDto insumo) {
        this.insumo = insumo;
    }

    public Integer getTope() {
        return tope;
    }

    public void setTope(Integer tope) {
        this.tope = tope;
    }

    public Integer getPeriodicidadDias() {
        return periodicidadDias;
    }

    public void setPeriodicidadDias(Integer periodicidadDias) {
        this.periodicidadDias = periodicidadDias;
    }

    public Long getTipoInsumoId() {
        return tipoInsumoId;
    }

    public void setTipoInsumoId(Long tipoInsumoId) {
        this.tipoInsumoId = tipoInsumoId;
    }
        
}
