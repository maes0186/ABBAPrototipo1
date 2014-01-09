/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexia.saludcoop.administracion.contratos.dtos;

import com.conexia.saludcoop.common.dto.EspecialidadDto;

/**
 *
 * @author rcarbonell
 */
public class EspecialidadContratoDto extends EspecialidadDto{
    
    private Boolean checked;
    private double tarifaExcepcion;

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public double getTarifaExcepcion() {
        return tarifaExcepcion;
    }

    public void setTarifaExcepcion(double tarifaExcepcion) {
        this.tarifaExcepcion = tarifaExcepcion;
    }
    
    
}
