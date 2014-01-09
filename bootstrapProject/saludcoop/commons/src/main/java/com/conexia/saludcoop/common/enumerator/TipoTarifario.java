package com.conexia.saludcoop.common.enumerator;

/**
 * Tipo de tarifario.
 * 
 * @author Sebastián Matienzo
 */
public enum TipoTarifario {

	/**
	 * SOAT.
	 */
	SOAT (1L, "1"),

	/**
	 * ISS 2000.
	 */
	ISS_2000 (2L, "2"),

	/**
	 * ISS 2001.
	 */
	ISS_2001 (3L, "3"),

	/**
	 * ISS 2004.
	 */
	ISS_2004 (4L, "4"),

	/**
	 * No normativo.
	 */
	NO_NORMATIVO (5L, "5");
	
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
	 * @param id Identificador del tipo de identificador.
	 * @param code Código del tipo de identificación.
	 */
	private TipoTarifario(final Long id, final String code) {

		this.id = id;
		this.code = code;
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
	 * Obtiene el elemento correspondiente al identificador indicado.
	 * 
	 * @param id Identificador cuyo elemento obtener.
	 * @return Elemento obtenido, o null si ninguno coincide con dicho identificador.
	 */
	public static TipoTarifario fromId(final Long id) {

		for (TipoTarifario tipoIdentificacion : TipoTarifario.values()) {
			if (tipoIdentificacion.getId().equals(id)) {
				return (tipoIdentificacion);
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
	public static TipoTarifario fromCode(final String code) {

		for (TipoTarifario tipoIdentificacion : TipoTarifario.values()) {
			if (tipoIdentificacion.getCode().equals(code)) {
				return (tipoIdentificacion);
			}
		}

		return (null);
	}
}
