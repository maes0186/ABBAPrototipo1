package com.conexia.saludcoop.common.entity.maestro;

import java.math.BigDecimal;
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

import org.hibernate.annotations.Index;

import ar.com.conexia.common.persistence.entity.Identifiable;

/**
 * @author fgonzalez
 * 
 *        
 * 
 */
@Entity
@Table(name = "contrato", schema = "maestros")
public class Contrato extends BaseMaestro implements Identifiable<Long>{

	/**
	 * Identificador del contrato.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	/**
	 * Define el nombre del contrato
	 */
	@Index(name="ix_nombre_contrato")
	@Column(name = "nombre_contrato", nullable = false, length = 100)
	private String nombreContrato;

	/**
	 * Fecha de inicio del contrato.
	 */
	@Index(name="ix_contrato_fecha_inicio_contrato")
	@Column(name = "fecha_inicio_contrato", nullable = false)
	private Date fechaInicioContrato;

	/**
	 * Fecha de fin del contrato.
	 */
	@Index(name="ix_contrato_fecha_fin_contrato")
	@Column(name = "fecha_fin_contrato", nullable = false)
	private Date fechaFinContrato;

	/**
	 * Estado de aprobación por el contrato
	 */
	@Column(name = "aprobado_abogado", nullable = true)
	private Short aprAbogado;

	
	/**
	 * Define los dias de plazo para los pagos a terceros
	 */
	@Column(name = "plazo_dias_pago", nullable = false)
	private Integer plazoDiasPago;
	
	@Column(name = "legalizacion_contrato", nullable = false)
	private Short legalizacionContrato;
	
	@Column(name = "renovacion_automatica", nullable = false)
	private Short renovacionAutomatica;

	/**
	 * Define la fecha de aprobacion del contrato por parte del abogado
	 */
	@Index(name="ix_contrato_fecha_aprobado")
	@Column(name = "fecha_aprobado", nullable = true)
	private Date fechaAprobado;

	/**
	 * Define la fecha a partir de la cual se aplica los nuevos dias de pago a
	 * terceros
	 * 
	 */
	
	@ManyToOne
	@JoinColumn(name="sede_ips_id", nullable = false)
	private SedeIps sedeIps;
	
	@Column(name = "fecha_dias_plazo", nullable = false)
	private Date fechaDiasPlazo;

	
	@ManyToOne
	@JoinColumn(name="tipo_plan_contrato_id", nullable = false)
	private TipoPlanContrato tipoPlanContrato;

	/**
	 * Tarifario referencia para calculo de tarifas.
	 */
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="contrato")
	private Set<ContratoTarifario> tarifarios;
	
	/**
	 * Porcentaje negociado sobre el tarifario. aplica a todos los servicios contratados salvo las definidas en excepciones de practica.
	 */
	@Column(name="porcentaje_negociado", precision=5, scale=2)
	private BigDecimal porcentajeNegociado;
    
	
	@ManyToOne
	@JoinColumn( name="estado_contrato_id", nullable = true)
	private EstadoContrato estado;

	@ManyToOne
	@JoinColumn( name="eps_id", nullable = true)
	private Eps eps;
	
	@Column(name="cliente_pk", nullable = true)
	private Integer clientePk;
	
	
    @OneToMany(fetch = FetchType.LAZY, mappedBy="contrato")
	private Set<ServicioContratado> serviciosContratados;
	
	public BigDecimal getPorcentajeNegociado() {
		return porcentajeNegociado;
	}

	public void setPorcentajeNegociado(BigDecimal porcentajeNegociado) {
		this.porcentajeNegociado = porcentajeNegociado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombreContrato() {
		return nombreContrato;
	}

	public void setNombreContrato(String nombreContrato) {
		this.nombreContrato = nombreContrato;
	}

	
	public Date getFechaDiasPlazo() {
		return fechaDiasPlazo;
	}

	public void setFechaDiasPlazo(Date fechaDiasPlazo) {
		this.fechaDiasPlazo = fechaDiasPlazo;
	}

	
	public Date getFechaInicioContrato() {
		return fechaInicioContrato;
	}

	public void setFechaInicioContrato(Date fechaInicioContrato) {
		this.fechaInicioContrato = fechaInicioContrato;
	}


	public Date getFechaFinContrato() {
		return fechaFinContrato;
	}

	public void setFechaFinContrato(Date fechaFinContrato) {
		this.fechaFinContrato = fechaFinContrato;
	}

	
	public Short getAprAbogado() {
		return aprAbogado;
	}

	public void setAprAbogado(Short aprAbogado) {
		this.aprAbogado = aprAbogado;
	}

	
	public Integer getPlazoDiasPago() {
		return plazoDiasPago;
	}

	public void setPlazoDiasPago(Integer plazoDiasPago) {
		this.plazoDiasPago = plazoDiasPago;
	}

	
	public Date getFechaAprobado() {
		return fechaAprobado;
	}

	public void setFechaAprobado(Date fechaAprobado) {
		this.fechaAprobado = fechaAprobado;
	}

	
	public Set<ServicioContratado> getServiciosContratados() {
		return serviciosContratados;
	}

	public void setServiciosContratados(
			Set<ServicioContratado> serviciosContratados) {
		this.serviciosContratados = serviciosContratados;
	}



	public TipoPlanContrato getTipoPlanContrato() {
		return tipoPlanContrato;
	}

	public void setTipoPlanContrato(TipoPlanContrato tipoPlanContrato) {
		this.tipoPlanContrato = tipoPlanContrato;
	}

	public Integer getClientePk() {
		return clientePk;
	}

	public void setClientePk(Integer clientePk) {
		this.clientePk = clientePk;
	}

	public Short getLegalizacionContrato() {
		return legalizacionContrato;
	}

	public void setLegalizacionContrato(Short legalizacionContrato) {
		this.legalizacionContrato = legalizacionContrato;
	}

	public Short getRenovacionAutomatica() {
		return renovacionAutomatica;
	}

	public void setRenovacionAutomatica(Short renovacionAutomatica) {
		this.renovacionAutomatica = renovacionAutomatica;
	}

	public EstadoContrato getEstado() {
		return estado;
	}

	public void setEstado(EstadoContrato estado) {
		this.estado = estado;
	}

    public SedeIps getSedeIps() {
    	return sedeIps;
    }
	
    public void setSedeIps(SedeIps sedeIps) {
    	this.sedeIps = sedeIps;
    }

    public Eps getEps() {
    	return eps;
    }
	
    public void setEps(Eps eps) {
    	this.eps = eps;
    }

	public Set<ContratoTarifario> getTarifarios() {
		return tarifarios;
	}

	public void setTarifarios(Set<ContratoTarifario> tarifarios) {
		this.tarifarios = tarifarios;
	}
}
