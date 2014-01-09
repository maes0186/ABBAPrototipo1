package com.conexia.saludcoop.common.dto.transaccional;

public class ValidarTopesCantidadDto {
    
    private Long idInsumo;    
    private Integer cantidad;
    private Long idAfiliado;
    
  
    public Integer getCantidad() {
        return cantidad;
    }
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    public Long getIdAfiliado() {
        return idAfiliado;
    }
    public void setIdAfiliado(Long idAfiliado) {
        this.idAfiliado = idAfiliado;
    }
    public Long getIdInsumo() {
        return idInsumo;
    }
    public void setIdInsumo(Long idInsumo) {
        this.idInsumo = idInsumo;
    }
        
    
}
