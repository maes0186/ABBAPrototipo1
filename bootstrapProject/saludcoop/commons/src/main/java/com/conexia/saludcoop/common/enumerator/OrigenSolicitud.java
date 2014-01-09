package com.conexia.saludcoop.common.enumerator;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *  Origen solicitud
 * 
 * @author Fabián González
 */

public enum OrigenSolicitud {




	COMITES (1L, "COMITES"),

	POS_QUIMIOTERAPIA(2L, "POS QUIMIOTERAPIA"),
	
	POS_R001 (3L, "POS R001"),
	
	TUTELA (4L, "TUTELA"),
	
	POS (5L, "POS"),
	
	POS_ENFERMEDAD_RENAL_CRONICA (6L, "POS ENFERMEDAD RENAL CRONICA"),
	
	POS_HEMOFILIA (7L, "POS HEMOFILIA"),
	
	CTC (8L, "CTC"),
	
	PROCEDIMIENTOS (9L, "PROCEDIMIENTOS"),
	
	PROCEDIMIENTOS_CTC(10L, "PROCEDIMIENTOS CTC"),
	
	INSUMOS_ALTO_COSTO(11L, "INSUMOS ALTO COSTO"),
	
	MEDICAMENTO_O_INSUMO_NOPOS(12L, "MEDICAMENTO O INSUMO NOPOS - ORIGEN NO ESPECIFICADO"),
	
	FARMACOVIGILANCIA(13L, "FARMACOVIGILANCIA"),
	
	PERIODOS_MINIMOS_DE_COTIZACION(14L, "PERIODOS MINIMOS DE COTIZACION"),
	
	POS_OTROS_PROGRAMAS(15L, "POS OTROS PROGRAMAS "),
	
	MEDICAMENTOS_CONDICIONADOS(16L, "MEDICAMENTOS CONDICIONADOS");


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
	 * @param id Identificador del origen de solicitud.
	 * @param code Código del origen de solicitud.

	 */
	
	
	private OrigenSolicitud(final Long id, final String code) {

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
	public static OrigenSolicitud fromId(final Long id) {

		for (OrigenSolicitud origenSolicitud : OrigenSolicitud.values()) {
			if (origenSolicitud.getId().equals(id)) {
				return (origenSolicitud);
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
	public static OrigenSolicitud fromCode(final String code) {

		for (OrigenSolicitud origenSolicitud : OrigenSolicitud.values()) {
			if (origenSolicitud.getCode().equals(code)) {
				return (origenSolicitud);
			}
		}

		return (null);
	}
}
