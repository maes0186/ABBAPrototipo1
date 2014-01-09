package com.conexia.saludcoop.common.entity.maestro;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import ar.com.conexia.common.persistence.entity.Identifiable;

import com.conexia.saludcoop.common.dto.MontosCopagoDto;

/**
 * Definición de montos del copago contributivo.
 * 
 * @author Sebastián Matienzo
 */
@Entity
@Table(name = "montos_copago_contributivo", schema = "maestros")
public class MontosCopagoContributivo extends BaseMaestro implements Identifiable<Long> {

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
	 * Nivel de IBC al que aplican los valores.
	 */
	@OneToOne
	@JoinColumn(nullable = false, name="nivel_ibc_id")
	@ForeignKey(name = "fk_montos_copago_contributivo_nivel_ibc")
	private NivelIBC nivelIbc;

	/**
	 * Valor de la cuota moderadora.
	 */
	@Column(name = "valor_cuota_moderadora", nullable = false)
	private BigDecimal valorCuotaModeradora;

	/**
	 * Porcentaje del valor del servicio que corresponde como copago.
	 */
	@Column(name = "copago_porcentaje_valor_servicio", nullable = false)
	private BigDecimal copagoPorcentajeValorServicio;

	/**
	 * Tope anual de copagos para la misma patología.
	 */
	@Column(name = "copago_tope_anual_misma_patologia", nullable = false)
	private BigDecimal copagoTopeAnualMismaPatologia;

	/**
	 * Tope anual de copagos todas las patologías.
	 */
	@Column(name = "copago_tope_anual_cualquier_patologia", nullable = false)
	private BigDecimal copagoTopeAnualCualquierPatologia;

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
	 * Obtiene el nivel IBC.
	 * 
	 * @return Nivel IBC.
	 */
	public NivelIBC getNivelIBC() {
		
		return (this.nivelIbc);
	}

	/**
	 * Asigna el nivel IBC.
	 * 
	 * @param nivelIBC Nivel IBC.
	 */
	public void setNivelIBC(final NivelIBC nivelIbc) {
		
		this.nivelIbc = nivelIbc;
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

	/**
	 * Obtiene el DTO que representa a esta entidad.
	 * 
	 * @return DTO que representa a esta entidad.
	 */
	public MontosCopagoDto toDto() {
		
		MontosCopagoDto dto = new MontosCopagoDto();
		dto.setId(this.id);
		dto.setAnio(this.getAnio());
		
		if (this.getNivelIBC() != null) {
			dto.setNivel(new Integer(this.getNivelIBC().getCodigo()));
		}
		
		dto.setValorCuotaModeradora(this.getValorCuotaModeradora());
		dto.setCopagoPorcentajeValorServicio(this.getCopagoPorcentajeValorServicio());
		dto.setCopagoTopeAnualMismaPatologia(this.getCopagoTopeAnualMismaPatologia());
		dto.setCopagoTopeAnualCualquierPatologia(this.getCopagoTopeAnualCualquierPatologia());

		return (dto);
	}
}
