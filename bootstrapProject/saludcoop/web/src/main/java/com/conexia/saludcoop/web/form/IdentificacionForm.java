/**
 * 
 */
package com.conexia.saludcoop.web.form;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author nobregon
 * 
 */
public class IdentificacionForm {
	@Autowired
	private Integer tipoIdentificacion;
	private String numeroIdentificacion;
	private String nombres;
	private String apellidos;
	private Long eps;

	private String target;

	public Integer getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(Integer tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	
	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Long getEps() {
		return eps;
	}
	
	public void setEps(Long eps) {
		this.eps = eps;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

}
