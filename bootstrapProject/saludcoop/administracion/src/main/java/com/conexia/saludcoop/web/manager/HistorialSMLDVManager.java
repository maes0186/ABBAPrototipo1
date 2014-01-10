package com.conexia.saludcoop.web.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.HistorialSMLDVDto;
import com.conexia.saludcoop.common.entity.maestro.HistorialSMLDV;
import com.conexia.saludcoop.common.repository.HistorialSMLDVRepository;

/**
 * @author dprieto 
 */
@Service
@Transactional
public class HistorialSMLDVManager {
    
    @Autowired
    private HistorialSMLDVRepository hsr;
    
    public List<HistorialSMLDVDto> findAllOrderByAnio() {
        Iterable<HistorialSMLDV> vals=hsr.findAllOrderByAnio();
        List<HistorialSMLDVDto> dtos= new ArrayList<HistorialSMLDVDto>();
        for (HistorialSMLDV historialSMLDV : vals) {
            dtos.add(historialSMLDV.toDto());
        }
        return dtos;
    }


    public void addSMLDV(Double valor, Integer anio){
        HistorialSMLDV nuevoRegistro = new HistorialSMLDV();
        nuevoRegistro.setValor(valor);
        nuevoRegistro.setAnio(anio);
        hsr.save(nuevoRegistro);
    }
    
    public HistorialSMLDVDto findByAnio(Integer anio){
        HistorialSMLDV a = hsr.findOneByAnio(anio);
        if(a != null){
            return a.toDto();
        } else {
            return null;
        }
    }    
    
    public HistorialSMLDVDto findById(Long id){
        HistorialSMLDV h = hsr.findOne(id);
        return h.toDto();
    }
    
    public void removeSMLDV (Long id){
        hsr.delete(id);
    }
    
    public void editSMLDV (Long id, Double valor, Integer anio){
        HistorialSMLDV historial = hsr.findOne(id);
        historial.setValor(valor);
        historial.setAnio(anio);
        hsr.save(historial);
    }
}
