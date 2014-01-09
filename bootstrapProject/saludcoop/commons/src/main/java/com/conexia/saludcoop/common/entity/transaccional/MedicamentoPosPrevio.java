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

import com.conexia.saludcoop.common.dto.transaccional.MedicamentoPosPrevioDto;
import com.conexia.saludcoop.common.entity.maestro.Medicamento;
import com.conexia.saludcoop.common.entity.maestro.RespuestaClinicaObservada;

@Entity
@Table(name="medicamento_pos_previo", schema="transaccional")
public class MedicamentoPosPrevio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="formulario_ctc_medicamento_id", nullable=false)
	private FormularioCTCMedicamento formularioCTCMedicamento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Medicamento medicamento;
	
	@Column(name="dosis", nullable=false)
	private Integer dosis;
	
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

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public Integer getDosis() {
		return dosis;
	}

	public void setDosis(Integer dosis) {
		this.dosis = dosis;
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
	
	
	public FormularioCTCMedicamento getFormularioCTCMedicamento() {
		return formularioCTCMedicamento;
	}

	public void setFormularioCTCMedicamento(
			FormularioCTCMedicamento formularioCTCMedicamento) {
		this.formularioCTCMedicamento = formularioCTCMedicamento;
	}

	public MedicamentoPosPrevioDto toDto(){
	    MedicamentoPosPrevioDto dto =  new MedicamentoPosPrevioDto();
	    dto.setDiasTratamiento(diasTratamiento);
	    dto.setDosis(dosis);
	    dto.setId(id);
	    dto.setMedicamento(medicamento.toDto());
	    dto.setRespuestaClinicaObservada(respuestaClinicaObservada.toDto());	    
	    return dto;
	}	

	
}
