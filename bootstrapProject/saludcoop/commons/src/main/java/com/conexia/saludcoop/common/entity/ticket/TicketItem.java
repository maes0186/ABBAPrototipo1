package com.conexia.saludcoop.common.entity.ticket;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ticket_item", schema="ticket")
public class TicketItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@OneToOne
    @JoinColumn(name = "ticket_cabecera_id", nullable = false)
    private TicketCabecera ticketCabecera;
	
	@Column(nullable=false)
	private String codigo;

	@Column(nullable=false)
	private String descripcion;

	@Column(nullable=false)
	private Integer cantidad;

	@Column(nullable=false)
	private String finalidad;

	@Column(nullable=true, name="causa_externa")
	private String causaExterna;
	
	@Column(nullable=true)
	private String lateralidad;

	@Column(nullable=false)
	private String observaciones;
	
	@Column(nullable=true, name="tipo_catastrofico")
	private String tipoCatastrofico;

	public TicketCabecera getTicketCabecera() {
		return ticketCabecera;
	}

	public void setTicketCabecera(TicketCabecera ticketCabecera) {
		this.ticketCabecera = ticketCabecera;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public String getFinalidad() {
		return finalidad;
	}

	public void setFinalidad(String finalidad) {
		this.finalidad = finalidad;
	}

	public String getCausaExterna() {
		return causaExterna;
	}

	public void setCausaExterna(String causaExterna) {
		this.causaExterna = causaExterna;
	}

	public String getLateralidad() {
		return lateralidad;
	}

	public void setLateralidad(String lateralidad) {
		this.lateralidad = lateralidad;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getTipoCatastrofico() {
		return tipoCatastrofico;
	}

	public void setTipoCatastrofico(String tipoCatastrofico) {
		this.tipoCatastrofico = tipoCatastrofico;
	}

	public Integer getId() {
		return id;
	}
	
	
	
	
	
	
}
