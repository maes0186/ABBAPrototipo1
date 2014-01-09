package com.conexia.saludcoop.web.vo;

import com.conexia.saludcoop.common.dto.transaccional.InsumoPosPrevioDto;

public class InsumoPosPrevioVO {
    private DescriptivoVO respuestaClinicaObservada;
    private ProcedimientoMedicamentoVO medicamento;
    private Long id;
    private Integer cantidad;
    private Integer diasTratamiento;

    public InsumoPosPrevioVO(DescriptivoVO respuestaClinicaObservada, ProcedimientoMedicamentoVO medicamento, Long id, Integer dosis,
            Integer diasTratamiento) {
        this.respuestaClinicaObservada = respuestaClinicaObservada;
        this.medicamento = medicamento;
        this.id = id;
        this.cantidad = dosis;
        this.diasTratamiento = diasTratamiento;
    }

    public InsumoPosPrevioVO(InsumoPosPrevioDto insumoPosPrevioDto) {
        this.respuestaClinicaObservada = new DescriptivoVO(insumoPosPrevioDto.getRespuestaClinicaObservada());
        this.medicamento = new ProcedimientoMedicamentoVO(insumoPosPrevioDto.getInsumo());
        this.id = insumoPosPrevioDto.getId();
        this.cantidad = insumoPosPrevioDto.getCantidad();
        this.diasTratamiento = insumoPosPrevioDto.getDiasTratamiento();
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
        return cantidad;
    }

    public void setDosis(Integer dosis) {
        this.cantidad = dosis;
    }

    public Integer getDiasTratamiento() {
        return diasTratamiento;
    }

    public void setDiasTratamiento(Integer diasTratamiento) {
        this.diasTratamiento = diasTratamiento;
    }
}
