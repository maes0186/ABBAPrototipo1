package com.conexia.saludcoop.web.vo;

import com.conexia.saludcoop.common.dto.SedeIpsDto;

public class SedeIpsVO {
	private Long id;
	private String municipioCodigo;
	private String municipioNombre;
	private Integer tipoIdentificacion;
	private String numeroIdentificacion;
	private String direccion;
	private String razonSocial;
	private String telefono1;
	private String nombreIps;
	private Integer tipoServicio;
	private String departamentoSedeIpsId;
	private String municipioSedeIpsId;
	
	public SedeIpsVO(){
		
	}
	
	public SedeIpsVO(SedeIpsDto sedeIpsDto){
		this.id = sedeIpsDto.getId();
		this.municipioCodigo = sedeIpsDto.getMunicipio().getCodigo();
		this.municipioNombre = sedeIpsDto.getMunicipio().getDescripcion();
		this.tipoIdentificacion = sedeIpsDto.getIps().getTipoIdentificacion().getId();
		this.numeroIdentificacion = sedeIpsDto.getIps().getNumeroIdentificacion();
		this.direccion = sedeIpsDto.getDireccion();
		this.razonSocial = sedeIpsDto.getNombre();
		this.tipoServicio = sedeIpsDto.getTipoServicio();
		
		this.departamentoSedeIpsId = String.valueOf(sedeIpsDto.getMunicipio().getDepartamento().getId());
		this.municipioSedeIpsId = String.valueOf(sedeIpsDto.getMunicipio().getId());
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMunicipioCodigo() {
		return municipioCodigo;
	}
	public void setMunicipioCodigo(String municipioCodigo) {
		this.municipioCodigo = municipioCodigo;
	}
	public String getMunicipioNombre() {
		return municipioNombre;
	}
	public void setMunicipioNombre(String municipioNombre) {
		this.municipioNombre = municipioNombre;
	}
	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}
	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
    public String getTelefono1() {
        return telefono1;
    }
    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }
    public String getNombreIps() {
        return nombreIps;
    }
    public void setNombreIps(String nombreIps) {
        this.nombreIps = nombreIps;
    }
	public Integer getTipoServicio() {
		return tipoServicio;
	}
	public void setTipoServicio(Integer tipoServicio) {
		this.tipoServicio = tipoServicio;
	}
	public Integer getTipoIdentificacion() {
		return tipoIdentificacion;
	}
	public void setTipoIdentificacion(Integer tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}
	
    public String getMunicipioSedeIpsId() {
    	return municipioSedeIpsId;
    }
	
    public void setMunicipioSedeIpsId(String municipioSedeIpsId) {
    	this.municipioSedeIpsId = municipioSedeIpsId;
    }
	
    public String getDepartamentoSedeIpsId() {
    	return departamentoSedeIpsId;
    }
	
    public void setDepartamentoSedeIpsId(String departamentoSedeIpsId) {
    	this.departamentoSedeIpsId = departamentoSedeIpsId;
    }
}
