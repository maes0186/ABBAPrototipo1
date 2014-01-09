package com.conexia.saludcoop.web.vo;

import com.conexia.saludcoop.common.dto.transaccional.MedicamentoPosPrevioDto;

public class MedicamentoPosPrevioVO {
    private DescriptivoVO respuestaClinicaObservada;
    private ProcedimientoMedicamentoVO medicamento;
    private Long id;
    private Integer dosis;
    private Integer diasTratamiento;

    public MedicamentoPosPrevioVO(DescriptivoVO respuestaClinicaObservada, ProcedimientoMedicamentoVO medicamento, Long id, Integer dosis,
            Integer diasTratamiento) {
        this.respuestaClinicaObservada = respuestaClinicaObservada;
        this.medicamento = medicamento;
        this.id = id;
        this.dosis = dosis;
        this.diasTratamiento = diasTratamiento;
    }

    public MedicamentoPosPrevioVO(MedicamentoPosPrevioDto dto) {
        this.respuestaClinicaObservada = new DescriptivoVO(dto.getRespuestaClinicaObservada());
        this.medicamento = new ProcedimientoMedicamentoVO(dto.getMedicamento());
        this.id = dto.getId();
        this.dosis = dto.getDosis();
        this.diasTratamiento = dto.getDiasTratamiento();
    }

    public DescriptivoVO getRespuestaClinicaObservada() {
        return respuestaClinicaObservada;
    }

    public void setRespuestaClinicaObservada(DescriptivoVO respuestaClinicaObservada) {
        this.respuestaClinicaObservada = respuestaClinicaObservada;
    }

    public ProcedimientoMedicamentoVO getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(ProcedimientoMedicamentoVO medicamento) {
        this.medicamento = medicamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
