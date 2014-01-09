package com.conexia.saludcoop.common.enumerator;

/**
 * Tipo de afiliado.
 * 
 * @author Sebastián Matienzo
 */
public enum TipoAfiliado {

	/**
	 * Cotizante.
	 */
	COTIZANTE (1L, "1", true, false, "COTIZANTE"),

	/**
	 * Beneficiario.
	 */
	BENEFICIARIO (2L, "2", false, true, "BENEFICIARIO"),

	/**
	 * Beneficiario adicional.
	 */
	BENEFICIARIO_ADICIONAL (3L, "3", false, true, "BENEFICIARIO ADICIONAL");
	
	/**
	 * Identificador.
	 */
	private Long id;

	/**
	 * Código.
	 */
	private String code;

	/**
	 * Indica si es un tipo de cotizante.
	 */
	private boolean tipoCotizante;

	/**
	 * Indica si es un tipo de beneficiario.
	 */
	private boolean tipoBeneficiario;

	/**
     * Descripción
     */
    private String descripcion;
    
	/**
	 * Constructor del enumerador.
	 * 
	 * @param id Identificador del tipo de identificador.
	 * @param code Código del tipo de identificación.
	 * @param tipoCotizante Indica si equivale a un cotizante.
	 * @param tipoBeneficiario Indica si equivale a un beneficiario.
	 */
	private TipoAfiliado(final Long id, final String code, final boolean tipoCotizante,
			final boolean tipoBeneficiario, String descripcion) {

		this.id = id;
		this.code = code;
		this.tipoCotizante = tipoCotizante;
		this.tipoBeneficiario = tipoBeneficiario;
		this.descripcion = descripcion;
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
     * Devuelve la descripcion del tipo de Afiliado
     * 
     * @return La descripcion del tipo de Afiliado
     */
    public final String getDescripcion() {

        return (this.descripcion);
    }
	
	/**
	 * Indica si es un tipo de cotizante.
	 * 
	 * @return True si lo es; False si no.
	 */
	public boolean isTipoCotizante() {
		
		return (this.tipoCotizante);
	}

	/**
	 * Indica si es un tipo de beneficiario.
	 * 
	 * @return True si la es; False si no.
	 */
	public boolean isTipoBeneficiario() {
		
		return (this.tipoBeneficiario);
	}

	/**
	 * Obtiene el elemento correspondiente al identificador indicado.
	 * 
	 * @param id Identificador cuyo elemento obtener.
	 * @return Elemento obtenido, o null si ninguno coincide con dicho identificador.
	 */
	public static TipoAfiliado fromId(final Long id) {

		for (TipoAfiliado tipoIdentificacion : TipoAfiliado.values()) {
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
	public static TipoAfiliado fromCode(final String code) {

		for (TipoAfiliado tipoIdentificacion : TipoAfiliado.values()) {
			if (tipoIdentificacion.getCode().equals(code)) {
				return (tipoIdentificacion);
			}
		}

		return (null);
	}
}
