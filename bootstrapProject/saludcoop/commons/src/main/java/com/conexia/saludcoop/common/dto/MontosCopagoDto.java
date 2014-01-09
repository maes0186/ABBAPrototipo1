package com.conexia.saludcoop.common.dto;

import java.math.BigDecimal;

/**
 * Dto de montos de copago contributivo.
 * 
 * @author Sebastián Matienzo
 */
public class MontosCopagoDto {
	
	/**
	 * Identificador de la entidad.
	 */
	private Long id;

	/**
	 * Año al que aplican los valores.
	 */
	private Integer anio;

	/**
	 * Nivel de IBC/Sisben al que aplican los valores.
	 */
	private Integer nivel;

	/**
	 * Valor de la cuota moderadora.
	 */
	private BigDecimal valorCuotaModeradora;

	/**
	 * Porcentaje del valor del servicio que corresponde como copago.
	 */
	private BigDecimal copagoPorcentajeValorServicio;

	/**
	 * Tope anual de copagos para la misma patología.
	 */
	private BigDecimal copagoTopeAnualMismaPatologia;

	/**
	 * Tope anual de copagos todas las patologías.
	 */
	private BigDecimal copagoTopeAnualCualquierPatologia;

	/**
	 * Asigna el identificador.
	 * 
	 * @param id Identificador.
	 */
	public void setId(final Long id) {
		
		this.id = id;
	}

	/**
	 * Obtiene el identificador.
	 * 
	 * @return Identificador.
	 */
	public Long getId() {
		
		return (this.id);
	}
	
	/**
	 * Obtiene el año.
	 * 
	 * @return Año.
	 */
	public Integer getAnio() {
		
		return (this.anio);
	}

	/**
	 * Asigna el año.
	 * 
	 * @param anio Año.
	 */
	public void setAnio(final Integer anio) {
		
		this.anio = anio;
	}
	
	/**
	 * Obtiene el nivel.
	 * 
	 * @return Nivel.
	 */
	public Integer getNivel() {
		
		return (this.nivel);
	}

	/**
	 * Asigna el nivel.
	 * 
	 * @param nivelIBC Nivel.
	 */
	public void setNivel(final Integer nivel) {
		
		this.nivel = nivel;
	}

	/**
	 * Obtiene el valor de la cuota moderadora.
	 * 
	 * @return Valor de la cuota moderadora.
	 */
	public BigDecimal getValorCuotaModeradora() {
		
		return (this.valorCuotaModeradora);
	}

	/**
	 * Asigna el valor de la cuota moderadora.
	 * 
	 * @param valorCuotaModeradora Valor de la cuota moderadora.
	 */
	public void setValorCuotaModeradora(final BigDecimal valorCuotaModeradora) {
		
		this.valorCuotaModeradora = valorCuotaModeradora;
	}

	/**
	 * Obtiene el porcentaje del valor del servicio para copago.
	 * 
	 * @return Porcentaje del valor del servicio para copago.
	 */
	public BigDecimal getCopagoPorcentajeValorServicio() {
		
		return (this.copagoPorcentajeValorServicio);
	}

	/**
	 * Asigna el porcentaje del valor del servicio para copago.
	 * 
	 * @param copagoPorcentajeValorServicio Porcentaje del valor del servicio para copago.
	 */
	public void setCopagoPorcentajeValorServicio(final BigDecimal copagoPorcentajeValorServicio) {
		
		this.copagoPorcentajeValorServicio = copagoPorcentajeValorServicio;
	}

	/**
	 * Obtiene el tope anual de copagos para la misma patología.
	 * 
	 * @return Tope anual de copagos para la misma patología.
	 */
	public BigDecimal getCopagoTopeAnualMismaPatologia() {
		
		return (this.copagoTopeAnualMismaPatologia);
	}

	/**
	 * Asigna el tope anual de copagos para la misma patología.
	 * 
	 * @param copagoTopeAnualMismaPatologia Tope anual de copagos para la misma patología.
	 */
	public void setCopagoTopeAnualMismaPatologia(final BigDecimal copagoTopeAnualMismaPatologia) {
		
		this.copagoTopeAnualMismaPatologia = copagoTopeAnualMismaPatologia;
	}

	/**
	 * Obtiene el tope anual de copagos todas las patologías.
	 * 
	 * @return Tope anual de copagos todas las patologías.
	 */
	public BigDecimal getCopagoTopeAnualCualquierPatologia() {
		
		return (this.copagoTopeAnualCualquierPatologia);
	}

	/**
	 * Asigna el tope anual de copagos todas las patologías.
	 * 
	 * @param copagoTopeAnualCualquierPatologia Tope anual de copagos todas las patologías.
	 */
	public void setCopagoTopeAnualCualquierPatologia(final BigDecimal copagoTopeAnualCualquierPatologia) {
		
		this.copagoTopeAnualCualquierPatologia = copagoTopeAnualCualquierPatologia;
	}
}
