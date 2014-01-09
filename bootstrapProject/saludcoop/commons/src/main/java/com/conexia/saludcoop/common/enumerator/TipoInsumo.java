package com.conexia.saludcoop.common.enumerator;

/**
 * Tipo de insumo
 */
public enum TipoInsumo {

	/**
	 * GLUCOMETRIA.
	 */
	GLUCOMETRIA (1L, "1"),

	/**
	 * OSTOMIA.
	 */
	OSTOMIA (2L, "2");
	
	/**
	 * Identificador.
	 */
	private Long id;

	/**
	 * Código.
	 */
	private String code;

	/**
	 * Constructor del enumerador.
	 * 
	 * @param id Identificador del tipo de insumo.
	 * @param code Código del tipo de insumo.
	 */
	private TipoInsumo(final Long id, final String code) {

		this.id = id;
		this.code = code;
	}

	/**
	 * Devuelve el valor del insumo.
	 * 
	 * @return El valor del insumo.
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
	 * Obtiene el elemento correspondiente al identificador indicado.
	 * 
	 * @param id Identificador cuyo elemento obtener.
	 * @return Elemento obtenido, o null si ninguno coincide con dicho identificador.
	 */
	public static TipoInsumo fromId(final Long id) {

		for (TipoInsumo tipoInsumo : TipoInsumo.values()) {
			if (tipoInsumo.getId().equals(id)) {
				return (tipoInsumo);
			}
		}

		return (null);
	}

	/**
	 * Obtiene el elemento correspondiente al código indicado.
	 * 
	 * @param code Código cuyo elemento obtener.
	 * @return Elemento obtenido, o null si ninguno coincide con dicho código.
	 */
	public static TipoInsumo fromCode(final String code) {

		for (TipoInsumo tipoInsumo : TipoInsumo.values()) {
			if (tipoInsumo.getCode().equals(code)) {
				return (tipoInsumo);
			}
		}

		return (null);
	}
}
