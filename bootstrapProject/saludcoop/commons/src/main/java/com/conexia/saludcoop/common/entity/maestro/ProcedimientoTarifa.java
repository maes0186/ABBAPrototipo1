package com.conexia.saludcoop.common.entity.maestro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ar.com.conexia.common.persistence.entity.Identifiable;

/**
 * 
 * Procedimiento Tarifa
 * 
 * @author fgonzalez
 * 
 */
@Entity
@Table(name = "procedimiento_tarifa", schema = "maestros")
public class ProcedimientoTarifa extends BaseMaestro implements Identifiable<Long> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	

	@ManyToOne
	@JoinColumn( nullable = false)
	private Procedimiento procedimiento;
	
	
	@Column(name="valor", nullable= false)
	private Double valor;

	/**
	 * se usa únicamente para los tarifarios del tipo SOAT
	 */
	@Column(name="factor", nullable = true)
	private Double factor;
	
	/**
	 * se usa únicamente para los tarifarios del tipo iss
	 */
	@Column(name="uvr", nullable = true)
	private Double uvr;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Procedimiento getProcedimiento() {
		return procedimiento;
	}

	public void setProcedimiento(Procedimiento procedimiento) {
		this.procedimiento = procedimiento;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getFactor() {
		return factor;
	}

	public void setFactor(Double factor) {
		this.factor = factor;
	}

	public Double getUvr() {
		return uvr;
	}

	public void setUvr(Double uvr) {
		this.uvr = uvr;
	}

}
