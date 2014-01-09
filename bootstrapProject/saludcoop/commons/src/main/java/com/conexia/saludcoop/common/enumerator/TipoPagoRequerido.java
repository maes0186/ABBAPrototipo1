package com.conexia.saludcoop.common.enumerator;

/**
 * Tipo de pago requerido (sea un medicamento, un procedimiento, etc).
 * 
 * @author Sebastián Matienzo
 */
public enum TipoPagoRequerido {

	/**
	 * Copago.
	 */
	COPAGO (1L, "1", true, false, false),

	/**
	 * Cuota moderadora.
	 */
	CUOTA_MODERADORA (2L, "2", false, true, false),

	/**
	 * Copago más cuota moderadora.
	 */
	COPAGO_MAS_CUOTA_MODERADORA (3L, "3", true, true, false),

	/**
	 * Pago compartido.
	 */
	PAGO_COMPARTIDO (4L, "4", false, false, true),

	/**
	 * No aplica.
	 */
	NO_APLICA (5L, "5", false, false, false);
	
	/**
	 * Identificador.
	 */
	private Long id;

	/**
	 * Código.
	 */
	private String code;

	/**
	 * Indica si el copago es aplicable.
	 */
	private boolean copagoAplicable;

	/**
	 * Indica si la cuota moderadora es aplicable.
	 */
	private boolean cuotaModeradoraAplicable;

	/**
	 * Indica si el pago compartido es aplicable.
	 */
	private boolean pagoCompartidoAplicable;

	/**
	 * Constructor del enumerador.
	 * 
	 * @param id Identificador del tipo de identificador.
	 * @param code Código del tipo de identificación.

	 */
	private TipoPagoRequerido(final Long id, final String code, final boolean copagoAplicable,
			final boolean cuotaModeradoraAplicable, final boolean pagoCompartidoAplicable) {

		this.id = id;
		this.code = code;
		this.copagoAplicable = copagoAplicable;
		this.cuotaModeradoraAplicable = cuotaModeradoraAplicable;
		this.pagoCompartidoAplicable = pagoCompartidoAplicable;
	}

	/**
	 * Devuelve el valor del identificador.
	 * 
	 * @return El valor del identificador.
	 */
	public final Long getId() {

		return (this.id);
	}

	/**
	 * Devuelve el valor del código.
	 * 
	 * @return El valor del código.
	 */
	public final String getCode() {

		return (this.code);
	}

	/**
	 * Indica si el copago es aplicable.
	 * 
	 * @return True si lo es; False si no.
	 */
	public boolean isCopagoAplicable() {
		
		return (this.copagoAplicable);
	}

	/**
	 * Indica si la cuota moderadora es aplicable.
	 * 
	 * @return True si la es; False si no.
	 */
	public boolean isCuotaModeradoraAplicable() {
		
		return (this.cuotaModeradoraAplicable);
	}

	/**
	 * Indica si el pago compartido es aplicable.
	 * 
	 * @return True si lo es; False si no.
	 */
	public boolean isPagoCompartidoAplicable() {
		
		return (this.pagoCompartidoAplicable);
	}

	/**
	 * Obtiene el elemento correspondiente al identificador indicado.
	 * 
	 * @param id Identificador cuyo elemento obtener.
	 * @return Elemento obtenido, o null si ninguno coincide con dicho identificador.
	 */
	public static TipoPagoRequerido fromId(final Long id) {

		for (TipoPagoRequerido tipoIdentificacion : TipoPagoRequerido.values()) {
			if (tipoIdentificacion.getId().equals(id)) {
				return (tipoIdentificacion);
			}
		}

		return (null);
	}

	/**
	 * Obtiene el elemento correspondiente al code indicado.
	 * 
	 * @param code Code cuyo elemento obtener.
	 * @return Elemento obtenido, o null si ninguno coincide con dicho código.
	 */
	public static TipoPagoRequerido fromCode(final String code) {

		for (TipoPagoRequerido tipoIdentificacion : TipoPagoRequerido.values()) {
			if (tipoIdentificacion.getCode().equals(code)) {
				return (tipoIdentificacion);
			}
		}

		return (null);
	}
}
