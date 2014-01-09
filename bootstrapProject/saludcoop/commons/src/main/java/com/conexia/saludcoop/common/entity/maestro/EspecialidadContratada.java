package com.conexia.saludcoop.common.entity.maestro;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import ar.com.conexia.common.persistence.entity.Identifiable;

@Entity
@Table(name = "especialidad_contratada", schema = "maestros")

@Where(clause="delete < now()")
@SQLDelete(sql="update ")
public class EspecialidadContratada extends BaseMaestro implements Identifiable<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name="servicio_contratado_id", nullable=false)
	private ServicioContratado servicioContratado;
	
	@ManyToOne
	@JoinColumn(name="especialidad_id", nullable=false)
	private Especialidad especialidad;

	@Column(name = "fecha_vencimiento", nullable = false)
	private Date fechaVencimiento;

	@Column(name = "porcentaje_negociacion", nullable = false)
	private Double porcentajeNegociacion;
	
	@Column(name = "nivel_complejidad", nullable = false)
	private Integer nivelComplejidad;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tarifario_excepcion_id")
	private Tarifario tarifarioExcepcion;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "especialidadContratada")
	private Set<ProcedimientoContratado> procedimientosContratados;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipo_minuta_id", nullable = true)
	private TipoMinuta tipoMinuta;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "estado_especialidad_contratada_id", nullable = true)
	private EstadoItemContrato estado;

	@Column(name = "otro_si", nullable = true)
	private Short otroSi;

	@Column(name = "monto_fijo", nullable = true)
	private Double montoFijo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unidad_tiempo_id", nullable = true)
	private UnidadTiempo unidadTiempo;
	
	
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

	public Tarifario getTarifarioExcepcion() {
		return tarifarioExcepcion;
	}

	public void setTarifarioExcepcion(Tarifario tarifarioExcepcion) {
		this.tarifarioExcepcion = tarifarioExcepcion;
	}

	public Set<ProcedimientoContratado> getProcedimientosContratados() {
		return procedimientosContratados;
	}

	public void setProcedimientosContratados(Set<ProcedimientoContratado> procedimientosContratados) {
		this.procedimientosContratados = procedimientosContratados;
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

	public EstadoItemContrato getEstado() {
		return estado;
	}

	public void setEstado(EstadoItemContrato estado) {
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public ServicioContratado getServicioContratado() {
		return servicioContratado;
	}

	public void setServicioContratado(ServicioContratado servicioContratado) {
		this.servicioContratado = servicioContratado;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	public Integer getClientePk() {
		return clientePk;
	}

	public void setClientePk(Integer clientePk) {
		this.clientePk = clientePk;
	}

	public Integer getNivelComplejidad() {
		return nivelComplejidad;
	}

	public void setNivelComplejidad(Integer nivelComplejidad) {
		this.nivelComplejidad = nivelComplejidad;
	}

}
