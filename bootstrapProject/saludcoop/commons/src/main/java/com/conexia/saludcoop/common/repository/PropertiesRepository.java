package com.conexia.saludcoop.common.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.conexia.saludcoop.common.entity.configuration.Properties;

public interface PropertiesRepository extends CrudRepository<Properties, Long>{
	
	@Query
	public Properties findOneByClaveAndAplicacion(String clave, String aplicacion);
	
}