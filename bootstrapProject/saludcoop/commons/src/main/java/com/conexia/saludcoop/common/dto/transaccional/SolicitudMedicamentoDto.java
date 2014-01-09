package com.conexia.saludcoop.common.dto.transaccional;

import com.conexia.saludcoop.common.dto.MedicamentoDto;


public class SolicitudMedicamentoDto { 
	private Long id;
	private MedicamentoDto medicamento;
	private FormularioCTCMedicamentoDto formCTCMedicamento;
	private FormulaMedicamentoDto formulaMedicamento;

	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}
	public void setMedicamento(MedicamentoDto medicamento){
		this.medicamento = medicamento;
	}

	public MedicamentoDto getMedicamento(){
		return this.medicamento;
	}
	public void setFormCTCMedicamento(FormularioCTCMedicamentoDto formCTCMedicamento){
		this.formCTCMedicamento = formCTCMedicamento;
	}

	public FormularioCTCMedicamentoDto getFormCTCMedicamento(){
		return this.formCTCMedicamento;
	}
	public void setFormulaMedicamento(FormulaMedicamentoDto formulaMedicamento){
		this.formulaMedicamento = formulaMedicamento;
	}

	public FormulaMedicamentoDto getFormulaMedicamento(){
		return this.formulaMedicamento;
	}

}
