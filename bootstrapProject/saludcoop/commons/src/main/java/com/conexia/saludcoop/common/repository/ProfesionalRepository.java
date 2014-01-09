package com.conexia.saludcoop.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.conexia.saludcoop.common.entity.maestro.Profesional;

public interface ProfesionalRepository extends CrudRepository<Profesional, Long>{
	@Query("select p from Profesional p "
			+ "	where p.registroMedico = COALESCE(Cast(?1 as string), p.registroMedico)"
			+ "   and p.identificacionProfesional.id = COALESCE(Cast(?2 as integer), p.identificacionProfesional.id)"
			+ "   and p.numeroIdentificacion = COALESCE(Cast(?3 as string), p.numeroIdentificacion)"
			+ "   and p.primerNombre like ?4"
			+ "   and p.segundoNombre like ?5"
			+ "   and p.primerApellido like ?6"
			+ "   and p.segundoApellido like ?7"
			)
	List<Profesional> findByData(String registroMedico,
			Integer tipoDocumento, String numeroDocumento, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido);
	
	List<Profesional> findByRegistroMedicoOrNumeroIdentificacion(String registroMedico,String NumeroIdentificacion);

	
}
