package com.conexia.saludcoop.web.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.SedeIpsDto;
import com.conexia.saludcoop.common.entity.maestro.SedeIps;
import com.conexia.saludcoop.common.repository.SedeIpsDaoImpl;
import com.conexia.saludcoop.common.repository.SedeIpsRepository;

/**
 * 
 * @author nobregon
 *
 */
@Service
@Transactional
public class SedeIpsManager extends SpringDataManager<SedeIps, SedeIpsDto>{
	
	@Autowired 
	private SedeIpsRepository repo;


	@Autowired 
	private SedeIpsDaoImpl sedeIpsDao;
	
	@Override
	public SedeIpsDto findOne(Long id) {
		return repo.findOne(id).toDto();
	}

	@Override
	public List<SedeIpsDto> findAll() {
		return toDtos(repo.findAll());
	}

	@Override
	public List<SedeIpsDto> toDtos(Iterable<SedeIps> entidades) {
		List<SedeIpsDto> dtos = new Vector<SedeIpsDto>(((List<SedeIps>)entidades).size());
		for (SedeIps e : entidades){
			dtos.add(e.toDto());
		}
		return dtos;
	}
	
	public List<SedeIpsDto> getSedesByIpsDireccion( Integer tipoIdentificacion,	String numeroIdentificacion, String razonSocial,
													String departamento, String municipio, String direccion, Long epsId){
		List<SedeIps> sedesEncontradas = new Vector<>();
		sedesEncontradas = sedeIpsDao.findByIPS(tipoIdentificacion,	getNullable(numeroIdentificacion), razonSocial,	
				departamento, municipio, direccion, epsId);
		if(sedesEncontradas != null && !sedesEncontradas.isEmpty()){
			return toDtos(sedesEncontradas);
		}else{
			return new Vector<>();	
		}
		
	}
	
	public List<SedeIpsDto> buscarIpsPorProfesional(Long idProfesional) {
		List<SedeIps> sedesEncontradas = new ArrayList<>();
		sedesEncontradas = repo.findByProfesional(idProfesional);
		if (sedesEncontradas != null && !sedesEncontradas.isEmpty()) {
			return toDtos(sedesEncontradas);
		} else {
			return new ArrayList<>();
		}

	}
	
	
	
}
