package com.conexia.saludcoop.web.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.HistorialVariacionIPCDto;
import com.conexia.saludcoop.common.entity.maestro.HistorialVariacionIPC;
import com.conexia.saludcoop.common.repository.HistorialVariacionIPCRepository;

/**
 * @author dprieto 
 */
@Service
@Transactional
public class HistorialIPCManager {
    
    @Autowired
    private HistorialVariacionIPCRepository hvir;
    
    public List<HistorialVariacionIPCDto> findAllOrderByAnio() {
        Iterable<HistorialVariacionIPC> vals=hvir.findAllOrderByAnio();
        List<HistorialVariacionIPCDto> dtos= new ArrayList<HistorialVariacionIPCDto>();
        for (HistorialVariacionIPC historial : vals) {
            dtos.add(historial.toDto());
        }
        return dtos;
    }


    public void addVariacionIPC(Double valor, Integer anio){
        HistorialVariacionIPC nuevoRegistro = new HistorialVariacionIPC();
        nuevoRegistro.setValor(valor);
        nuevoRegistro.setAnio(anio);
        hvir.save(nuevoRegistro);
    }
    
    public HistorialVariacionIPCDto findByAnio(Integer anio){
        HistorialVariacionIPC a = hvir.findOneByAnio(anio);
        if(a != null){
            return a.toDto();
        } else {
            return null;
        }
    }    
    
    public HistorialVariacionIPCDto findById(Long id){
        HistorialVariacionIPC h = hvir.findOne(id);
        return h.toDto();
    }
    
    public void removeVariacionIPC(Long id){
        hvir.delete(id);
    }
    
    public void editVariacionIPC (Long id, Double valor, Integer anio){
        HistorialVariacionIPC historial = hvir.findOne(id);
        historial.setValor(valor);
        historial.setAnio(anio);
        hvir.save(historial);
    }
}
