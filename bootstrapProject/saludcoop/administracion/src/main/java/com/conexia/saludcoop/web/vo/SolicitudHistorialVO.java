package com.conexia.saludcoop.web.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 
 * @author mortega
 *
 */
public class SolicitudHistorialVO implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private final static SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
    
    private Long numeroSolicitud;
    private String regional;
    private Date fecha;
    private String descripcionProducto;
    private String estado;
    private String tipoDeSolicitud;
    private String codigoProducto;
    private Integer unidadesAprobadas;
    private Integer periodoAprobado;
    private Integer diasPeriodo;


    public String getRegional() {
        return regional;
    }

    public void setRegional(String regional) {
        this.regional = regional;
    }

    public String getFecha() {
        return SolicitudHistorialVO.SDF.format(fecha);
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public Integer getUnidadesAprobadas() {
		return unidadesAprobadas;
	}

	public void setUnidadesAprobadas(Integer unidadesAprobadas) {
		this.unidadesAprobadas = unidadesAprobadas;
	}

	public Integer getPeriodoAprobado() {
		return periodoAprobado;
	}

	public void setPeriodoAprobado(Integer periodoAprobado) {
		this.periodoAprobado = periodoAprobado;
	}

	public Integer getDiasPeriodo() {
		return diasPeriodo;
	}

	public void setDiasPeriodo(Integer diasPeriodo) {
		this.diasPeriodo = diasPeriodo;
	}

	public Long getNumeroSolicitud() {
    
    	return numeroSolicitud;
    }

	
    public void setNumeroSolicitud(Long numeroSolicitud) {
    
    	this.numeroSolicitud = numeroSolicitud;
    }

	
    public String getDescripcionProducto() {
    
    	return descripcionProducto;
    }

	
    public void setDescripcionProducto(String descripcionProducto) {
    
    	this.descripcionProducto = descripcionProducto;
    }

	
    public String getTipoDeSolicitud() {
    
    	return tipoDeSolicitud;
    }

	
    public void setTipoDeSolicitud(String tipoDeSolicitud) {
    
    	this.tipoDeSolicitud = tipoDeSolicitud;
    }



}
