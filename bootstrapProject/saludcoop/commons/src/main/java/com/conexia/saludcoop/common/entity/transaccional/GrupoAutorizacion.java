package com.conexia.saludcoop.common.entity.transaccional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.conexia.saludcoop.common.enumerator.TipoPagoRequerido;
@Entity
@Table(name="grupo_Autorizacion", schema="transaccional")
@org.hibernate.annotations.Entity(dynamicInsert=true)
public class GrupoAutorizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 10)
	private Long id;
    
    @Column(name="cuota_moderadora_estimada", nullable=false)
	private BigDecimal cuotaModeradoraEstimada = new BigDecimal(0);
    
    @Column(name="cuota_moderadora_pagada", nullable=false)
	private Boolean cuotaModeradoraPagada = false;
    
    @Column(name="tipo_pago_enum", nullable=false)
	private Long tipoPago;
    
    @Column(name="autorizado_automaticamente",nullable=false)
	private Boolean autorizadoAutomaticamente = false;
    
    @OneToMany(mappedBy="grupoAutorizacion",cascade=CascadeType.ALL)
	private Set<Autorizacion> autorizaciones = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name="solicitud_id", nullable=false)
	private Solicitud solicitud;
	
	public BigDecimal getCuotaModeradoraEstimada() {
		return cuotaModeradoraEstimada;
	}
	public void setCuotaModeradoraEstimada(BigDecimal cuotaModeradoraEstimada) {
		this.cuotaModeradoraEstimada = cuotaModeradoraEstimada;
	}
	public boolean isCuotaModeradoraPagada() {
		return cuotaModeradoraPagada;
	}
	public void setCuotaModeradoraPagada(boolean cuotaModeradoraPagada) {
		this.cuotaModeradoraPagada = cuotaModeradoraPagada;
	}
	public TipoPagoRequerido getTipoPago() {
		return TipoPagoRequerido.fromId(tipoPago);
	}
	public void setTipoPago(TipoPagoRequerido tipoPago) {
		this.tipoPago = tipoPago.getId();
	}
	public boolean isAutorizadoAutomaticamente() {
		return autorizadoAutomaticamente;
	}
	public void setAutorizadoAutomaticamente(boolean autorizadoAutomaticamente) {
		this.autorizadoAutomaticamente = autorizadoAutomaticamente;
	}
	
	
	public void addAutorizacion(Autorizacion autorizacion){
		this.autorizaciones.add(autorizacion);
		autorizacion.setGrupoAutorizacion(this);
	}
	
	public Set<Autorizacion> getAutorizaciones() {
		return Collections.unmodifiableSet(autorizaciones);
	}
	public Solicitud getSolicitud() {
		return solicitud;
	}
	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}
	public Long getId() {
		return id;
	}
	

	
		

}
