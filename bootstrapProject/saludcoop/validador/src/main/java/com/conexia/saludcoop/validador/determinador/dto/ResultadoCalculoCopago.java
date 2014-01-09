package com.conexia.saludcoop.validador.determinador.dto;

import java.math.BigDecimal;

/**
 * Resultado de cálculo de copago de un ítem.
 * 
 * @author Sebastián Matienzo
 */
public class ResultadoCalculoCopago {

	/**
	 * Ítem.
	 */
	private ItemSolicitado item;

	/**
	 * Monto de copago calculado.
	 */
	private BigDecimal montoCopago;

	/**
	 * Constructor público.
	 * 
	 * @param item Ítem.
	 * @param montoCopago Monto de copago.
	 */
	public ResultadoCalculoCopago(final ItemSolicitado item, final BigDecimal montoCopago) {

		this.item = item;
		this.montoCopago = montoCopago;
	}

	/**
	 * Obtiene el ítem.
	 * 
	 * @return Ítem.
	 */
	public ItemSolicitado getItem() {
		
		return (this.item);
	}

	/**
	 * Asigna el ítem.
	 * 
	 * @param item Ítem.
	 */
	public void setItem(final ItemSolicitado item) {
		
		this.item = item;
	}

	/**
	 * Obtiene el monto de copago.
	 * 
	 * @return Monto de copago.
	 */
	public BigDecimal getMontoCopago() {
		
		return (this.montoCopago);
	}

	/**
	 * Asigna el monto de copago.
	 * 
	 * @param montoCopago Monto de copago.
	 */
	public void setMontoCopago(final BigDecimal montoCopago) {
		
		this.montoCopago = montoCopago;
	}
}
