package com.conexia.saludcoop.common.dto.transaccional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import com.conexia.saludcoop.common.enumerator.TipoPagoRequerido;

public class GrupoAutorizacionDto {

	private BigDecimal cuotaModeradoraEstimada;
	private boolean cuotaModeradoraPagada;
	private TipoPagoRequerido tipoPago;
	private boolean autorizadoAutomaticamente;
	private List<AutorizacionDto> autorizaciones = new Vector<>();
	
	
	public void addAutorizacion(AutorizacionDto autorizacion){
		this.autorizaciones.add(autorizacion);
	}
	
	public List<AutorizacionDto> getAutorizaciones() {
		return Collections.unmodifiableList(autorizaciones);
	}
	
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
		return tipoPago;
	}
	public void setTipoPago(TipoPagoRequerido tipoPago) {
		this.tipoPago = tipoPago;
	}
	public boolean isAutorizadoAutomaticamente() {
		return autorizadoAutomaticamente;
	}
	public void setAutorizadoAutomaticamente(boolean autorizadoAutomaticamente) {
		this.autorizadoAutomaticamente = autorizadoAutomaticamente;
	}

	public void setAutorizaciones(List<AutorizacionDto> autorizaciones) {
		this.autorizaciones = autorizaciones;
	}
	
	
	
}
