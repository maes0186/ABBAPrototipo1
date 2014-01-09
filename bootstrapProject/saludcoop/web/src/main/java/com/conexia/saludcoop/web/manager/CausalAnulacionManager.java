package com.conexia.saludcoop.web.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.entity.maestro.CausalAnulacion;
import com.conexia.saludcoop.common.repository.CausalAnulacionRepository;

@Service
@Transactional
public class CausalAnulacionManager {
    @Autowired
    private CausalAnulacionRepository descRepo;
    @Autowired
    private DescriptivoManagerUtils<CausalAnulacion> descManager;

    public List<DescriptivoDto> getAll() {
        Iterable<CausalAnulacion> entidades = descRepo.findAll();
        return descManager.getDtos(entidades);
    }

    public DescriptivoDto findOne(Integer arg0) {
        return descRepo.findOne(arg0).toDto();
    }
}
