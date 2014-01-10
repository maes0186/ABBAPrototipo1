package com.conexia.saludcoop.web.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.crud.ProfesionalItemVO;
import com.conexia.saludcoop.common.dto.EspecialidadDto;
import com.conexia.saludcoop.common.dto.ProfesionalDto;
import com.conexia.saludcoop.common.dto.SedeIpsDto;
import com.conexia.saludcoop.common.entity.maestro.Especialidad;
import com.conexia.saludcoop.common.entity.maestro.EstadoProfesional;
import com.conexia.saludcoop.common.entity.maestro.Profesional;
import com.conexia.saludcoop.common.entity.maestro.ProfesionalEspecialidad;
import com.conexia.saludcoop.common.entity.maestro.ProfesionalEspecialidadSedeIps;
import com.conexia.saludcoop.common.entity.maestro.SedeIps;
import com.conexia.saludcoop.common.entity.maestro.TipoIdentificacion;
import com.conexia.saludcoop.common.repository.EspecialidadRepository;
import com.conexia.saludcoop.common.repository.EstadoProfesionalRepository;
import com.conexia.saludcoop.common.repository.ProfesionalDao;
import com.conexia.saludcoop.common.repository.ProfesionalEspecialidadRepository;
import com.conexia.saludcoop.common.repository.ProfesionalEspecialidadSedeIpsRepository;
import com.conexia.saludcoop.common.repository.ProfesionalRepository;
import com.conexia.saludcoop.common.repository.SedeIpsRepository;
import com.conexia.saludcoop.common.repository.TipoIdentificacionRepository;
import com.conexia.saludcoop.common.util.Pagination;
import com.conexia.saludcoop.web.form.FiltroCrudForm;
import com.conexia.saludcoop.web.vo.PageVO;

@Repository
@Transactional
public class ProfesionalManager extends SpringDataManager<Profesional, ProfesionalDto> {
	@Autowired
	private ProfesionalRepository profesionalRepo;
	@Autowired 
	private SedeIpsRepository ipsRepo;
	@Autowired
	private EspecialidadRepository espeRepo;
	
	@Autowired
	private ProfesionalDao profesionalDao;
	
	@Autowired
	private ProfesionalEspecialidadRepository profesionalEspecialidadRepository;
	@Autowired
	private TipoIdentificacionRepository tir;
	
	@Autowired
	private EstadoProfesionalRepository estadoProfesionalRepository;
	
	@Autowired
	private ProfesionalEspecialidadSedeIpsRepository profesionalEspecialidadSedeIpsRepository;

	public List<ProfesionalDto> findByRegistroMedicoOrNumeroIdentificacion(
			String registroMedico, String NumeroIdentificacion) {
		List<ProfesionalDto> profesionalDtos = new ArrayList<>();
		List<Profesional> profesionalesEncontrados = profesionalRepo
				.findByRegistroMedicoOrNumeroIdentificacion(registroMedico,
						NumeroIdentificacion);
		for (Profesional profesional : profesionalesEncontrados) {
			profesionalDtos.add(profesional.toDto());
		}

		return profesionalDtos;
	}
	
	
	public List<ProfesionalDto> getProfesionales(String registroMedico, Integer tipoDocumento, String numeroDocumento, String primerNombre, String segundoNombre, String primerApellido,
			String segundoApellido) {

		List<ProfesionalDto> profesionalDtos = new Vector<>();
		List<Profesional> profesionalesEncontrados = profesionalRepo
				.findByData(
						getLikeParameter(registroMedico), tipoDocumento, getLikeParameter(numeroDocumento), getLikeParameter(primerNombre), getLikeParameter(segundoNombre),
						getLikeParameter(primerApellido), getLikeParameter(segundoApellido));

		for (Profesional profesional : profesionalesEncontrados) {
			profesionalDtos.add(profesional.toDto());
		}

		return profesionalDtos;

	}

	@Override
	public ProfesionalDto findOne(Long id) {
		ProfesionalDto p = new ProfesionalDto();
		Profesional pEnt = this.profesionalRepo.findOne(id);
		if (pEnt != null) {
			p = pEnt.toDto();
			if(pEnt.getIdentificacionProfesional()!=null&&pEnt.getIdentificacionProfesional().getId()!=null){
				p.setTipoIdentificacion(String.valueOf(pEnt.getIdentificacionProfesional().getId()));
			}
		}
		return p;
	}

	@Override
	public List<ProfesionalDto> findAll() {
		Iterable<Profesional> entidades = this.profesionalRepo.findAll();
		return this.toDtos(entidades);
	}

	@Override
	public List<ProfesionalDto> toDtos(Iterable<Profesional> entidades) {

		List<ProfesionalDto> profesionales = new Vector<>();
		if (entidades != null) {
			for (Profesional profesional : entidades) {
				profesionales.add(profesional.toDto());
			}
		}
		return profesionales;
	}

	public List<ProfesionalDto> getProfesionalesBySede(SedeIpsDto sedeIps, String registroMedico, Integer especialidad, Integer tipoDocumento, 
														String numeroDocumento, String primerNombre, String segundoNombre,
			String primerApellido, String segundoApellido) {
		List<ProfesionalDto> profesionalDtos = new Vector<>();
		List<Profesional> profesionalesEncontrados = profesionalDao
				.findBySede(
						getNullable(registroMedico), especialidad, tipoDocumento, getNullable(numeroDocumento), getLikeParameter(primerNombre), getLikeParameter(segundoNombre),
						getLikeParameter(primerApellido), getLikeParameter(segundoApellido),sedeIps.getId() );

		for (Profesional profesional : profesionalesEncontrados) {
			profesionalDtos.add(profesional.toDto());
		}

		return profesionalDtos;
	}
	
	public PageVO<ProfesionalItemVO> getProfesionalesProj(FiltroCrudForm form) {
		Pagination pagination = new Pagination(form.getActualPage(), 10);
		List<ProfesionalItemVO> profesionales = this.profesionalDao.getProfesionales(form.getPrimerNombre(),form.getSegundoNombre(),form.getPrimerApellido(),form.getSegundoApellido(),form.getTipoDocumento(),form.getNumeroDocumento(),pagination,form.getRegistroMedico());
		PageVO<ProfesionalItemVO> paginaBandeja = new PageVO<>(profesionales, pagination.getTotalPages(), profesionales.size(), pagination.getActualPage());
		return paginaBandeja;
	}
	
	/**
	 * 
	 * @param dto
	 */
	public void guardarProfesional(ProfesionalDto dto, SedeIpsDto ipsDto,
			EspecialidadDto especialidadDto) {
		Profesional profesional = null;
		if (dto.getId() != null) {
			profesional = profesionalRepo.findOne(dto.getId());
			profesional = toProfesional(dto, profesional);
			if (ipsDto != null) {
				SedeIps sedeIps = ipsRepo.findOne(ipsDto.getId());
				Especialidad especialidad = espeRepo.findOne(especialidadDto
						.getId());
				ProfesionalEspecialidad profesionalEspecialidad = profesionalEspecialidadRepository
						.findOneByProfesionalIdAndEspecialidadId(dto.getId(),
								especialidadDto.getId());
				if (profesionalEspecialidad == null) {
					profesionalEspecialidad = new ProfesionalEspecialidad();
					// Activo
					EstadoProfesional estadoProfesional = estadoProfesionalRepository
							.findOne(4);
					profesionalEspecialidad
							.setEstadoProfesional(estadoProfesional);
					profesionalEspecialidad.setEspecialidad(especialidad);
					profesionalEspecialidad.setProfesional(profesional);
					profesionalEspecialidad.setFechaInsert(new Date());
					profesionalEspecialidad.setFechaUpdate(new Date());
					profesionalEspecialidadRepository
							.save(profesionalEspecialidad);
				}

				ProfesionalEspecialidadSedeIps profesionalEspecialidadSedeIps = profesionalEspecialidadSedeIpsRepository
						.busquedaPorEspecialidadSedeIps(
								profesionalEspecialidad.getId(), ipsDto.getId());

				if (profesionalEspecialidadSedeIps == null) {
					profesionalEspecialidadSedeIps = new ProfesionalEspecialidadSedeIps();
					profesionalEspecialidadSedeIps
							.setProfesionalEspecialidad(profesionalEspecialidad);
					profesionalEspecialidadSedeIps.setSedeIps(sedeIps);
					profesionalEspecialidadSedeIpsRepository
							.save(profesionalEspecialidadSedeIps);
				}
			}

		} else {
			profesional = toProfesional(dto, null);
			profesional.setFechaInsert(new Date());
			profesional.setFechaUpdate(new Date());
		}
		profesionalRepo.save(profesional);
	}
	
	/**
	 * 
	 * @param dto
	 * @return
	 */
	
	private Profesional toProfesional(ProfesionalDto dto,Profesional entity){
		if(entity==null)entity = new Profesional();
		entity.setPrimerNombre(dto.getPrimerNombre());
		entity.setSegundoNombre(dto.getSegundoNombre());
		entity.setPrimerApellido(dto.getPrimerApellido());
		entity.setSegundoApellido(dto.getSegundoApellido());
		entity.setNumeroIdentificacion(dto.getNumeroIdentificacion());
		TipoIdentificacion tipoIdentificacion=tir.findOne(Integer.valueOf(dto.getTipoIdentificacion()));	
		entity.setIdentificacionProfesional(tipoIdentificacion);
		entity.setRegistroMedico(dto.getRegistroMedico());
		entity.setId(dto.getId());
		return entity;
	}
	
	

}
