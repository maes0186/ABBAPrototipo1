package com.conexia.saludcoop.web.vo;

import com.conexia.saludcoop.common.dto.transaccional.ProcedimientoPosPrevioDto;

public class ProcedimientoPosPrevioVO {
	private Long id;
	private DescriptivoVO respuestaClinicaObservada;
	private ProcedimientoMedicamentoVO procedimiento;
	
	public ProcedimientoPosPrevioVO(ProcedimientoPosPrevioDto dto){
	    this.id = dto.getId();
	    this.respuestaClinicaObservada = new DescriptivoVO(dto.getRespuestaClinicaObservada());
	    this.procedimiento = new ProcedimientoMedicamentoVO(dto.getProcedimiento());
	}
	public ProcedimientoPosPrevioVO(DescriptivoVO respuestaClinicaObservada, ProcedimientoMedicamentoVO procedimiento) {
		this.respuestaClinicaObservada = respuestaClinicaObservada;
		this.procedimiento = procedimiento;
	}
	public DescriptivoVO getRespuestaClinicaObservada() {
		return respuestaClinicaObservada;
	}
	public void setRespuestaClinicaObservada(DescriptivoVO respuestaClinicaObservada) {
		this.respuestaClinicaObservada = respuestaClinicaObservada;
	}
	public ProcedimientoMedicamentoVO getProcedimiento() {
		return procedimiento;
	}
	public void setProcedimiento(ProcedimientoMedicamentoVO procedimiento) {
		this.procedimiento = procedimiento;
	}
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
	
}
