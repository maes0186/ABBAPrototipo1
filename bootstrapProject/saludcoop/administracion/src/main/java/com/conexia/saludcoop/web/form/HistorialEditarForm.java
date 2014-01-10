package com.conexia.saludcoop.web.form;

public class HistorialEditarForm {

    private Long registroEditarId;
    private Double valorEditar;
    private Integer anioEditar;
    
    public Long getRegistroEditarId() {
        return registroEditarId;
    }
    public void setRegistroEditarId(Long id) {
        this.registroEditarId = id;
    }
    public Double getValorEditar() {
        return valorEditar;
    }
    public void setValorEditar(Double valor) {
        this.valorEditar = valor;
    }
    public Integer getAnioEditar() {
        return anioEditar;
    }
    public void setAnioEditar(Integer anio) {
        this.anioEditar = anio;
    }
    
    
}
