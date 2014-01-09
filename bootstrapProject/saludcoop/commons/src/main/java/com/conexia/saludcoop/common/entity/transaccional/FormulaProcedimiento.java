package com.conexia.saludcoop.common.entity.transaccional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ar.com.conexia.common.persistence.entity.Identifiable;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.dto.transaccional.FormulaProcedimientoDto;
import com.conexia.saludcoop.common.entity.maestro.CausaExterna;
import com.conexia.saludcoop.common.entity.maestro.Finalidad;
import com.conexia.saludcoop.common.entity.maestro.Lateralidad;
import com.conexia.saludcoop.common.entity.maestro.ObjetivoProcedimiento;
import com.conexia.saludcoop.common.entity.maestro.OrigenRepeticion;
import com.conexia.saludcoop.common.entity.maestro.TipoCatastrofico;
import com.conexia.saludcoop.common.entity.maestro.TipoPrestacion;
@Entity
@Table(name="formula_item_procedimiento", schema="transaccional")
public class FormulaProcedimiento implements Identifiable<Long> {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@OneToOne
	@JoinColumn(name="solicitud_procedimiento_id", nullable=false)
	private SolicitudProcedimiento solicitudProcedimiento;

	@ManyToOne
	@JoinColumn(name = "causa_externa_id", nullable = false)
	private CausaExterna causaExterna;

	@ManyToOne
	@JoinColumn(name = "finalidad_id", nullable = false)
	private Finalidad finalidad;
	
	@ManyToOne
	@JoinColumn(name = "tipo_catastrofico_id", nullable = false)
	private TipoCatastrofico tipoCatastrofico;
	
	@ManyToOne
	@JoinColumn(name="lateralidad_id", nullable = false)
	private Lateralidad lateralidad;
	
	@Column(name="posologia", nullable=false, length=500)
	private String posologia;
	
	@ManyToOne
	@JoinColumn(name="objetivo_procedimiento_id", nullable = true)
	private ObjetivoProcedimiento objetivoProcedimiento;
	
	@ManyToOne
	@JoinColumn(name="origen_repeticion_id", nullable = true)
	private OrigenRepeticion origenRepeticion;
	
	@ManyToOne
	@JoinColumn(name="tipo_prestacion_id", nullable = true)
	private TipoPrestacion tipoPrestacion;
	
	public Long getId() {
		return id;
	}

	public Lateralidad getLateralidad() {
		return lateralidad;
	}

	public void setLateralidad(Lateralidad lateralidad) {
		this.lateralidad = lateralidad;
	}

	public String getPosologia() {
		return posologia;
	}

	public void setPosologia(String posologia) {
		this.posologia = posologia;
	}

	public ObjetivoProcedimiento getObjetivoProcedimiento() {
		return objetivoProcedimiento;
	}

	public void setObjetivoProcedimiento(ObjetivoProcedimiento objetivoProcedimiento) {
		this.objetivoProcedimiento = objetivoProcedimiento;
	}

	public OrigenRepeticion getOrigenRepeticion() {
		return origenRepeticion;
	}

	public void setOrigenRepeticion(OrigenRepeticion origenRepeticion) {
		this.origenRepeticion = origenRepeticion;
	}

	public TipoPrestacion getTipoPrestacion() {
		return tipoPrestacion;
	}

	public void setTipoPrestacion(TipoPrestacion tipoPrestacion) {
		this.tipoPrestacion = tipoPrestacion;
	}

	public SolicitudProcedimiento getSolicitudProcedimiento() {
		return solicitudProcedimiento;
	}

	public void setSolicitudProcedimiento(
			SolicitudProcedimiento solicitudProcedimiento) {
		this.solicitudProcedimiento = solicitudProcedimiento;
	}

	public CausaExterna getCausaExterna() {
		return causaExterna;
	}

	public void setCausaExterna(CausaExterna causaExterna) {
		this.causaExterna = causaExterna;
	}

	public Finalidad getFinalidad() {
		return finalidad;
	}

	public void setFinalidad(Finalidad finalidad) {
		this.finalidad = finalidad;
	}

	public TipoCatastrofico getTipoCatastrofico() {
		return tipoCatastrofico;
	}

	public void setTipoCatastrofico(TipoCatastrofico tipoCatastrofico) {
		this.tipoCatastrofico = tipoCatastrofico;
	}
	
	public FormulaProcedimientoDto toDto(){
	    FormulaProcedimientoDto dto = new FormulaProcedimientoDto();
	    dto.setCausaExterna(causaExterna.getId());
	    dto.setFinalidad(finalidad.getId());
	    dto.setId(id);
	    dto.setLateralidad(lateralidad.toDto());
	    dto.setObjetivo(objetivoProcedimiento.toDto());
	    dto.setOrigenRepeticion((origenRepeticion != null ) ? origenRepeticion.toDto() : new DescriptivoDto());
	    dto.setPosologia(posologia);
	    dto.setTipoCatastrofico(tipoCatastrofico.getId());
	    dto.setTipoPrestacion(tipoPrestacion.toDto());
	    return dto;
	}
	
	
}
