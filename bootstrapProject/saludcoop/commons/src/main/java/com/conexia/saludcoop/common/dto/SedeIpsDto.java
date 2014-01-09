package com.conexia.saludcoop.common.dto;

import java.util.Date;
import java.util.Set;

import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.entity.maestro.SedeIps;

@Mappeable(mappedTo=SedeIps.class)
public class SedeIpsDto { 
	private long serialVersionUID;
	private Long id;
	private MunicipioDto municipio;
	private Set<ProfesionalEspecialidadDto>profesionalEspecialidades;
	private IpsDto ips;
	private String codigoMinisterioSalud;
	private ContratoDto contrato;
	private String representanteLegal;
	private String direccion;
	private String telefono1;
	private String telefono2;
	private String celular;
	private String fax;
	private Date fechaHabilitacion;
	private Integer nivelDeAtencion;
	private String nombre;
	private Integer tipoServicio;
	private String  nombreIps;

    public void setSerialVersionUID(long serialVersionUID){
		this.serialVersionUID = serialVersionUID;
	}

	public long getSerialVersionUID(){
		return this.serialVersionUID;
	}
	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}
	public void setMunicipio(MunicipioDto municipio){
		this.municipio = municipio;
	}

	public MunicipioDto getMunicipio(){
		return this.municipio;
	}
	public void setProfesionalEspecialidades(Set<ProfesionalEspecialidadDto>profesionalEspecialidades){
		this.profesionalEspecialidades = profesionalEspecialidades;
	}

	public Set<ProfesionalEspecialidadDto>getProfesionalEspecialidades(){
		return this.profesionalEspecialidades;
	}
	public void setIps(IpsDto ips){
		this.ips = ips;
	}

	public IpsDto getIps(){
		return this.ips;
	}

	public void setContrato(ContratoDto contrato){
		this.contrato = contrato;
	}

	public ContratoDto getContrato(){
		return this.contrato;
	}
	public void setRepresentanteLegal(String representanteLegal){
		this.representanteLegal = representanteLegal;
	}

	public String getRepresentanteLegal(){
		return this.representanteLegal;
	}
	public void setDireccion(String direccion){
		this.direccion = direccion;
	}

	public String getDireccion(){
		return this.direccion;
	}
	public void setTelefono1(String telefono1){
		this.telefono1 = telefono1;
	}

	public String getTelefono1(){
		return this.telefono1;
	}
	public void setTelefono2(String telefono2){
		this.telefono2 = telefono2;
	}

	public String getTelefono2(){
		return this.telefono2;
	}
	public void setCelular(String celular){
		this.celular = celular;
	}

	public String getCelular(){
		return this.celular;
	}
	public void setFax(String fax){
		this.fax = fax;
	}

	public String getFax(){
		return this.fax;
	}
	public void setFechaHabilitacion(Date fechaHabilitacion){
		this.fechaHabilitacion = fechaHabilitacion;
	}

	public Date getFechaHabilitacion(){
		return this.fechaHabilitacion;
	}
	public void setNivelDeAtencion(Integer nivelDeAtencion){
		this.nivelDeAtencion = nivelDeAtencion;
	}

	public Integer getNivelDeAtencion(){
		return this.nivelDeAtencion;
	}
	
	public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

	public String getCodigoMinisterioSalud() {
		return codigoMinisterioSalud;
	}

	public void setCodigoMinisterioSalud(String codigoMinisterioSalud) {
		this.codigoMinisterioSalud = codigoMinisterioSalud;
	}

	public Integer getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(Integer tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	public String getNombreIps() {
		return nombreIps;
	}

	public void setNombreIps(String nombreIps) {
		this.nombreIps = nombreIps;
	}


	public boolean equals(Object obj) {
	    return ((SedeIpsDto)obj).getId().equals(this.id);
	  }

	  public int hashCode() {
	    return 0;
	  }

}
