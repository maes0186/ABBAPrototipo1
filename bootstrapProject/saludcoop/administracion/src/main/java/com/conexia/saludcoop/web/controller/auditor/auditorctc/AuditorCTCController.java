package com.conexia.saludcoop.web.controller.auditor.auditorctc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.conexia.saludcoop.common.dto.SolicitudCTCDto;
import com.conexia.saludcoop.web.BaseValidatingController;
import com.conexia.saludcoop.web.manager.DepartamentoManager;
import com.conexia.saludcoop.web.manager.EpsManager;
import com.conexia.saludcoop.web.manager.EstadoAfiliacionManager;
import com.conexia.saludcoop.web.manager.TipoIdentificacionAfiliadoManager;

/**
 * 
 * @author mortega
 * 
 */
@Controller
public class AuditorCTCController extends BaseValidatingController {

    @Autowired
    private TipoIdentificacionAfiliadoManager tipoIdentAfiliadoManager;

    @Autowired
    private EstadoAfiliacionManager estadoAfiliacionManager;

    @Autowired
    private EpsManager epsManager;

    @Autowired
    private DepartamentoManager departamentoManager;

    private int sEcho = 0;

    private List<SolicitudCTCDto> lista;
    
    private String numSol;
    
    public void filtrar() {
        lista = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            SolicitudCTCDto dto = new SolicitudCTCDto();
            dto.setNumeroSolicitud(i);
            dto.setAfiliado(numSol + i);
            lista.add(dto);
        }
    }
    
    public List<SolicitudCTCDto> getLista() {
        if(lista == null) {
            lista = new ArrayList<>();
            for (int i = 0; i < 30; i++) {
                SolicitudCTCDto dto = new SolicitudCTCDto();
                dto.setNumeroSolicitud(i);
                dto.setAfiliado("Pepito " + i);
                lista.add(dto);
            }
        }
        return lista;
    }

    public void setLista(List<SolicitudCTCDto> lista) {
        this.lista = lista;
    }

    public String getNumSol() {
        return numSol;
    }

    public void setNumSol(String numSol) {
        this.numSol = numSol;
    }

}
