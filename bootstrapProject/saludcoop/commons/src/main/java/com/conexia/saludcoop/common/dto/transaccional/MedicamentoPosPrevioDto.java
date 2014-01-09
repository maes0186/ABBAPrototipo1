package com.conexia.saludcoop.common.dto.transaccional;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.dto.MedicamentoDto;

public class MedicamentoPosPrevioDto {

	private Long id;
	private MedicamentoDto medicamento;
	private Integer dosis;
	private Integer diasTratamiento;
	private DescriptivoDto respuestaClinicaObservada;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public MedicamentoDto getMedicamento() {
		return medicamento;
	}
	public void setMedicamento(MedicamentoDto medicamento) {
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
	public DescriptivoDto getRespuestaClinicaObservada() {
		return respuestaClinicaObservada;
	}
	public void setRespuestaClinicaObservada(
			DescriptivoDto respuestaClinicaObservada) {
		this.respuestaClinicaObservada = respuestaClinicaObservada;
	}
	
	
}
