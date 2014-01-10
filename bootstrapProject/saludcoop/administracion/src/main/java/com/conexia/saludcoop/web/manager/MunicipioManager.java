package com.conexia.saludcoop.web.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.MunicipioDto;
import com.conexia.saludcoop.common.entity.maestro.Municipio;
import com.conexia.saludcoop.common.repository.MunicipioRepository;

/**
 * Manager de Departamentos
 * @author rcarbonell
 *
 */
@Service
@Transactional
public class MunicipioManager {

    @Autowired
    private MunicipioRepository repo;
    
    public List<MunicipioDto> findAll(){
        Iterable<Municipio> vals=repo.findAll();
        List<MunicipioDto> dtoList = new ArrayList<MunicipioDto>();
        for (Municipio municipio : vals) {
            dtoList.add(municipio.toDto());
        }
        return dtoList;
    }
    
    public List<MunicipioDto> findByDepartamentoId(Long idDepartamento){
        Iterable<Municipio> vals=repo.findMunicipiosByDepartamentoId(idDepartamento, new Sort(Sort.Direction.ASC, "descripcion"));
        List<MunicipioDto> dtoList = new ArrayList<MunicipioDto>();
        for (Municipio municipio : vals) {
            dtoList.add(municipio.toDto());
        }
        return dtoList;
    }
}
