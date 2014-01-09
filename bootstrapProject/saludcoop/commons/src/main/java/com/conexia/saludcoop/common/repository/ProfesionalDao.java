package com.conexia.saludcoop.common.repository;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.crud.ProfesionalItemVO;
import com.conexia.saludcoop.common.entity.maestro.Profesional;
import com.conexia.saludcoop.common.util.Pagination;
@Component
@Transactional
public interface ProfesionalDao {

	public List<Profesional> findBySede( String registroMedico, Integer especialidad,
			Integer tipoDocumento, String numeroDocumento, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, Long sedeId);
	
	public List<ProfesionalItemVO> getProfesionales(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, Integer tipoDocumento, String numeroDocumento, Pagination pagination, String resgitroMedico);
	
}
