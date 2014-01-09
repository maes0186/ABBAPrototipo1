package com.conexia.saludcoop.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.conexia.saludcoop.common.entity.maestro.HistorialVariacionIPC;
import com.conexia.saludcoop.common.repository.custom.ExtentedHistorialVariacionIPCRepository;

public interface HistorialVariacionIPCRepository extends CrudRepository<HistorialVariacionIPC, Long>, 
																ExtentedHistorialVariacionIPCRepository {
    /**
     * Busca el salario IPC para un año específico.
     * 
     * @param anio Año para el cual buscar.
     * @return IPC hallado.
     */
    public HistorialVariacionIPC findOneByAnio(Integer anio);
    
    @Query(" select h from HistorialVariacionIPC h order by h.anio desc")
    public List<HistorialVariacionIPC> findAllOrderByAnio();
    
}
