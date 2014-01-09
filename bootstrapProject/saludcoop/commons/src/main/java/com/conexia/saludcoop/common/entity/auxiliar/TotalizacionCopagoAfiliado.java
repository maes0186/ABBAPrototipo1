package com.conexia.saludcoop.common.entity.auxiliar;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.conexia.common.persistence.entity.Identifiable;
import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.dto.TotalizacionCopagoAfiliadoDto;

/**
 * Totalización de los copagos de los afiliados (sin discriminación alguna).
 * 
 * @author Sebastián Matienzo
 */
@Entity
@Table(name = "totalizacion_copago_afiliado", schema = "auxiliar")
@Mappeable(mappedTo = TotalizacionCopagoAfiliadoDto.class)
public class TotalizacionCopagoAfiliado implements Identifiable<Long> {

	/**
	 * Identificador de la entidad.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	/**
	 * Año al que aplican los valores.
	 */
	@Column(name = "anio", nullable = false)
	private Integer anio;

	/**
	 * ID del afiliado al que corresponde la totalización.
	 */
	@Column(name = "afiliado_id", nullable = false)
	private Long afiliadoId;

	/**
	 * Total de copagos realizados.
	 */
	@Column(name = "total_copagos", nullable = false)
	private BigDecimal totalCopagos;

	@Override
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

	/**
	 * Obtiene el DTO que representa a esta entidad.
	 * 
	 * @return DTO que representa a esta entidad.
	 */
	public TotalizacionCopagoAfiliadoDto toDto() {
		
		TotalizacionCopagoAfiliadoDto dto = new TotalizacionCopagoAfiliadoDto();
		dto.setId(this.id);
		dto.setAnio(this.getAnio());
		dto.setAfiliadoId(this.getAfiliadoId());
		dto.setTotalCopagos(this.getTotalCopagos());

		return (dto);
	}
}
