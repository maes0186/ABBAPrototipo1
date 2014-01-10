package com.conexia.saludcoop.web.vo;

import com.conexia.saludcoop.common.dto.ProfesionalEspecialidadDto;

public class ProfesionalVO {
	private Long id;
	private Long profesionalId;
	private String registroMedico;
	private Integer tipoDocumento;
	private String tipoDocumentoDescripcion;
	private String numeroDocumento;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private String nombreCompleto;
	private Integer especialidad;
	private String especialidadDescripcion;

	public ProfesionalVO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProfesionalVO(ProfesionalEspecialidadDto profesionalDto) {
		this.id = profesionalDto.getId();
		this.registroMedico = profesionalDto.getProfesional().getRegistroMedico();
		this.tipoDocumento = profesionalDto.getProfesional().getIdentificacionProfesional().getId();
		this.numeroDocumento = profesionalDto.getProfesional().getNumeroIdentificacion();
		this.primerNombre = profesionalDto.getProfesional().getPrimerNombre();
		this.segundoNombre = profesionalDto.getProfesional().getSegundoNombre();
		this.primerApellido = profesionalDto.getProfesional().getPrimerApellido();
		this.segundoApellido = profesionalDto.getProfesional().getSegundoApellido();
		this.nombreCompleto = this.primerNombre + "  " + this.segundoNombre + "  " + this.primerApellido + "  " + this.segundoApellido;
		this.profesionalId = profesionalDto.getProfesional().getId();
		this.especialidad = profesionalDto.getEspecialidad().getId();
		this.especialidadDescripcion = profesionalDto.getEspecialidad().getDescripcion();
		this.tipoDocumentoDescripcion = profesionalDto.getProfesional().getIdentificacionProfesional().getDescripcion();
	}

	public Long getProfesionalId() {
		return profesionalId;
	}

	public void setProfesionalId(Long profesionalId) {
		this.profesionalId = profesionalId;
	}

	public String getRegistroMedico() {
		return registroMedico;
	}

	public void setRegistroMedico(String registroMedico) {
		this.registroMedico = registroMedico;
	}

	public Integer getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(Integer tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
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

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public Integer getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Integer especialidad) {
		this.especialidad = especialidad;
	}

	public String getEspecialidadDescripcion() {
		return especialidadDescripcion;
	}

	public void setEspecialidadDescripcion(String especialidadDescripcion) {
		this.especialidadDescripcion = especialidadDescripcion;
	}
	
    public String getTipoDocumentoDescripcion() {
    	return tipoDocumentoDescripcion;
    }
	
    public void setTipoDocumentoDescripcion(String tipoDocumentoDescripcion) {
    	this.tipoDocumentoDescripcion = tipoDocumentoDescripcion;
    }
}
