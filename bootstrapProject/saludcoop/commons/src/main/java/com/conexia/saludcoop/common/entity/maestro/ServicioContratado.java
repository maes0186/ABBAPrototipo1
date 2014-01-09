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

import ar.com.conexia.common.persistence.entity.Identifiable;

@Entity
@Table(name = "servicio_contratado", schema = "maestros")
public class ServicioContratado extends BaseMaestro implements Identifiable<Integer> {

	private static final long serialVersionUID = 7873132578775874174L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
		
	@ManyToOne
	@JoinColumn(name="servicio_id", nullable=false)
	private Servicio servicio;
	@ManyToOne
	@JoinColumn(name="contrato_id", nullable=false)
	private Contrato contrato;

	@Column(name = "fecha_vencimiento", nullable = false)
	private Date fechaVencimiento;

	@Column(name = "porcentaje_negociacion", nullable = false)
	private Double porcentajeNegociacion;

	@Column(name = "observaciones", nullable = true)
	private String observaciones;
	
	@Column(name = "nivel_complejidad", nullable = false)
	private Integer nivelComplejidad;

	@Column(name = "cantidad_promedio", nullable = true)
	private Integer cantidadPromedio;

	@OneToMany(fetch = FetchType.LAZY, mappedBy="servicioContratado")
	private Set<EspecialidadContratada> especialidadesContratadas;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="tarifario_excepcion_id")
	private Tarifario tarifarioExcepcion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="estado_servicio_contratado_id", nullable = false)
	private EstadoItemContrato estado;
	
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
	
	@Column(name = "cliente_pk", nullable = true)
	private Integer clientePk;

	

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public Integer getId() {
		return id;
	}

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

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Integer getCantidadPromedio() {
		return cantidadPromedio;
	}

	public void setCantidadPromedio(Integer cantidadPromedio) {
		this.cantidadPromedio = cantidadPromedio;
	}

	public Set<EspecialidadContratada> getEspecialidadesContratadas() {
		return especialidadesContratadas;
	}

	public void setEspecialidadesContratadas(
			Set<EspecialidadContratada> especialidadesContratadas) {
		this.especialidadesContratadas = especialidadesContratadas;
	}
	


	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		ServicioContratado that = (ServicioContratado) o;

		if (id != null ? !id.equals(that.id)
				: that.id != null)
			return false;

		return true;
	}

	public int hashCode() {
		return (id!= null ? id.hashCode() : 0);
	}

	public Tarifario getTarifarioExcepcion() {
		return tarifarioExcepcion;
	}

	public void setTarifarioExcepcion(Tarifario tarifarioExcepcion) {
		this.tarifarioExcepcion = tarifarioExcepcion;
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
