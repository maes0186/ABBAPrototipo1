package com.conexia.saludcoop.common.repository;

import org.springframework.data.repository.CrudRepository;

import com.conexia.saludcoop.common.entity.maestro.ProfesionalEspecialidad;

public interface ProfesionalEspecialidadRepository extends CrudRepository<ProfesionalEspecialidad, Long> {


	public ProfesionalEspecialidad findOneByProfesionalIdAndEspecialidadId(Long profesionalId, Integer especialidadId);
//	@Query("select p from ProfesionalEspecialidad p "
//			+ "	where p.pk.profesional.registroMedico = COALESCE(Cast(?1 as string), p.pk.profesional.registroMedico)"
//			+ "   and p.pk.profesional.identificacionProfesional.id = COALESCE(Cast(?2 as int), p.pk.profesional.identificacionProfesional.id)"
//			+ "   and p.pk.profesional.numeroIdentificacion = COALESCE(Cast(?3 as string), p.pk.profesional.numeroIdentificacion)"
//			+ "   and p.pk.profesional.primerNombre like ?4"
//			+ "   and p.pk.profesional.segundoNombre like ?5"
//			+ "   and p.pk.profesional.primerApellido like ?6"
//			+ "   and p.pk.profesional.segundoApellido like ?7"
//			+ "   and p.pk.especialidad.id = COALESCE(Cast(?8 as int), p.pk.especialidad.id)"
//			
//			)
//	public List<ProfesionalEspecialidad> findByProfesional(String registroMedico,
//			Integer tipoDocumento, String numeroDocumento, 
//			String primerNombre, String segundoNombre, String primerApellido, String segundoApellido,
//			Integer especialidad);
//	
//	@Query("select p from ProfesionalEspecialidad p"
//			+ "	where p.pk.profesional.registroMedico = COALESCE(Cast(?1 as string), p.pk.profesional.registroMedico)"
//			+ "   and p.pk.profesional.identificacionProfesional.id = COALESCE(Cast(?2 as int4), p.pk.profesional.identificacionProfesional.id)"
//			+ "   and p.pk.profesional.numeroIdentificacion = COALESCE(Cast(?3 as string), p.pk.profesional.numeroIdentificacion)"
//			+ "   and p.pk.profesional.primerNombre like ?4"
//			+ "   and p.pk.profesional.segundoNombre like ?5"
//			+ "   and p.pk.profesional.primerApellido like ?6"
//			+ "   and p.pk.profesional.segundoApellido like ?7" 
//			+ "   and p.pk.especialidad.id = COALESCE(Cast(?8 as int4), p.pk.especialidad.id)"  
//			+ "   and ?9 member p.sedes ")
//	
//	public List<ProfesionalEspecialidad> findBySedeIps(String registroMedico,
//			Integer tipoDocumento, String numeroDocumento, 
//			String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, Integer especialidad, SedeIps sedeIps);
//	

	
}
