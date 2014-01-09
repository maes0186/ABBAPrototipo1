package com.conexia.saludcoop.web.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.InsumoTopeDto;
import com.conexia.saludcoop.common.entity.maestro.InsumoTope;
import com.conexia.saludcoop.common.repository.InsumoTopeRepository;

@Service
@Transactional
public class InsumoTopeManager {

    @Autowired
    InsumoTopeRepository itr;
       
    public InsumoTopeDto findOneByInsumoId(String id){
        InsumoTope insumoTope = itr.findOneByIdInsumo(Long.valueOf(id));
        if(insumoTope != null){
            return insumoTope.toDto();
        } else {
            return null;
        }
    }
}
