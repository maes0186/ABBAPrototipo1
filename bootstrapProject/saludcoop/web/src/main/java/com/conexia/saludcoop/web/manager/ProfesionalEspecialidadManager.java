package com.conexia.saludcoop.web.manager;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.ProfesionalEspecialidadDto;
import com.conexia.saludcoop.common.dto.SedeIpsDto;
import com.conexia.saludcoop.common.entity.maestro.ProfesionalEspecialidad;
import com.conexia.saludcoop.common.repository.ProfesionalEspecialidadDaoImpl;
import com.conexia.saludcoop.common.repository.ProfesionalEspecialidadRepository;
import com.conexia.saludcoop.common.repository.SedeIpsRepository;
import com.conexia.saludcoop.web.form.SeachProfesionalForm;
@Repository
@Transactional
public class ProfesionalEspecialidadManager extends GeneralManager{

	@Autowired
	private ProfesionalEspecialidadRepository profesionalEspecialidadRepo;

	@Autowired
	private ProfesionalEspecialidadDaoImpl profesionalEspecialidadDao;
	
	@Autowired
	private SedeIpsRepository sedeIpsRepo;

	public ProfesionalEspecialidadDto findOne(Long id) {
		
		ProfesionalEspecialidad pEntity = profesionalEspecialidadRepo.findOne(id);
		ProfesionalEspecialidadDto pDto = null;
		if(pEntity!= null){
			pDto = pEntity.toDto();
		}
		return pDto;
		
		
		
	}

	public List<ProfesionalEspecialidadDto> getProfesionales(SeachProfesionalForm form) {
		List<ProfesionalEspecialidad> profesionales = new Vector<>();
		List<ProfesionalEspecialidadDto> profesionalesDto = new Vector<>();
//		profesionales =
//				profesionalEspecialidadRepo.findByProfesional(
//						  getLikeParameter(form.getRegistroMedico()), 
//						  form.getTipoDocumento(), 
//						  getLikeParameter(form.getNumeroDocumento()), 
//						  getLikeParameter(form.getPrimerNombre()), 
//						  getLikeParameter(form.getSegundoNombre()),
//						  getLikeParameter(form.getPrimerApellido()), 
//						  getLikeParameter(form.getSegundoApellido()),
//											  form.getEspecialidad());

				for (ProfesionalEspecialidad profesional : profesionales) {
					profesionalesDto.add(profesional.toDto());
				}

				return profesionalesDto;
	}

	public List<ProfesionalEspecialidadDto> getProfesionalesBySede(SedeIpsDto sedeIps, SeachProfesionalForm form) {

		List<ProfesionalEspecialidadDto> profesionalesDtos = new Vector<>();
		List<ProfesionalEspecialidad> profesionalesEncontrados = profesionalEspecialidadDao
				.findBySedeIps(form.getRegistroMedico(), 
						  form.getTipoDocumento(), 
						  form.getNumeroDocumento(), 
						  form.getPrimerNombre(), 
						  form.getSegundoNombre(),
						  form.getPrimerApellido(), 
						  form.getSegundoApellido(),
						  form.getEspecialidad(),
						  sedeIps.getId());

		for (ProfesionalEspecialidad profesional : profesionalesEncontrados) {
			profesionalesDtos.add(profesional.toDto());
		}

		return profesionalesDtos;
	}

}
