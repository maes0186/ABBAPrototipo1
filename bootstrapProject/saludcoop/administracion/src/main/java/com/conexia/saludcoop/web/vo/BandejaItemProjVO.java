package com.conexia.saludcoop.web.vo;

import java.text.SimpleDateFormat;
import java.util.Date;


public class BandejaItemProjVO {

	private final static SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	
	private Integer numeroSolicitud;
	private Long numeroAutorizacion;
	private Date fechaCreacionSolicitud;
	private String fechaModificacionSolicitud;
	private String tipoIdentificacionAfiliado;
	private String numeroIdentificacionAfiliado;
	private String eps;
	private String regional;
	private String programa;
	
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
	
	// Tipo de redireccion
	
	private Boolean esNivelAutorizacionAuditor;


	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public Integer getNumeroSolicitud() {
		return numeroSolicitud;
	}
	
	public void setNumeroSolicitud(Integer numeroSolicitud) {
		this.numeroSolicitud = numeroSolicitud;
	}
	
	public Long getNumeroAutorizacion() {
		return numeroAutorizacion;
	}
	
	public void setNumeroAutorizacion(Long numeroAutorizacion) {
		this.numeroAutorizacion = numeroAutorizacion;
	}

	public String getFechaCreacionSolicitud() {
		return BandejaItemProjVO.SDF.format(this.fechaCreacionSolicitud) ;
	}

	public void setFechaCreacionSolicitud(Date fechaCreacionSolicitud) {
		this.fechaCreacionSolicitud = fechaCreacionSolicitud;
	}

	public String getFechaModificacionSolicitud() {
		return fechaModificacionSolicitud;
	}

	public void setFechaModificacionSolicitud(String fechaModificacionSolicitud) {
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

	public String getRegional() {
		return regional;
	}

	public void setRegional(String regional) {
		this.regional = regional;
	}

	public String getProfesionalSolicitante() {

		return (this.primerNombreSolicitante + " " + this.segundoNombreSolicitante + " "
		        + this.primerApellidoSolicitante + " " + this.segundoApellidoSolicitante);
	}


	@Override
	public boolean equals(Object obj) {
		return this.numeroSolicitud.equals(((BandejaItemProjVO)obj).numeroSolicitud);
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
    /**
     * 
     * @return
     */
    public Boolean getEsNivelAutorizacionAuditor() {
		return esNivelAutorizacionAuditor;
	}
    /**
     * 
     * @param esNivelAutorizacionAuditor
     */
	public void setEsNivelAutorizacionAuditor(Boolean esNivelAutorizacionAuditor) {
		this.esNivelAutorizacionAuditor = esNivelAutorizacionAuditor;
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
	    builder.append(regional);
	    builder.append(", programa=");
	    builder.append(programa);
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
