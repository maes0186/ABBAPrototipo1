package com.conexia.saludcoop.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.conexia.saludcoop.common.entity.maestro.HistorialSMLDV;

/**
 * Repositorio de historial de SMLDV.
 * 
 * @author Sebastián Matienzo
 */
public interface HistorialSMLDVRepository extends CrudRepository<HistorialSMLDV, Long> {
	
	/**
	 * Busca el salario SMLDV para un año específico.
	 * 
	 * @param anio Año para el cual buscar.
	 * @return Salario hallado.
	 */
    public HistorialSMLDV findOneByAnio(Integer anio);
    
    @Query(" select h from HistorialSMLDV h order by h.anio desc")
    public List<HistorialSMLDV> findAllOrderByAnio();
    
}
