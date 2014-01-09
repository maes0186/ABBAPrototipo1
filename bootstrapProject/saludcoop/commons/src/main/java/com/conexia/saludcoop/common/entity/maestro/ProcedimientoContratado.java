package com.conexia.saludcoop.common.entity.maestro;

import java.util.Date;

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
@Table(name = "procedimiento_contratado", schema = "maestros",
uniqueConstraints = {@UniqueConstraint(columnNames={"especialidad_contratada_id","procedimiento_id"})}
		)
public class ProcedimientoContratado extends BaseMaestro implements Identifiable<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="especialidad_contratada_id", nullable=false)
	private EspecialidadContratada especialidadContratada;
	@ManyToOne
	@JoinColumn(name="procedimiento_id", nullable=false)
	private Procedimiento procedimiento;
	

	@Column(name = "fecha_vencimiento", nullable = false)
	private Date fechaVencimiento;
	
	@Column(name = "porcentaje_negociacion", nullable = false)
	private Double porcentajeNegociacion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="tarifario_id", nullable = false)
	private Tarifario tarifario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="tipo_minuta_id", nullable = false)
	private TipoMinuta tipoMinuta;
	
	@Column(name = "otro_si", nullable = false)
	private Short otroSi;
	
	@Column(name = "monto_fijo", nullable = false)
	private Double montoFijo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="unidad_tiempo_id", nullable = false)
	private UnidadTiempo unidadTiempo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="estado_procedimiento_contratado_id", nullable = false)
	private EstadoItemContrato estadoProcedimientoContratado;

	@Column(name = "requiere_auto", nullable = false)
	private Double requiereAuto;
	
	@Column(name = "cliente_pk", nullable = true)
	private Integer clientePk;

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public Double getPorcentajeNegociacion() {
		return porcentajeNegociacion;
	}

	public void setPorcentajeNegociacion(Double porcentajeNegociacion) {
		this.porcentajeNegociacion = porcentajeNegociacion;
	}

	public Tarifario getTarifario() {
		return tarifario;
	}

	public void setTarifario(Tarifario tarifario) {
		this.tarifario = tarifario;
	}
	


	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		ProcedimientoContratado that = (ProcedimientoContratado) o;

		if (getId() != null ? !getId().equals(that.getId())
				: that.getId() != null)
			return false;

		return true;
	}

	public TipoMinuta getTipoMinuta() {
		return tipoMinuta;
	}

	public void setTipoMinuta(TipoMinuta tipoMinuta) {
		this.tipoMinuta = tipoMinuta;
	}

	public Short getOtroSi() {
		return otroSi;
	}

	public void setOtroSi(Short otroSi) {
		this.otroSi = otroSi;
	}

	public Double getMontoFijo() {
		return montoFijo;
	}

	public void setMontoFijo(Double montoFijo) {
		this.montoFijo = montoFijo;
	}

	public UnidadTiempo getUnidadTiempo() {
		return unidadTiempo;
	}

	public void setUnidadTiempo(UnidadTiempo unidadTiempo) {
		this.unidadTiempo = unidadTiempo;
	}


	public EstadoItemContrato getEstadoProcedimientoContratado() {
		return estadoProcedimientoContratado;
	}

	public void setEstadoProcedimientoContratado(
			EstadoItemContrato estadoProcedimientoContratado) {
		this.estadoProcedimientoContratado = estadoProcedimientoContratado;
	}

	public Double getRequiereAuto() {
		return requiereAuto;
	}

	public void setRequiereAuto(Double requiereAuto) {
		this.requiereAuto = requiereAuto;
	}
	public Long getId() {
		return id;
	}
	public EspecialidadContratada getEspecialidadContratada() {
		return especialidadContratada;
	}
	public void setEspecialidadContratada(EspecialidadContratada especialidadContratada) {
		this.especialidadContratada = especialidadContratada;
	}
	public Procedimiento getProcedimiento() {
		return procedimiento;
	}
	public void setProcedimiento(Procedimiento procedimiento) {
		this.procedimiento = procedimiento;
	}

	public Integer getClientePk() {
		return clientePk;
	}

	public void setClientePk(Integer clientePk) {
		this.clientePk = clientePk;
	}

}
