package com.conexia.saludcoop.common.entity.maestro;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.conexia.saludcoop.common.enumerator.TipoTarifario;

/**
 * 
 * Entidad tarifario
 * 
 * @author fgonzalez
 *
 */
@Entity
@Table(name = "tarifario", schema="maestros")
public class Tarifario extends BaseMaestro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8269035060995199679L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "porcentaje_ajuste", nullable = false)
	private Double porcentajeAjuste;
	
	@Column(name="nombre", nullable=false)
	private String nombre;

	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="tarifario_id",nullable=false)
	private Set<ProcedimientoTarifa> procedimientos;
	
	@Column(name="cliente_pk")
	private Integer clientePk;
	
	/**
	 * Identificador del tipo de tarifario.
	 * 
	 * TODO Este atributo debe ser persistente, no transient.
	 */
	@Transient
	private Long tipoTarifarioId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public Double getPorcentajeAjuste() {
		return porcentajeAjuste;
	}

	public void setPorcentajeAjuste(Double porcentajeAjuste) {
		this.porcentajeAjuste = porcentajeAjuste;
	}
	
	public Set<ProcedimientoTarifa> getProcedimientos() {
		return procedimientos;
	}

	public void setProcedimientos(Set<ProcedimientoTarifa> procedimientos) {
		this.procedimientos = procedimientos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getClientePk() {
		return clientePk;
	}

	public void setClientePk(Integer clientePk) {
		this.clientePk = clientePk;
	}

	/**
	 * Obtiene el tipo de tarifario.
	 * 
	 * @return Tipo de tarifario.
	 */
	public TipoTarifario getTipoTarifario() {
		
		return (TipoTarifario.fromId(this.tipoTarifarioId));
	}

	/**
	 * Asigna el tipo de tarifario.
	 * 
	 * @param tipoTarifario Tipo de tarifario.
	 */
	public void setTipoTarifario(final TipoTarifario tipoTarifario) {
		
		if (tipoTarifarioId != null) {
			this.tipoTarifarioId = tipoTarifario.getId();
		} else {
			this.tipoTarifarioId = null;
		}
	}
}
