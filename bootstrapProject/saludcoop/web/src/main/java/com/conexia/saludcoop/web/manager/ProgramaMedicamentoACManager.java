package com.conexia.saludcoop.web.manager;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.entity.maestro.ProgramaMedicamentoAltoCosto;
import com.conexia.saludcoop.common.repository.ProgramaMedicamentoACRepository;

@Service
@Transactional
public class ProgramaMedicamentoACManager{
    @Autowired
    private ProgramaMedicamentoACRepository descRepo;
    @Autowired
    private DescriptivoManagerUtils<ProgramaMedicamentoAltoCosto> descManager;
    
    public List<DescriptivoDto> getAll() {
        Iterable<ProgramaMedicamentoAltoCosto> entidades = descRepo.findAll();
        return descManager.getDtos(entidades);
    }
    public DescriptivoDto findOne(Integer arg0) {
        return descRepo.findOne(arg0).toDto();
    }
    
}
