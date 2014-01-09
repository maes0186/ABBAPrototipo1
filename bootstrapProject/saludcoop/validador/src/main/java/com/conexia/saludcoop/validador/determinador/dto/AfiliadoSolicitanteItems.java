package com.conexia.saludcoop.validador.determinador.dto;

import com.conexia.saludcoop.common.enumerator.RegimenAfiliacion;
import com.conexia.saludcoop.common.enumerator.TipoAfiliado;

/**
 * Afiliado al cual verificar el monto a abonar y la factibilidad de exención.
 * 
 * @author Sebastián Matienzo
 */
public class AfiliadoSolicitanteItems {

	/**
	 * Identificador del afiliado.
	 */
	private Long id;

	/**
	 * Régimen de afiliación determinado.
	 */
	private RegimenAfiliacion regimenAfiliacion;
	
	/**
	 * Nivel de afiliación (IBC/SISBEN) determinado.
	 */
	private int nivelAfiliacion;
	
	/**
	 * Tipo de afiliado determinado.
	 */
	private TipoAfiliado tipoAfiliado;

	/**
	 * Constructor público.
	 * 
	 * @param afiliadoId Identificador del afiliado.
	 * @param regimenAfiliacion Régimen de afiliación.
	 * @param nivelAfiliacion Nivel de afiliación.
	 * @param tipoAfiliado Tipo de afiliado.
	 */
	public AfiliadoSolicitanteItems(final Long afiliadoId,
			final RegimenAfiliacion regimenAfiliacion,
			final int nivelAfiliacion, final TipoAfiliado tipoAfiliado) {

		this.id = afiliadoId;
		this.regimenAfiliacion = regimenAfiliacion;
		this.nivelAfiliacion = nivelAfiliacion;
		this.tipoAfiliado = tipoAfiliado;
	}

	/**
	 * Obtiene el identificador del afiliado.
	 * 
	 * @return Identificador del afiliado.
	 */
	public Long getId() {
		
		return (this.id);
	}

	/**
	 * Asigna el identificador del afiliado.
	 * 
	 * @param id Identificador del afiliado.
	 */
	public void setId(final Long id) {
		
		this.id = id;
	}

	/**
	 * Obtiene el régimen de afiliación.
	 * 
	 * @return Régimen de afiliación.
	 */
	public RegimenAfiliacion getRegimenAfiliacion() {
		
		return (this.regimenAfiliacion);
	}

	/**
	 * Asigna el régimen de afiliación.
	 * 
	 * @param regimenAfiliacion Régimen de afiliación.
	 */
	public void setRegimenAfiliacion(final RegimenAfiliacion regimenAfiliacion) {
		
		this.regimenAfiliacion = regimenAfiliacion;
	}

	/**
	 * Obtiene el nivel de afiliación.
	 * 
	 * @return Nivel de afiliación.
	 */
	public int getNivelAfiliacion() {
		
		return (this.nivelAfiliacion);
	}

	/**
	 * Asigna el nivel de afiliación.
	 * 
	 * @param regimenAfiliacion Nivel de afiliación.
	 */
	public void setNivelAfiliacion(final int nivelAfiliacion) {
		
		this.nivelAfiliacion = nivelAfiliacion;
	}

	/**
	 * Obtiene el tipo de afiliado.
	 * 
	 * @return Tipo de afiliado.
	 */
	public TipoAfiliado getTipoAfiliado() {
		
		return (this.tipoAfiliado);
	}

	/**
	 * Asigna el tipo de afiliado.
	 * 
	 * @param tipoAfiliado Tipo de afiliado.
	 */
	public void setTipoAfiliado(final TipoAfiliado tipoAfiliado) {
		
		this.tipoAfiliado = tipoAfiliado;
	}
}
