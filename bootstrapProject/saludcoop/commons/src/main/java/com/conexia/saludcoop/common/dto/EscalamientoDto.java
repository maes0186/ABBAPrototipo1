package com.conexia.saludcoop.common.dto;

public class EscalamientoDto {
    private String nombreBandeja;
    private Long itemId;
    
    public String getNombreBandeja() {
        return nombreBandeja;
    }
    public void setNombreBandeja(String nombreBandeja) {
        this.nombreBandeja = nombreBandeja;
    }
    public Long getItemId() {
        return itemId;
    }
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
}
