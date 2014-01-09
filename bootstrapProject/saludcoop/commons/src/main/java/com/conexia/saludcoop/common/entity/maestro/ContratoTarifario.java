package com.conexia.saludcoop.common.entity.maestro;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import ar.com.conexia.common.persistence.entity.Identifiable;

@Entity
@Table(name = "contrato_tarifario", schema = "maestros", 
uniqueConstraints = {
		@UniqueConstraint(columnNames = { "tarifario_id","contrato_id", "nivel_atencion"})
		
		})
public class ContratoTarifario extends BaseMaestro implements Identifiable<Integer> {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
		
	@ManyToOne
	@JoinColumn(name="contrato_id", nullable=false)
	private Contrato contrato;

	@ManyToOne
	@JoinColumn(name="tarifario_id", nullable=false)
	private Tarifario tarifario;
	
	@Column(name="nivel_atencion")
	private Short nivelAtencion;
	
	@Column(name = "porcentaje_descuento", nullable = false)
	private BigDecimal porcentajeDescuento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="tipo_minuta_id", nullable = false)
	private TipoMinuta tipoMinuta;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="estado_contrato_tarifario_id", nullable = false)
	private EstadoItemContrato estado;
	

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public Integer getId() {
		return id;
	}

	public int hashCode() {
		return (id!= null ? id.hashCode() : 0);
	}

	public Tarifario getTarifario() {
		return tarifario;
	}

	public void setTarifario(Tarifario tarifario) {
		this.tarifario = tarifario;
	}

	public Short getNivelAtencion() {
		return nivelAtencion;
	}

	public void setNivelAtencion(Short nivelAtencion) {
		this.nivelAtencion = nivelAtencion;
	}

	public EstadoItemContrato getEstado() {
		return estado;
	}

	public void setEstado(EstadoItemContrato estado) {
		this.estado = estado;
	}

	public BigDecimal getPorcentajeNegociacion() {
		return porcentajeDescuento;
	}

	public void setPorcentajeNegociacion(BigDecimal porcentajeNegociacion) {
		this.porcentajeDescuento = porcentajeNegociacion;
	}

	public TipoMinuta getTipoMinuta() {
		return tipoMinuta;
	}

	public void setTipoMinuta(TipoMinuta tipoMinuta) {
		this.tipoMinuta = tipoMinuta;
	}


	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		ContratoTarifario that = (ContratoTarifario) o;

		if (id != null ? !id.equals(that.id)
				: that.id != null)
			return false;

		return true;
	}




}
