package com.conexia.saludcoop.validador.determinador.dto;

/**
 * Resultado de verificación de exención de pago de un ítem.
 * 
 * @author Sebastián Matienzo
 */
public class ResultadoVerificacionExencion {

	/**
	 * Ítem.
	 */
	private ItemSolicitado item;

	/**
	 * Indica si está exento de pago.
	 */
	private boolean exento;
	
	/**
	 * Indica si la exención corresponde por ser la primera consulta del año.
	 */
	private boolean exentoPorPrimeraConsultaAnio;

	/**
	 * Constructor público.
	 * 
	 * @param item Ítem.
	 * @param exento Indica si está exento.
	 * @param exentoPorPrimeraConsultaAnio Indica si la exención corresponde por ser la primera consulta del año.
	 */
	public ResultadoVerificacionExencion(final ItemSolicitado item, final boolean exento, 
			final boolean exentoPorPrimeraConsultaAnio) {

		this.item = item;
		this.exento = exento;
		this.exentoPorPrimeraConsultaAnio = exentoPorPrimeraConsultaAnio;
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
	 * Indica si está exento.
	 * 
	 * @return True si está exento; caso contrario, False.
	 */
	public boolean isExento() {
		
		return (this.exento);
	}

	/**
	 * Asigna el indicador de si está exento.
	 * 
	 * @param exento Indicador de si está exento.
	 */
	public void setExento(final boolean exento) {
		
		this.exento = exento;
	}

	/**
	 * Indica si la exención corresponde por ser la primera consulta del año.
	 * 
	 * @return True si corresponde; caso contrario, False.
	 */
	public boolean isExentoPorPrimeraConsultaAnio() {
		
		return (this.exentoPorPrimeraConsultaAnio);
	}

	/**
	 * Asigna el indicador de si la exención corresponde por ser la primera consulta del año.
	 * 
	 * @param exento Indicador de si la exención corresponde por ser la primera consulta del año.
	 */
	public void setExentoPorPrimeraConsultaAnio(final boolean exentoPorPrimeraConsultaAnio) {
		
		this.exentoPorPrimeraConsultaAnio = exentoPorPrimeraConsultaAnio;
	}
}
