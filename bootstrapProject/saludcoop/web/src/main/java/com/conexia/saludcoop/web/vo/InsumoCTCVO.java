package com.conexia.saludcoop.web.vo;

import com.conexia.saludcoop.common.dto.InsumoDto;

public class InsumoCTCVO {

    private ProcedimientoMedicamentoVO insumo;
    private Integer cantidad;
    private Integer duracion;
    
    public InsumoCTCVO(ProcedimientoMedicamentoVO insumo, Integer dosis, Integer frecuencia, Integer duracion) {
        this.insumo = insumo;
        this.cantidad = dosis;
        this.duracion = duracion;
    }
    public InsumoCTCVO(InsumoDto insumoDto, Integer cantidad, Integer duracion) {
        this.insumo = new ProcedimientoMedicamentoVO(insumoDto);
        this.cantidad = cantidad;

        this.duracion = duracion;

        
    }
    public ProcedimientoMedicamentoVO getInsumo() {
        return insumo;
    }
    public void setInsumo(ProcedimientoMedicamentoVO insumo) {
        this.insumo = insumo;
    }
    public Integer getDosis() {
        return cantidad;
    }
    public void setDosis(Integer dosis) {
        this.cantidad = dosis;
    }
    public Integer getDuracion() {
        return duracion;
    }
    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }
	
    
}
