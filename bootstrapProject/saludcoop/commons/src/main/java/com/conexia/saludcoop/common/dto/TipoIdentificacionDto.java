package com.conexia.saludcoop.common.dto;

import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.entity.maestro.TipoIdentificacion;

@Mappeable(mappedTo=TipoIdentificacion.class)
public class TipoIdentificacionDto {

	
	private Integer id;
	private String codigo;
	private String descripcion;
	private Short esAlfanumerico;
	private Integer minLength;
	private Integer maxLength;
	private Short aplicaIps; 
    private Short aplicaAfiliado; 
    private Short aplicaProfesional;
	
	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}
	public void setCodigo(String codigo){
		this.codigo = codigo;
	}

	public String getCodigo(){
		return this.codigo;
	}
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}

	public String getDescripcion(){
		return this.descripcion;
	}
	public Short getEsAlfanumerico() {
		return esAlfanumerico;
	}
	public void setEsAlfanumerico(Short esAlfanumerico) {
		this.esAlfanumerico = esAlfanumerico;
	}
	
	public Integer getMinLength() {
		return minLength;
	}

	public void setMinLength(Integer minLength) {
		this.minLength = minLength;
	}

	public Integer getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}

    public Short getAplicaIps() {
        return aplicaIps;
    }

    public void setAplicaIps(Short aplicaIps) {
        this.aplicaIps = aplicaIps;
    }

    public Short getAplicaAfiliado() {
        return aplicaAfiliado;
    }

    public void setAplicaAfiliado(Short aplicaAfiliado) {
        this.aplicaAfiliado = aplicaAfiliado;
    }

    public Short getAplicaProfesional() {
        return aplicaProfesional;
    }

    public void setAplicaProfesional(Short aplicaProfesional) {
        this.aplicaProfesional = aplicaProfesional;
    }
		
	
}
