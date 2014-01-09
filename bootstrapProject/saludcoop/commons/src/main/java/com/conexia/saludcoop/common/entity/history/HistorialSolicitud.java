package com.conexia.saludcoop.common.entity.history;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.conexia.common.persistence.entity.Identifiable;

/**
 * Entidad de historial de solicitudes.
 * 
 * @author Julio Sejtman
 * 
 */
@Entity
@Table(name = "historial_solicitud", schema = "historial")
public class HistorialSolicitud implements Identifiable<Long> {

	/**
	 * id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	private Long id;
	
	/**
	 * Número de la solicitud.
	 */
	@Column(name="numero_solicitud", nullable = false)
	private Long nroSolicitud;
	
	/**
	 * Identificador de la sede IPS.
	 */
	@Column(name="id_sede_ips", nullable = false)
	private Long idSedeIps;
	
	@Column(name="id_regional", nullable = false)
	private Integer idRegional;
	
	@Column(name="descripcion_regional", nullable = false)
	private String descripcionRegional;
	
	@Column(name="id_tipo_documento_afiliado", nullable = false)	
	private Integer idTipoDocumentoAfiliado;
	
	@Column(name="codigo_tipo_documento_afiliado", nullable = false)
	private String codigoTipoDocumentoAfiliado;
	
	@Column(name="descripcion_tipo_documento_afiliado", nullable = false)
	private String descripcionTipoDocumentoAfiliado;
	
	@Column(name="numero_documento_afiliado", nullable = false)
	private String numeroDocumentoAfiliado;
	
	@Column(name="fecha", nullable = false)
	private Date fecha;
	
	@Column(name="descripcion", nullable = false)
	private String descripcion;
	
	@Column(name="id_estado", nullable = false)
	private Integer idEstado;
	
	@Column(name="descripcion_estado", nullable = false)
	private String descripcionEstado;
	
	@Column(name="tipo_solicitud", nullable = false)
	private String tipoSolicitud;
	
	@Column(name="codigo_producto", nullable = false)
	private String codigoProducto;
	
	@Column(name="unidades_aprobadas", nullable = false)
	private Integer unidadesAprobadas;
	
	@Column(name="periodo_aprobado", nullable = false)
	private Integer periodoAprobado;
	
	@Column(name="dias_por_periodo", nullable = false)
	private Integer diasPorPeriodo;

	
    public Long getId() {
    
    	return id;
    }

	
    public void setId(Long id) {
    
    	this.id = id;
    }

	
    public Long getNroSolicitud() {
    
    	return nroSolicitud;
    }

	
    public void setNroSolicitud(Long nroSolicitud) {
    
    	this.nroSolicitud = nroSolicitud;
    }

	
    public Long getIdSedeIps() {
    
    	return idSedeIps;
    }

	
    public void setIdSedeIps(Long idSedeIps) {
    
    	this.idSedeIps = idSedeIps;
    }

	

	public String getDescripcionRegional() {
    
    	return descripcionRegional;
    }

	
    public void setDescripcionRegional(String descripcionRegional) {
    
    	this.descripcionRegional = descripcionRegional;
    }

	
    public Integer getIdTipoDocumentoAfiliado() {
    
    	return idTipoDocumentoAfiliado;
    }

	
    public void setIdTipoDocumentoAfiliado(Integer idTipoDocumentoAfiliado) {
    
    	this.idTipoDocumentoAfiliado = idTipoDocumentoAfiliado;
    }

	
    public String getCodigoTipoDocumentoAfiliado() {
    
    	return codigoTipoDocumentoAfiliado;
    }

	
    public void setCodigoTipoDocumentoAfiliado(String codigoTipoDocumentoAfiliado) {
    
    	this.codigoTipoDocumentoAfiliado = codigoTipoDocumentoAfiliado;
    }

	
    public String getDescripcionTipoDocumentoAfiliado() {
    
    	return descripcionTipoDocumentoAfiliado;
    }

	
    public void setDescripcionTipoDocumentoAfiliado(String descripcionTipoDocumentoAfiliado) {
    
    	this.descripcionTipoDocumentoAfiliado = descripcionTipoDocumentoAfiliado;
    }

	
    public String getNumeroDocumentoAfiliado() {
    
    	return numeroDocumentoAfiliado;
    }

	
    public void setNumeroDocumentoAfiliado(String numeroDocumentoAfiliado) {
    
    	this.numeroDocumentoAfiliado = numeroDocumentoAfiliado;
    }

	
    public Date getFecha() {
    
    	return fecha;
    }

	
    public void setFecha(Date fecha) {
    
    	this.fecha = fecha;
    }

	
    public String getDescripcion() {
    
    	return descripcion;
    }

	
    public void setDescripcion(String descripcion) {
    
    	this.descripcion = descripcion;
    }



	public Integer getIdRegional() {
		return idRegional;
	}


	public void setIdRegional(Integer idRegional) {
		this.idRegional = idRegional;
	}


	public Integer getIdEstado() {
		return idEstado;
	}


	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}


	public String getDescripcionEstado() {
		return descripcionEstado;
	}


	public void setDescripcionEstado(String descripcionEstado) {
		this.descripcionEstado = descripcionEstado;
	}


	public String getTipoSolicitud() {
    
    	return tipoSolicitud;
    }

	
    public void setTipoSolicitud(String tipoSolicitud) {
    
    	this.tipoSolicitud = tipoSolicitud;
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

	
    public Integer getDiasPorPeriodo() {
    
    	return diasPorPeriodo;
    }

	
    public void setDiasPorPeriodo(Integer diasPorPeriodo) {
    
    	this.diasPorPeriodo = diasPorPeriodo;
    }
	
}
