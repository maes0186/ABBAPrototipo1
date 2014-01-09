package com.conexia.saludcoop.common.dto;

import java.math.BigDecimal;

import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.entity.auxiliar.TotalizacionCopagoAfiliadoDiagnostico;

/**
 * Dto de totalización de los copagos de los afiliados por diagnóstico.
 * 
 * @author Sebastián Matienzo
 */
@Mappeable(mappedTo = TotalizacionCopagoAfiliadoDiagnostico.class)
public class TotalizacionCopagoAfiliadoDiagnosticoDto {

	/**
	 * Identificador de la entidad.
	 */
	private Long id;

	/**
	 * Año al que aplican los valores.
	 */
	private Integer anio;

	/**
	 * ID del afiliado al que corresponde la totalización.
	 */
	private Long afiliadoId;

	/**
	 * ID del diagnóstico por el cual se agrupa la totalización.
	 */
	private Long diagnosticoId;

	/**
	 * Total de copagos realizados.
	 */
	private BigDecimal totalCopagos;

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
	 * Obtiene el ID del afiliado al que corresponde la totalización.
	 * 
	 * @return ID del afiliado al que corresponde la totalización.
	 */
	public Long getAfiliadoId() {
		
		return (this.afiliadoId);
	}

	/**
	 * Asigna el ID del afiliado al que corresponde la totalización.
	 * 
	 * @param afiliadoId ID del afiliado al que corresponde la totalización.
	 */
	public void setAfiliadoId(final Long afiliadoId) {
		
		this.afiliadoId = afiliadoId;
	}
	
	/**
	 * Obtiene el ID del diagnóstico por el cual se agrupa la totalización.
	 * 
	 * @return ID del diagnóstico por el cual se agrupa la totalización.
	 */
	public Long getDiagnosticoId() {
		
		return (this.diagnosticoId);
	}

	/**
	 * Asigna el ID del diagnóstico por el cual se agrupa la totalización.
	 * 
	 * @param diagnosticoId ID del diagnóstico por el cual se agrupa la totalización.
	 */
	public void setDiagnosticoId(final Long diagnosticoId) {
		
		this.diagnosticoId = diagnosticoId;
	}

	/**
	 * Obtiene el total de copagos realizados.
	 * 
	 * @return Total de copagos realizados.
	 */
	public BigDecimal getTotalCopagos() {
		
		return (this.totalCopagos);
	}

	/**
	 * Asigna el total de copagos realizados.
	 * 
	 * @param valorCuotaModeradora Total de copagos realizados.
	 */
	public void setTotalCopagos(final BigDecimal totalCopagos) {
		
		this.totalCopagos = totalCopagos;
	}
}
