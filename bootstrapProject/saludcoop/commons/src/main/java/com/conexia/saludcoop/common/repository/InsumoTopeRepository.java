package com.conexia.saludcoop.common.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.conexia.saludcoop.common.entity.maestro.InsumoTope;

public interface InsumoTopeRepository extends CrudRepository<InsumoTope, Long>  {

    @Query(" select i from InsumoTope i JOIN i.insumo m where m.codigo = ?")
    public InsumoTope findOneByCodigoInsumo(String codigo);
    
    @Query(" select i from InsumoTope i JOIN i.insumo m where m.id = ?")
    public InsumoTope findOneByIdInsumo(Long id);
}
