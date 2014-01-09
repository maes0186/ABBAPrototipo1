package com.conexia.saludcoop.web.vo;

import com.conexia.saludcoop.common.dto.TipoIdentificacionDto;

public class TipoIdentificacionVO {

	private Integer id;
	private String codigo;
	private String descripcion;
	private Boolean esAlfanumerico;
	private String minLength;
	private String maxLength;
    private Boolean aplicaIps; 
    private Boolean aplicaAfiliado; 
    private Boolean aplicaProfesional;
	
	
	
	public TipoIdentificacionVO() {}
	
	public TipoIdentificacionVO(Integer id, String codigo, String descripcion, Short esAlfa, String minLength, String maxLength,
	        Short aplicaIps, Short aplicaAfiliado, Short aplicaProfesional) {
		this.id = id;
		this.codigo = codigo ;
		this.descripcion = descripcion;
		this.esAlfanumerico = esAlfa>0?true:false;
		this.minLength = minLength;
		this.maxLength = maxLength;
		this.aplicaIps = aplicaIps>0?true:false; 
		this.aplicaAfiliado = aplicaAfiliado>0?true:false; 
		this.aplicaProfesional = aplicaProfesional>0?true:false;
	}
	public TipoIdentificacionVO(TipoIdentificacionDto dto){
		this.id = dto.getId();
		this.codigo = dto.getCodigo() ;
		this.descripcion = dto.getDescripcion();
		this.esAlfanumerico = dto.getEsAlfanumerico()>0?true:false;
		this.minLength = dto.getMinLength().toString();
		this.maxLength = dto.getMaxLength().toString();
		this.aplicaIps = dto.getAplicaIps()>0?true:false;
		this.aplicaAfiliado = dto.getAplicaAfiliado()>0?true:false;
		this.aplicaProfesional = dto.getAplicaProfesional()>0?true:false;
	}
	
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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

	public Boolean getEsAlfanumerico() {
		return esAlfanumerico;
	}

	public void setEsAlfanumerico(Boolean esAlfanumerico) {
		this.esAlfanumerico = esAlfanumerico;
	}

	public String getMinLength() {
		return minLength;
	}

	public void setMinLength(String minLength) {
		this.minLength = minLength;
	}

	public String getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(String maxLength) {
		this.maxLength = maxLength;
	}

    public Boolean getAplicaIps() {
        return aplicaIps;
    }

    public void setAplicaIps(Boolean aplicaIps) {
        this.aplicaIps = aplicaIps;
    }

    public Boolean getAplicaAfiliado() {
        return aplicaAfiliado;
    }

    public void setAplicaAfiliado(Boolean aplicaAfiliado) {
        this.aplicaAfiliado = aplicaAfiliado;
    }

    public Boolean getAplicaProfesional() {
        return aplicaProfesional;
    }

    public void setAplicaProfesional(Boolean aplicaProfesional) {
        this.aplicaProfesional = aplicaProfesional;
    }
	
}
