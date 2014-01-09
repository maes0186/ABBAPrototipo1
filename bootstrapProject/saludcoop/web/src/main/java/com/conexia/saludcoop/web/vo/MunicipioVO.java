package com.conexia.saludcoop.web.vo;

import com.conexia.saludcoop.common.dto.MunicipioDto;

public class MunicipioVO {
    private Long id;
    private String codigo;
    private String descripcion;
    private Long idDepartamento;
        
    public MunicipioVO(MunicipioDto dto) {
        this.id = dto.getId();
        this.codigo = dto.getCodigo();
        this.descripcion = dto.getDescripcion();
        this.idDepartamento = dto.getDepartamento().getId();
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getIdDepartamento() {
        return idDepartamento;
    }
    public void setIdDepartamento(Long idDepartamento) {
        this.idDepartamento = idDepartamento;
    }
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
    
}
