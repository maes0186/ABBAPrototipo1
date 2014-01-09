package com.conexia.saludcoop.common.entity.maestro;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Index;

import ar.com.conexia.common.persistence.entity.Identifiable;

//@Entity
//@Table(name = "transaccion", schema = "maestros")
public class Transaccion extends BaseMaestro implements Identifiable<Long> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "tipo_transaccion", nullable = false)
	private String tipoTransaccion;

	@OneToOne
	@JoinColumn(name = "id", nullable = true)
	private Afiliado afiliado;
	
	@OneToOne
	private Eps eps;

	@Column(name="origen", length=20)
	private String origen;

	@Index(name="ix_transaccion_fecha_creacion")
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Column(name="codigo_mensaje_respuesta", length=5)
	private String codigoMensajeRespuesta;

	@Column(name="codigo_respuesta", length=5)
	private String codigoRespuesta;

	public String getTipoTransaccion() {
		return tipoTransaccion;
	}

	public void setTipoTransaccion(String tipoTransaccion){
		this.tipoTransaccion = tipoTransaccion;
	}
	
	public String getCodigoMensajeRespuesta() {
		return codigoMensajeRespuesta;
	}

	public void setCodigoMensajeRespuesta(String codigoMensajeRespuesta) {
		this.codigoMensajeRespuesta = codigoMensajeRespuesta;
	}

	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}

	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Afiliado getAfiliado() {
		return afiliado;
	}

	public void setAfiliado(Afiliado afiliado) {
		this.afiliado = afiliado;
	}

	public Eps getEps() {
		return eps;
	}

	public void setEps(Eps eps) {
		this.eps = eps;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	

}