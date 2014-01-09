package com.conexia.saludcoop.common.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.conexia.saludcoop.common.entity.maestro.Municipio;

public interface MunicipioRepository extends PagingAndSortingRepository<Municipio, Long>{
    
    @Query
    public List<Municipio> findMunicipiosByDepartamentoId(Long id, Sort sort);
    
}
