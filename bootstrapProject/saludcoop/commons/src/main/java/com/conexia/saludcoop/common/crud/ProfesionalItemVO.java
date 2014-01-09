package com.conexia.saludcoop.common.crud;


/**
 * 
 * @author mortega
 *
 */
public class ProfesionalItemVO {
	
	private Long id;
	
	/**
	 * Define el registro medico del profesional
	 */
	
	private String registroMedico;
	/**
	 * 
	 */
	private String tipoDocumento;
	
	/**
	 * 
	 */
	private String numeroIdentificacion;
	
	
	/**
	 * Define el primer nombre del profesional
	 */
	
	private String primerNombre;
	
	/**
	 * Define el segundo nombre del profesional
	 */
	
	private String segundoNombre;

	/**
	 * Define el primer apellido del profesional
	 */
	
	private String primerApellido;

	/**
	 * Define el primer apellido del profesional
	 */
	
	private String segundoApellido;
	
	

	
	
	
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

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


}
 