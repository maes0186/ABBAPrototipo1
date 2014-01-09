package com.conexia.saludcoop.web.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.entity.maestro.CriterioNegacion;
import com.conexia.saludcoop.common.repository.CriterioNegacionRepository;

@Service
@Transactional
public class CriterioNegacionManager {
    @Autowired
    private CriterioNegacionRepository descRepo;
    @Autowired
    private DescriptivoManagerUtils<CriterioNegacion> descManager;

    public List<DescriptivoDto> getAll() {
        Iterable<CriterioNegacion> entidades = descRepo.findAll();
        return descManager.getDtos(entidades);
    }

    public DescriptivoDto findOne(Integer arg0) {
        return descRepo.findOne(arg0).toDto();
    }
}
