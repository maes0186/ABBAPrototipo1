package com.conexia.saludcoop.common.enumerator;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Tipo de frecuencia para la prescripción del medicamento
 * 
 * @author Pablo Rodas
 */
public enum TipoFrecuencia {

	/**
	 * Horas.
	 */
	HORAS (1, "H"),

	/**
	 * Días.
	 */
	DIAS (2, "D");

	/**
	 * Identificador.
	 */
	private Integer id;

	/**
	 * Código.
	 */
	private String code;

	/**
	 * Constructor del enumerador.
	 * 
	 * @param id Identificador del tipo de frecuencia.
	 * @param code Código del tipo de frecuencia.

	 */
	
	
	private TipoFrecuencia(final Integer id, final String code) {

		this.id = id;
		this.code = code;
	}

	/**
	 * Devuelve el valor del identificador.
	 * 
	 * @return El valor del identificador.
	 */
	public final Integer getId() {

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
	public static TipoFrecuencia fromId(final Integer id) {

		for (TipoFrecuencia tipoIdentificacion : TipoFrecuencia.values()) {
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
	public static TipoFrecuencia fromCode(final String code) {

		for (TipoFrecuencia tipoIdentificacion : TipoFrecuencia.values()) {
			if (tipoIdentificacion.getCode().equals(code)) {
				return (tipoIdentificacion);
			}
		}

		return (null);
	}
}
