/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexia.saludcoop.administracion.contratos.dtos;

import com.conexia.saludcoop.common.dto.ServicioDto;
import com.conexia.saludcoop.common.dto.TarifarioExcepcionDto;
import java.io.Serializable;

/**
 *
 * @author prodas
 */
public class ServicioContratoDto extends ServicioDto implements Serializable {

    private boolean checked;
    private TarifarioExcepcionDto tarifario;
    private Integer idTarifario;
    
    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public TarifarioExcepcionDto getTarifario() {
        if(tarifario == null) {
            tarifario = new TarifarioExcepcionDto();
        }
        return tarifario;
    }

    public void setTarifario(TarifarioExcepcionDto tarifario) {
        this.tarifario = tarifario;
    }

    public Integer getIdTarifario() {
        return idTarifario;
    }

    public void setIdTarifario(Integer idTarifario) {
        this.idTarifario = idTarifario;
    }
}
