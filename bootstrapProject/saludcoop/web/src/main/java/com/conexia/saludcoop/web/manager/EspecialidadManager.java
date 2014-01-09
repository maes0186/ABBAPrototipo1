package com.conexia.saludcoop.web.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.dto.EspecialidadDto;
import com.conexia.saludcoop.common.entity.maestro.Especialidad;
import com.conexia.saludcoop.common.repository.EspecialidadRepository;
@Service
@Transactional
public class EspecialidadManager extends GeneralManager {

	@Autowired
	private EspecialidadRepository repo; 
	@Autowired
	private DescriptivoManagerUtils<Especialidad> descManager;

	public EspecialidadDto findOne(Integer id){
		return repo.findOne(id).toEspecialidadDto();
	}

	public List<DescriptivoDto> getAll() {
		
		Iterable<Especialidad> especialidades = repo.findAll(new Sort(Sort.Direction.ASC, "descripcion"));
		return descManager.getDtos(especialidades);
	}
	
public List<EspecialidadDto> getEspecialidadByCodigoDescripcion(String codigo, String descripcion) {
		List<Especialidad> especialidades = repo.findDistinctByCodigoLikeAndDescripcionLikeAllIgnoreCase(getLikeParameter(codigo), getLikeParameter(descripcion));
		List<EspecialidadDto> especialidadesDTO = new ArrayList<>();
		for (Especialidad especialidad : especialidades) {
			especialidadesDTO.add(toEspecialidadDto(especialidad));
		}
	
		return especialidadesDTO;

	}

public List<EspecialidadDto> getEspecialidadByCodigo(String codigo) {
	List<Especialidad> especialidades = repo.findByCodigo(codigo);
	List<EspecialidadDto> especialidadesDTO = new ArrayList<>();
	for (Especialidad especialidad : especialidades) {
		especialidadesDTO.add(toEspecialidadDto(especialidad));
	}

	return especialidadesDTO;

}

private EspecialidadDto toEspecialidadDto(Especialidad especialidad){
	EspecialidadDto dto= new EspecialidadDto();
	dto.setId(especialidad.getId());
	dto.setCodigo(especialidad.getCodigo());
	dto.setDescripcion(especialidad.getDescripcion());
	return dto;
}

	public List<EspecialidadDto> buscarEspecialidadesPorMedico(
			Long idProfesional) {
		List<Especialidad> especialidades = repo
				.findByProfesional(idProfesional);
		List<EspecialidadDto> especialidadesDTO = new ArrayList<>();
		for (Especialidad especialidad : especialidades) {
			especialidadesDTO.add(toEspecialidadDto(especialidad));
		}
		return especialidadesDTO;
	}
	
}
