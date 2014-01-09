package com.conexia.saludcoop.common.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.conexia.saludcoop.common.entity.maestro.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long>{
	
	public Page<Departamento> findByDescripcionContaining(String descripcion, Pageable pageRequest);
//	@Query("SELECT COUNT(d) FROM Departamento d") 
//	long countAllDepartamentos();
}
