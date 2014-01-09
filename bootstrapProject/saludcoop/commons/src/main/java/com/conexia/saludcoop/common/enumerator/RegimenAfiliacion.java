package com.conexia.saludcoop.common.enumerator;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Tipo de régimen de afiliación.
 * 
 * @author Sebastián Matienzo
 */
public enum RegimenAfiliacion {

	/**
	 * Contributivo.
	 */
	CONTRIBUTIVO (1L, "C"),

	/**
	 * Subsidiado.
	 */
	SUBSIDIADO (2L, "S");

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
	
	
	private RegimenAfiliacion(final Long id, final String code) {

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
	@JsonIgnore
	public static RegimenAfiliacion fromId(final Long id) {

		for (RegimenAfiliacion tipoIdentificacion : RegimenAfiliacion.values()) {
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
	@JsonIgnore
	public static RegimenAfiliacion fromCode(final String code) {

		for (RegimenAfiliacion tipoIdentificacion : RegimenAfiliacion.values()) {
			if (tipoIdentificacion.getCode().equals(code)) {
				return (tipoIdentificacion);
			}
		}

		return (null);
	}
}
