package com.conexia.saludcoop.web.vo;

import java.text.SimpleDateFormat;
import java.util.Date;


public class BandejaAutorizacionItemProjVO {

	private final static SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	
	private Long numeroSolicitud;
	private Date fechaCreacionSolicitud;
	private Date fechaModificacionSolicitud;
	private String tipoIdentificacionAfiliado;
	private String numeroIdentificacionAfiliado;
	private String eps;
	
	//Nombre Afiliado
	private String codigoIdentificacion;
	private String numeroIdentificacion;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	
	// Nombre solicitante
	private String primerNombreSolicitante;
	private String segundoNombreSolicitante;
	private String primerApellidoSolicitante;
	private String segundoApellidoSolicitante;
	
	private String tecnologia;
	private String tipoSolicitud;
	private String diagnostico;

	private String procedimiento;
	private String medicamento;


	public String getTecnologia() {

		return tecnologia;
	}

	public void setTecnologia(String tecnologia) {

		this.tecnologia = tecnologia;
	}

	public String getTipoSolicitud() {

		return tipoSolicitud;
	}

	public void setTipoSolicitud(String tipoSolicitud) {

		this.tipoSolicitud = tipoSolicitud;
	}

	public String getServicioSolicitado() {

		return this.medicamento == null ? this.procedimiento : medicamento;
	}

	public String getDiagnostico() {

		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {

		this.diagnostico = diagnostico;
	}

	/**
	 * @param procedimiento the procedimiento to set
	 */
	public void setProcedimiento(String procedimiento) {

		this.procedimiento = procedimiento;
	}

	/**
	 * @param medicamento the medicamento to set
	 */
	public void setMedicamento(String medicamento) {

		this.medicamento = medicamento;
	}

	public Long getNumeroSolicitud() {
		return numeroSolicitud;
	}

	public void setNumeroSolicitud(Long numeroSolicitud) {
		this.numeroSolicitud = numeroSolicitud;
	}

	public String getFechaCreacionSolicitud() {
		return this.SDF.format(this.fechaCreacionSolicitud) ;
	}

	public void setFechaCreacionSolicitud(Date fechaCreacionSolicitud) {
		this.fechaCreacionSolicitud = fechaCreacionSolicitud;
	}

	public String getFechaModificacionSolicitud() {
		return this.SDF.format(this.fechaModificacionSolicitud) ;
	}

	public void setFechaModificacionSolicitud(Date fechaModificacionSolicitud) {
		this.fechaModificacionSolicitud = fechaModificacionSolicitud;
	}

	public String getTipoIdentificacionAfiliado() {
		return tipoIdentificacionAfiliado;
	}

	public void setTipoIdentificacionAfiliado(String tipoIdentificacionAfiliado) {
		this.tipoIdentificacionAfiliado = tipoIdentificacionAfiliado;
	}

	public String getNumeroIdentificacionAfiliado() {
		return numeroIdentificacionAfiliado;
	}

	public void setNumeroIdentificacionAfiliado(String numeroIdentificacionAfiliado) {
		this.numeroIdentificacionAfiliado = numeroIdentificacionAfiliado;
	}

	public String getNombreAfiliado() {
		
		return (this.codigoIdentificacion + " " + this.numeroIdentificacion + " "
		        + this.primerNombre + " " + this.segundoNombre + " " + this.primerApellido + " " + this.segundoApellido);
		
	}

	public String getEps() {
		return eps;
	}

	public void setEps(String eps) {
		this.eps = eps;
	}

	public String getProfesionalSolicitante() {

		return (this.primerNombreSolicitante + " " + this.segundoNombreSolicitante + " "
		        + this.primerApellidoSolicitante + " " + this.segundoApellidoSolicitante);
	}


	@Override
	public boolean equals(Object obj) {
		return this.numeroSolicitud.equals(((BandejaAutorizacionItemProjVO)obj).numeroSolicitud);
	}

	
    /**
     * @param codigoIdentificacion the codigoIdentificacion to set
     */
    public void setCodigoIdentificacion(String codigoIdentificacion) {
    
    	this.codigoIdentificacion = codigoIdentificacion;
    }

	
    /**
     * @param numeroIdentificacion the numeroIdentificacion to set
     */
    public void setNumeroIdentificacion(String numeroIdentificacion) {
    
    	this.numeroIdentificacion = numeroIdentificacion;
    }

	
    /**
     * @param primerNombre the primerNombre to set
     */
    public void setPrimerNombre(String primerNombre) {
    
    	this.primerNombre = primerNombre;
    }

	
    /**
     * @param segundoNombre the segundoNombre to set
     */
    public void setSegundoNombre(String segundoNombre) {
    
    	this.segundoNombre = segundoNombre;
    }

	
    /**
     * @param primerApellido the primerApellido to set
     */
    public void setPrimerApellido(String primerApellido) {
    
    	this.primerApellido = primerApellido;
    }

	
    /**
     * @param segundoApellido the segundoApellido to set
     */
    public void setSegundoApellido(String segundoApellido) {
    
    	this.segundoApellido = segundoApellido;
    }

	
    /**
     * @param primerNombreSolicitante the primerNombreSolicitante to set
     */
    public void setPrimerNombreSolicitante(String primerNombreSolicitante) {
    
    	this.primerNombreSolicitante = primerNombreSolicitante;
    }

	
    /**
     * @param segundoNombreSolicitante the segundoNombreSolicitante to set
     */
    public void setSegundoNombreSolicitante(String segundoNombreSolicitante) {
    
    	this.segundoNombreSolicitante = segundoNombreSolicitante;
    }

	
    /**
     * @param primerApellidoSolicitante the primerApellidoSolicitante to set
     */
    public void setPrimerApellidoSolicitante(String primerApellidoSolicitante) {
    
    	this.primerApellidoSolicitante = primerApellidoSolicitante;
    }

	
    /**
     * @param segundoApellidoSolicitante the segundoApellidoSolicitante to set
     */
    public void setSegundoApellidoSolicitante(String segundoApellidoSolicitante) {
    
    	this.segundoApellidoSolicitante = segundoApellidoSolicitante;
    }

    @Override
    public String toString() {

	    StringBuilder builder = new StringBuilder();
	    builder.append("BandejaItemProjVO [numeroSolicitud=");
	    builder.append(numeroSolicitud);
	    builder.append(", fechaCreacionSolicitud=");
	    builder.append(fechaCreacionSolicitud);
	    builder.append(", fechaModificacionSolicitud=");
	    builder.append(fechaModificacionSolicitud);
	    builder.append(", tipoIdentificacionAfiliado=");
	    builder.append(tipoIdentificacionAfiliado);
	    builder.append(", numeroIdentificacionAfiliado=");
	    builder.append(numeroIdentificacionAfiliado);
	    builder.append(", eps=");
	    builder.append(eps);
	    builder.append(", regional=");
	    builder.append(", programa=");
	    builder.append(", codigoIdentificacion=");
	    builder.append(codigoIdentificacion);
	    builder.append(", numeroIdentificacion=");
	    builder.append(numeroIdentificacion);
	    builder.append(", primerNombre=");
	    builder.append(primerNombre);
	    builder.append(", segundoNombre=");
	    builder.append(segundoNombre);
	    builder.append(", primerApellido=");
	    builder.append(primerApellido);
	    builder.append(", segundoApellido=");
	    builder.append(segundoApellido);
	    builder.append(", primerNombreSolicitante=");
	    builder.append(primerNombreSolicitante);
	    builder.append(", segundoNombreSolicitante=");
	    builder.append(segundoNombreSolicitante);
	    builder.append(", primerApellidoSolicitante=");
	    builder.append(primerApellidoSolicitante);
	    builder.append(", segundoApellidoSolicitante=");
	    builder.append(segundoApellidoSolicitante);
	    builder.append("]");
	    return builder.toString();
    }
	}
