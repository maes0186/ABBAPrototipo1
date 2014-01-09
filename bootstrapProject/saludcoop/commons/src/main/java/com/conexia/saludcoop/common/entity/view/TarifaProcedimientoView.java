package com.conexia.saludcoop.common.entity.view;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.conexia.common.persistence.entity.Identifiable;
import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.dto.TarifaProcedimientoMedicamentoInsumoDto;
import com.conexia.saludcoop.common.enumerator.TipoTarifario;

/**
 * Vista auxiliar para obtención de tarifas de procedimientos.
 * 
 * @author Sebastián Matienzo
 */
@Entity
@Table(name = "tarifa_procedimiento_view", schema = "vista")
@Mappeable(mappedTo = TarifaProcedimientoMedicamentoInsumoDto.class)
public class TarifaProcedimientoView implements Identifiable<Long> {

	/**
	 * Identificador de la entidad.
	 */
	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	/**
	 * Identificador de sede de IPS.
	 */
	@Column(name = "sede_ips_id", nullable = false)
	private Long sedeIpsId;

	/**
	 * Identificador del procedimiento.
	 */
	@Column(name = "procedimiento_id", nullable = false)
	private Long procedimientoId;

	/**
	 * Identificador del servicio.
	 */
	@Column(name = "servicio_id", nullable = false)
	private Long servicioId;

	/**
	 * Identificador de la especialidad.
	 */
	@Column(name = "especialidad_id", nullable = false)
	private Integer especialidadId;

	/**
	 * Fecha de inicio en que aplica el contrato.
	 */
	@Column(name = "fecha_inicio_contrato", nullable = false)
	private Date fechaInicioContrato;

	/**
	 * Fecha de finalización en que aplica el contrato.
	 */
	@Column(name = "fecha_finalizacion_contrato", nullable = false)
	private Date fechaFinalizacionContrato;

	/**
	 * Fecha de finalización en que aplica el servicio contratado.
	 */
	@Column(name = "fecha_finalizacion_servicio_contratado", nullable = false)
	private Date fechaFinalizacionServicioContratado;

	/**
	 * Fecha de finalización en que aplica la especialidad contratada.
	 */
	@Column(name = "fecha_finalizacion_especialidad_contratada", nullable = false)
	private Date fechaFinalizacionEspecialidadContratada;

	/**
	 * Fecha de finalización en que aplica el procedimiento contratado.
	 */
	@Column(name = "fecha_finalizacion_procedimiento_contratado", nullable = false)
	private Date fechaFinalizacionProcedimientoContratado;

	/**
	 * Tipo de tarifario.
	 */
	@Column(name = "tipo_tarifario_id", nullable = false)
	private Long tipoTarifarioId;

	/**
	 * Factor de la tarifa.
	 */
	@Column(name = "factor", nullable = false)
	private BigDecimal factor;

	/**
	 * Uvr de la tarifa.
	 */
	@Column(name = "uvr", nullable = false)
	private BigDecimal uvr;

	/**
	 * Valor de la tarifa.
	 */
	@Column(name = "valor", nullable = false)
	private BigDecimal valor;

	/**
	 * Indica si es un medicamento quirúrgico.
	 */
	@Column(name = "quirurgico", nullable = false)
	private boolean quirugico;

	@Override
	public Long getId() {
		
		return (this.id);
	}

	/**
	 * Obtiene el identificador de la sede de Ips.
	 * 
	 * @return Identificador de la sede de Ips.
	 */
	public Long getSedeIpsId() {
		
		return (this.sedeIpsId);
	}

	/**
	 * Asigna el identificador de la sede de Ips.
	 * 
	 * @param sedeIpsId Identificador de la sede de Ips.
	 */
	public void setSedeIpsId(final Long sedeIpsId) {
		
		this.sedeIpsId = sedeIpsId;
	}

	/**
	 * Obtiene el identificador del procedimiento.
	 * 
	 * @return Identificador del procedimiento.
	 */
	public Long getProcedimientoId() {
		
		return (this.procedimientoId);
	}

	/**
	 * Asigna el identificador del procedimiento.
	 * 
	 * @param procedimientoId Identificador del procedimiento.
	 */
	public void setProcedimientoId(final Long procedimientoId) {
		
		this.procedimientoId = procedimientoId;
	}

	/**
	 * Obtiene el identificador del servicio.
	 * 
	 * @return Identificador del servicio.
	 */
	public Long getServicioId() {
		
		return (this.servicioId);
	}

	/**
	 * Asigna el identificador del servicio.
	 * 
	 * @param servicioId Identificador del servicio.
	 */
	public void setServicioId(final Long servicioId) {
		
		this.servicioId = servicioId;
	}

	/**
	 * Obtiene el identificador de la especialidad.
	 * 
	 * @return Identificador de la especialidad.
	 */
	public Integer getEspecialidadId() {
		
		return (this.especialidadId);
	}

	/**
	 * Asigna el identificador de la especialidad.
	 * 
	 * @param especialidadId Identificador de la especialidad.
	 */
	public void setEspecialidadId(final Integer especialidadId) {
		
		this.especialidadId = especialidadId;
	}

	/**
	 * Obtiene la fecha de inicio del contrato.
	 * 
	 * @return Fecha de inicio del contrato.
	 */
	public Date getFechaInicioContrato() {
		
		return (this.fechaInicioContrato);
	}

	/**
	 * Asigna la fecha de inicio del contrato.
	 * 
	 * @param fechaInicioContrato Fecha de inicio del contrato.
	 */
	public void setFechaInicioContrato(final Date fechaInicioContrato) {
		
		this.fechaInicioContrato = fechaInicioContrato;
	}

	/**
	 * Obtiene la fecha de finalización del contrato.
	 * 
	 * @return Fecha de finalización del contrato.
	 */
	public Date getFechaFinalizacionContrato() {
		
		return (this.fechaFinalizacionContrato);
	}

	/**
	 * Asigna la fecha de finalización del contrato.
	 * 
	 * @param fechaFinalizacionContrato Fecha de finalización del contrato.
	 */
	public void setFechaFinalizacionContrato(final Date fechaFinalizacionContrato) {
		
		this.fechaFinalizacionContrato = fechaFinalizacionContrato;
	}

	/**
	 * Obtiene la fecha de finalización del servicio contratado.
	 * 
	 * @return Fecha de finalización del servicio contratado.
	 */
	public Date getFechaFinalizacionServicioContratado() {
		
		return (this.fechaFinalizacionServicioContratado);
	}

	/**
	 * Asigna la fecha de finalización del servicio contratado.
	 * 
	 * @param fechaFinalizacionServicioContratado Fecha de finalización del servicio contratado.
	 */
	public void setFechaFinalizacionServicioContratado(final Date fechaFinalizacionServicioContratado) {
		
		this.fechaFinalizacionServicioContratado = fechaFinalizacionServicioContratado;
	}

	/**
	 * Obtiene la fecha de finalización de la especialidad contratada.
	 * 
	 * @return Fecha de finalización de la especialidad contratada.
	 */
	public Date getFechaFinalizacionEspecialidadContratada() {
		
		return (this.fechaFinalizacionEspecialidadContratada);
	}

	/**
	 * Asigna la fecha de finalización de la especialidad contratada.
	 * 
	 * @param fechaFinalizacionEspecialidadContratada Fecha de finalización de la especialidad contratada.
	 */
	public void setFechaFinalizacionEspecialidadContratada(final Date fechaFinalizacionEspecialidadContratada) {
		
		this.fechaFinalizacionEspecialidadContratada = fechaFinalizacionEspecialidadContratada;
	}

	/**
	 * Obtiene la fecha de finalización del procedimiento contratado.
	 * 
	 * @return Fecha de finalización del procedimiento contratado.
	 */
	public Date getFechaFinalizacionProcedimientoContratado() {
		
		return (this.fechaFinalizacionProcedimientoContratado);
	}

	/**
	 * Asigna la fecha de finalización del procedimiento contratado.
	 * 
	 * @param fechaFinalizacionProcedimientoContratado Fecha de finalización del procedimiento contratado.
	 */
	public void setFechaFinalizacionProcedimientoContratado(final Date fechaFinalizacionProcedimientoContratado) {
		
		this.fechaFinalizacionProcedimientoContratado = fechaFinalizacionProcedimientoContratado;
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

	/**
	 * Obtiene el factor de la tarifa.
	 * 
	 * @return Factor de la tarifa.
	 */
	public BigDecimal getFactor() {
		
		return (this.factor);
	}

	/**
	 * Asigna el factor de la tarifa.
	 * 
	 * @param factor Factor de la tarifa.
	 */
	public void setFactor(final BigDecimal factor) {
		
		this.factor = factor;
	}

	/**
	 * Obtiene el uvr de la tarifa.
	 * 
	 * @return Uvr de la tarifa.
	 */
	public BigDecimal getUvr() {
		
		return (this.uvr);
	}

	/**
	 * Asigna el uvr de la tarifa.
	 * 
	 * @param uvr Uvr de la tarifa.
	 */
	public void setUvr(final BigDecimal uvr) {
		
		this.uvr = uvr;
	}

	/**
	 * Obtiene el valor de la tarifa.
	 * 
	 * @return Valor de la tarifa.
	 */
	public BigDecimal getValor() {
		
		return (this.valor);
	}

	/**
	 * Asigna el valor de la tarifa.
	 * 
	 * @param valor Valor de la tarifa.
	 */
	public void setValor(final BigDecimal valor) {
		
		this.valor = valor;
	}

	/**
	 * Indica si es un procedimiento quirúrgico.
	 * 
	 * @return True si es un procedimiento quirúrgico; caso contrario, False.
	 */
	public boolean isQuirurgico() {
		
		return (this.quirugico);
	}

	/**
	 * Asigna el indicador de si es un procedimiento quirúrgico.
	 * 
	 * @param quirugico Indicador de si es un procedimiento quirúrgico.
	 */
	public void setQuirurgico(final boolean quirugico) {
		
		this.quirugico = quirugico;
	}
	
	/**
	 * Obtiene el DTO que representa a esta entidad.
	 * 
	 * @return DTO que representa a esta entidad.
	 */
	public TarifaProcedimientoMedicamentoInsumoDto toDto() {
		
		TarifaProcedimientoMedicamentoInsumoDto dto = new TarifaProcedimientoMedicamentoInsumoDto();
		dto.setSedeIpsId(this.getSedeIpsId());
		dto.setItemId(this.getProcedimientoId());
		dto.setServicioId(this.getServicioId());
		dto.setEspecialidadId(this.getEspecialidadId());
		dto.setFechaInicioContrato(this.getFechaInicioContrato());
		dto.setFechaFinalizacionContrato(this.getFechaFinalizacionContrato());
		dto.setFechaFinalizacionServicioContratado(this.getFechaFinalizacionServicioContratado());
		dto.setFechaFinalizacionEspecialidadContratada(this.getFechaFinalizacionEspecialidadContratada());
		dto.setFechaFinalizacionItemContratado(this.getFechaFinalizacionProcedimientoContratado());
		dto.setTipoTarifario(this.getTipoTarifario());
		dto.setFactor(this.getFactor());
		dto.setUvr(this.getUvr());
		dto.setValor(this.getValor());
		dto.setQuirurgico(this.isQuirurgico());

		return (dto);
	}
}
