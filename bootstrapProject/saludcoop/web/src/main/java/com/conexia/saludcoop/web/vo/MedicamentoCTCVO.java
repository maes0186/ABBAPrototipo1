package com.conexia.saludcoop.web.vo;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.dto.MedicamentoDto;
import com.conexia.saludcoop.common.enumerator.TipoFrecuencia;

public class MedicamentoCTCVO {

    private ProcedimientoMedicamentoVO medicamento;
    private Integer dosis;
    private Integer frecuencia;
    private Integer duracion;
    private String viaAdministracion;
    private String efectosAdversos;
    private String tipoFrecuencia;
    
    public MedicamentoCTCVO(ProcedimientoMedicamentoVO medicamento, Integer dosis, Integer frecuencia, Integer duracion) {
        this.medicamento = medicamento;
        this.dosis = dosis;
        this.frecuencia = frecuencia;
        this.duracion = duracion;
    }
    public MedicamentoCTCVO(MedicamentoDto medicamento, Integer dosis, Integer frecuencia, Integer duracion,
    						DescriptivoDto viaAdministracion, String efectosAdversos, TipoFrecuencia tipoFrecuencia) {
        this.medicamento = new ProcedimientoMedicamentoVO(medicamento);
        this.dosis = dosis;
        this.frecuencia = frecuencia;
        this.duracion = duracion;
        this.viaAdministracion = viaAdministracion.getDescripcion();
        this.efectosAdversos = efectosAdversos;
        this.tipoFrecuencia = tipoFrecuencia.name();
        
    }
    public ProcedimientoMedicamentoVO getMedicamento() {
        return medicamento;
    }
    public void setMedicamento(ProcedimientoMedicamentoVO medicamento) {
        this.medicamento = medicamento;
    }
    public Integer getDosis() {
        return dosis;
    }
    public void setDosis(Integer dosis) {
        this.dosis = dosis;
    }
    public Integer getFrecuencia() {
        return frecuencia;
    }
    public void setFrecuencia(Integer frecuencia) {
        this.frecuencia = frecuencia;
    }
    public Integer getDuracion() {
        return duracion;
    }
    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }
	public String getViaAdministracion() {
		return viaAdministracion;
	}
	public void setViaAdministracion(String viaAdministracion) {
		this.viaAdministracion = viaAdministracion;
	}
	public String getEfectosAdversos() {
		return efectosAdversos;
	}
	public void setEfectosAdversos(String efectosAdversos) {
		this.efectosAdversos = efectosAdversos;
	}
	public String getTipoFrecuencia() {
		return tipoFrecuencia;
	}
	public void setTipoFrecuencia(String tipoFrecuencia) {
		this.tipoFrecuencia = tipoFrecuencia;
	}
}
