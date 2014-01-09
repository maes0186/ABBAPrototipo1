package com.conexia.saludcoop.common.entity.transaccional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.conexia.saludcoop.common.dto.transaccional.InsumoPosPrevioDto;
import com.conexia.saludcoop.common.entity.maestro.Insumo;
import com.conexia.saludcoop.common.entity.maestro.RespuestaClinicaObservada;

@Entity
@Table(name="insumo_pos_previo", schema="transaccional")
public class InsumoPosPrevio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="formulario_ctc_insumo_id", nullable=false)
	private FormularioCTCInsumo formularioCTCInsumo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Insumo insumo;
	
	@Column(name="cantidad", nullable=false)
	private Integer cantidad;
	
	@Column(name="diasTratamiento", nullable=false)
	private Integer diasTratamiento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="respuesta_clinica_observada_id")
	private RespuestaClinicaObservada respuestaClinicaObservada;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Insumo getInsumo() {
		return insumo;
	}

	public void setInsumo(Insumo insumo) {
		this.insumo = insumo;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getDiasTratamiento() {
		return diasTratamiento;
	}

	public void setDiasTratamiento(Integer diasTratamiento) {
		this.diasTratamiento = diasTratamiento;
	}

	public RespuestaClinicaObservada getRespuestaClinicaObservada() {
		return respuestaClinicaObservada;
	}

	public void setRespuestaClinicaObservada(
			RespuestaClinicaObservada respuestaClinicaObservada) {
		this.respuestaClinicaObservada = respuestaClinicaObservada;
	}
	
	
	public FormularioCTCInsumo getFormularioCTCInsumo() {
		return formularioCTCInsumo;
	}

	public void setFormularioCTCInsumo(
			FormularioCTCInsumo formularioCTCInsumo) {
		this.formularioCTCInsumo = formularioCTCInsumo;
	}

	public InsumoPosPrevioDto toDto(){
	    InsumoPosPrevioDto dto =  new InsumoPosPrevioDto();
	    dto.setDiasTratamiento(diasTratamiento);
	    dto.setCantidad(cantidad);
	    dto.setId(id);
	    dto.setRespuestaClinicaObservada(respuestaClinicaObservada.toDto());	    
	    return dto;
	}	

	
}
