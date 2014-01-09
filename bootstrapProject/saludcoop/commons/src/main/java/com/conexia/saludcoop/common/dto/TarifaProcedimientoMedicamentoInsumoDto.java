package com.conexia.saludcoop.common.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.conexia.saludcoop.common.enumerator.TipoTarifario;

/**
 * DTO de vista auxiliar para obtención de tarifas de procedimientos.
 * 
 * @author Sebastián Matienzo
 */
public class TarifaProcedimientoMedicamentoInsumoDto {

	/**
	 * Identificador de sede de IPS.
	 */
	private Long sedeIpsId;

	/**
	 * Identificador del procedimiento/medicamento.
	 */
	private Long itemId;

	/**
	 * Identificador del servicio.
	 */
	private Long servicioId;

	/**
	 * Identificador de la especialidad.
	 */
	private Integer especialidadId;

	/**
	 * Fecha de inicio en que aplica el contrato.
	 */
	private Date fechaInicioContrato;

	/**
	 * Fecha de finalización en que aplica el contrato.
	 */
	private Date fechaFinalizacionContrato;

	/**
	 * Fecha de finalización en que aplica el servicio contratado.
	 */
	private Date fechaFinalizacionServicioContratado;

	/**
	 * Fecha de finalización en que aplica la especialidad contratada.
	 */
	private Date fechaFinalizacionEspecialidadContratada;

	/**
	 * Fecha de finalización en que aplica el ítem (procedimiento/medicamento) contratado.
	 */
	private Date fechaFinalizacionItemContratado;

	/**
	 * Tipo de tarifario.
	 */
	private TipoTarifario tipoTarifario;

	/**
	 * Factor de la tarifa.
	 */
	private BigDecimal factor;

	/**
	 * Uvr de la tarifa.
	 */
	private BigDecimal uvr;

	/**
	 * Valor de la tarifa.
	 */
	private BigDecimal valor;

	/**
	 * Indica si es un procedimiento/medicamento quirúrgico.
	 */
	private boolean quirugico;

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
	 * Obtiene el identificador del procedimiento/medicamento.
	 * 
	 * @return Identificador del procedimiento/medicamento.
	 */
	public Long getItemId() {
		
		return (this.itemId);
	}

	/**
	 * Asigna el identificador del procedimiento/medicamento.
	 * 
	 * @param itemId Identificador del procedimiento/medicamento.
	 */
	public void setItemId(final Long itemId) {
		
		this.itemId = itemId;
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
	 * Obtiene la fecha de finalización del ítem contratado.
	 * 
	 * @return Fecha de finalización del ítem contratado.
	 */
	public Date getFechaFinalizacionItemContratado() {
		
		return (this.fechaFinalizacionItemContratado);
	}

	/**
	 * Asigna la fecha de finalización del ítem contratado.
	 * 
	 * @param fechaFinalizacionItemContratado Fecha de finalización del ítem contratado.
	 */
	public void setFechaFinalizacionItemContratado(final Date fechaFinalizacionItemContratado) {
		
		this.fechaFinalizacionItemContratado = fechaFinalizacionItemContratado;
	}

	/**
	 * Obtiene el tipo de tarifario.
	 * 
	 * @return Tipo de tarifario.
	 */
	public TipoTarifario getTipoTarifario() {
		
		return (this.tipoTarifario);
	}

	/**
	 * Asigna el tipo de tarifario.
	 * 
	 * @param tipoTarifario Tipo de tarifario.
	 */
	public void setTipoTarifario(final TipoTarifario tipoTarifario) {
		
		this.tipoTarifario = tipoTarifario;
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
	 * Indica si es un medicamento quirúrgico.
	 * 
	 * @return True si es un medicamento quirúrgico; caso contrario, False.
	 */
	public boolean isQuirurgico() {
		
		return (this.quirugico);
	}

	/**
	 * Asigna el indicador de si es un medicamento quirúrgico.
	 * 
	 * @param quirugico Indicador de si es un medicamento quirúrgico.
	 */
	public void setQuirurgico(final boolean quirugico) {
		
		this.quirugico = quirugico;
	}
}
