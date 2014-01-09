package com.conexia.saludcoop.common.entity.view;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.conexia.common.persistence.entity.Identifiable;
import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.dto.UbicacionSedeIpsEfectorProcedimientoDto;

/**
 * Vista auxiliar para obtención de ubicación de sedes de IPS efectoras de procedimientos.
 * 
 * @author Sebastián Matienzo
 */
@Entity
@Table(name = "ubicacion_sede_ips_efector_procedimiento", schema = "vista")
@Mappeable(mappedTo = UbicacionSedeIpsEfectorProcedimientoDto.class)
public class UbicacionSedeIpsEfectorProcedimiento implements Identifiable<Long> {

	/**
	 * Identificador de la entidad.
	 */
	@Id
	@Column(name = "id", nullable = false)
	private Long id;
	
	/**
	 * Identificador de EPS.
	 */
	@Column(name = "eps_id", nullable = false)
	private Long epsId;
	
	/**
	 * Identificador de sede de IPS.
	 */
	@Column(name = "sede_ips_id", nullable = false)
	private Long sedeIpsId;
	
	/**
	 * Nombre de sede de IPS.
	 */
	@Column(name = "sede_ips_nombre", nullable = false)
	private String sedeIpsNombre;
	
	/**
	 * Identificador de municipio.
	 */
	@Column(name = "municipio_id", nullable = false)
	private Long municipioId;
	
	/**
	 * Identificador de seccional.
	 */
	@Column(name = "division_seccional_id", nullable = false)
	private Long divisionSeccionalId;

	/**
	 * Identificador de regional.
	 */
	@Column(name = "regional_id", nullable = false)
	private Long regionalId;

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
	 * Código de tipo de minuta.
	 */
	@Column(name = "tipo_minuta_codigo", nullable = false)
	private String tipoMinutaCodigo;

	/**
	 * Monto fijo de procedimiento.
	 */
	@Column(name = "procedimiento_monto_fijo", nullable = false)
	private BigDecimal procedimientoMontoFijo;

	/**
	 * Valor original del procedimiento.
	 */
	@Column(name = "procedimiento_valor_original", nullable = false)
	private BigDecimal procedimientoValorOriginal;

	/**
	 * Porcentaje de negociación.
	 */
	@Column(name = "porcentaje_negociacion", nullable = false)
	private BigDecimal porcentajeNegociacion;

	/**
	 * Valor ajustado del procedimiento.
	 */
	@Column(name = "procedimiento_valor_ajustado", nullable = false)
	private BigDecimal procedimientoValorAjustado;

	@Override
	public Long getId() {
		
		return (this.id);
	}

	/**
	 * Obtiene el identificador de la Eps.
	 * 
	 * @return Identificador de la Eps.
	 */
	public Long getEpsId() {
		
		return (this.epsId);
	}

	/**
	 * Asigna el identificador de la Eps.
	 * 
	 * @param epsId Identificador de la Eps.
	 */
	public void setEpsId(final Long epsId) {
		
		this.epsId = epsId;
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
	 * Obtiene el nombre de la sede de Ips.
	 * 
	 * @return Nombre de la sede de Ips.
	 */
	public String getSedeIpsNombre() {
		
		return (this.sedeIpsNombre);
	}

	/**
	 * Asigna el nombre de la sede de Ips.
	 * 
	 * @param sedeIpsNombre Nombre de la sede de Ips.
	 */
	public void setSedeIpsNombre(final String sedeIpsNombre) {
		
		this.sedeIpsNombre = sedeIpsNombre;
	}

	/**
	 * Obtiene el identificador del municipio.
	 * 
	 * @return Identificador del municipio.
	 */
	public Long getMunicipioId() {
		
		return (this.municipioId);
	}

	/**
	 * Asigna el identificador del municipio.
	 * 
	 * @param municipioId Identificador del municipio.
	 */
	public void setMunicipioId(final Long municipioId) {
		
		this.municipioId = municipioId;
	}

	/**
	 * Obtiene el identificador de la división de seccional.
	 * 
	 * @return Identificador de la división de seccional.
	 */
	public Long getDivisionSeccionalId() {
		
		return (this.divisionSeccionalId);
	}

	/**
	 * Asigna el identificador de la división de seccional.
	 * 
	 * @param divisionSeccionalId Identificador de la división de seccional.
	 */
	public void setDivisionSeccionalId(final Long divisionSeccionalId) {
		
		this.divisionSeccionalId = divisionSeccionalId;
	}

	/**
	 * Obtiene el identificador de la regional.
	 * 
	 * @return Identificador de la regional.
	 */
	public Long getRegionalId() {
		
		return (this.regionalId);
	}

	/**
	 * Asigna el identificador de la regional.
	 * 
	 * @param regionalId Identificador de la regional.
	 */
	public void setRegionalId(final Long regionalId) {
		
		this.regionalId = regionalId;
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
	 * @param servicioId Identificador del procedimiento.
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
	 * Obtiene el código de tipo de minuta.
	 * 
	 * @return Código de tipo de minuta.
	 */
	public String getTipoMinutaCodigo() {
		
		return (this.tipoMinutaCodigo);
	}

	/**
	 * Asigna el código de tipo de minuta.
	 * 
	 * @param tipoMinutaCodigo Código de tipo de minuta.
	 */
	public void setTipoMinutaCodigo(final String tipoMinutaCodigo) {
		
		this.tipoMinutaCodigo = tipoMinutaCodigo;
	}

	/**
	 * Obtiene el monto fijo del procedimiento.
	 * 
	 * @return Monto fijo del procedimiento.
	 */
	public BigDecimal getProcedimientoMontoFijo() {
		
		return (this.procedimientoMontoFijo);
	}

	/**
	 * Asigna el monto fijo del procedimiento.
	 * 
	 * @param procedimientoMontoFijo Monto fijo del procedimiento.
	 */
	public void setProcedimientoMontoFijo(final BigDecimal procedimientoMontoFijo) {
		
		this.procedimientoMontoFijo = procedimientoMontoFijo;
	}

	/**
	 * Obtiene el valor original del procedimiento.
	 * 
	 * @return Valor original del procedimiento.
	 */
	public BigDecimal getProcedimientoValorOriginal() {
		
		return (this.procedimientoValorOriginal);
	}

	/**
	 * Asigna el valor original del procedimiento.
	 * 
	 * @param procedimientoValorOriginal Valor original del procedimiento.
	 */
	public void setProcedimientoValorOriginal(final BigDecimal procedimientoValorOriginal) {
		
		this.procedimientoValorOriginal = procedimientoValorOriginal;
	}

	/**
	 * Obtiene el porcentaje de negociación.
	 * 
	 * @return Porcentaje de negociación.
	 */
	public BigDecimal getPorcentajeNegociacion() {
		
		return (this.porcentajeNegociacion);
	}

	/**
	 * Asigna el porcentaje de negociación.
	 * 
	 * @param porcentajeNegociacion Porcentaje de negociación.
	 */
	public void setPorcentajeNegociacion(final BigDecimal porcentajeNegociacion) {
		
		this.porcentajeNegociacion = porcentajeNegociacion;
	}

	/**
	 * Obtiene el valor ajustado del procedimiento.
	 * 
	 * @return Valor ajustado del procedimiento.
	 */
	public BigDecimal getProcedimientoValorAjustado() {
		
		return (this.procedimientoValorAjustado);
	}

	/**
	 * Asigna el valor ajustado del procedimiento.
	 * 
	 * @param procedimientoValorAjustado Valor ajustado del procedimiento.
	 */
	public void setProcedimientoValorAjustado(final BigDecimal procedimientoValorAjustado) {
		
		this.procedimientoValorAjustado = procedimientoValorAjustado;
	}
	
	/**
	 * Obtiene el DTO que representa a esta entidad.
	 * 
	 * @return DTO que representa a esta entidad.
	 */
	public UbicacionSedeIpsEfectorProcedimientoDto toDto() {
		
		final UbicacionSedeIpsEfectorProcedimientoDto dto = new UbicacionSedeIpsEfectorProcedimientoDto();
		dto.setEpsId(this.getEpsId());
		dto.setSedeIpsId(this.getSedeIpsId());
		dto.setSedeIpsNombre(this.getSedeIpsNombre());
		dto.setMunicipioId(this.getMunicipioId());
		dto.setDivisionSeccionalId(this.getDivisionSeccionalId());
		dto.setRegionalId(this.getRegionalId());
		dto.setProcedimientoId(this.getProcedimientoId());
		dto.setServicioId(this.getServicioId());
		dto.setEspecialidadId(this.getEspecialidadId());
		dto.setTipoMinutaCodigo(this.getTipoMinutaCodigo());
		dto.setProcedimientoMontoFijo(this.getProcedimientoMontoFijo());
		dto.setProcedimientoValorOriginal(this.getProcedimientoValorOriginal());
		dto.setPorcentajeNegociacion(this.getPorcentajeNegociacion());
		dto.setProcedimientoValorAjustado(this.getProcedimientoValorAjustado());

		return (dto);
	}
}
