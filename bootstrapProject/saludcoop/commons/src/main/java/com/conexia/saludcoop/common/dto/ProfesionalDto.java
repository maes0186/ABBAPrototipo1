package com.conexia.saludcoop.common.dto;



import org.codehaus.jackson.annotate.JsonIgnore;

import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.entity.maestro.Profesional;

@Mappeable(mappedTo=Profesional.class)
public class ProfesionalDto { 
	private Long id;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private String registroMedico;
	private String direccion;
	private String telefono1;
	private String telefono2;
	private String telefonoAtencion;
	private String celular;
	private String fax;
	private String beeper;
	private String email;
	private String firmaImpresa;
	private DivisionSeccionalDto divisionSeccional;
	private MunicipioDto municipio;
	private TipoIdentificacionDto identificacionProfesional;
	private String tipoIdentificacion;
	private String numeroIdentificacion;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPrimerNombre() {
		return primerNombre;
	}
	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}
	public String getSegundoNombre() {
		return segundoNombre;
	}
	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}
	public String getPrimerApellido() {
		return primerApellido;
	}
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	public String getSegundoApellido() {
		return segundoApellido;
	}
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
	public String getRegistroMedico() {
		return registroMedico;
	}
	public void setRegistroMedico(String registroMedico) {
		this.registroMedico = registroMedico;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono1() {
		return telefono1;
	}
	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}
	public String getTelefono2() {
		return telefono2;
	}
	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}
	public String getTelefonoAtencion() {
		return telefonoAtencion;
	}
	public void setTelefonoAtencion(String telefonoAtencion) {
		this.telefonoAtencion = telefonoAtencion;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getBeeper() {
		return beeper;
	}
	public void setBeeper(String beeper) {
		this.beeper = beeper;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirmaImpresa() {
		return firmaImpresa;
	}
	public void setFirmaImpresa(String firmaImpresa) {
		this.firmaImpresa = firmaImpresa;
	}
	public DivisionSeccionalDto getDivisionSeccional() {
		return divisionSeccional;
	}
	public void setDivisionSeccional(DivisionSeccionalDto divisionSeccional) {
		this.divisionSeccional = divisionSeccional;
	}
	public MunicipioDto getMunicipio() {
		return municipio;
	}
	public void setMunicipio(MunicipioDto municipio) {
		this.municipio = municipio;
	}

	public TipoIdentificacionDto getIdentificacionProfesional() {
		return identificacionProfesional;
	}
	public void setIdentificacionProfesional(TipoIdentificacionDto identificacionProfesional) {
		this.identificacionProfesional = identificacionProfesional;
	}
	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}
	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}
	
	@JsonIgnore
	public String getNombreCompleto() {
		return this.primerNombre + " " + this.segundoNombre + " " + this.primerApellido + " " + this.segundoApellido; 
	}
	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}
	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}
}
