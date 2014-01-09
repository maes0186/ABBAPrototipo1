package com.conexia.saludcoop.web.manager;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.IpsDto;
import com.conexia.saludcoop.common.entity.maestro.Ips;
import com.conexia.saludcoop.common.repository.IpsRepository;

/**
 * 
 * @author nobregon
 *
 */
@Service
@Transactional
public class IpsManager extends SpringDataManager<Ips, IpsDto> {

	@Autowired
	private IpsRepository repo;

	@Override
	public IpsDto findOne(Long id) {
		return repo.findOne(id).toDto();
	}

	@Override
	public List<IpsDto> findAll() {
		return toDtos(repo.findAll());
	}

	@Override
	public List<IpsDto> toDtos(Iterable<Ips> entidades) {
		List<IpsDto> dtos = new Vector<IpsDto>(
				((List<Ips>) entidades).size());
		for (Ips e : entidades) {
			dtos.add(e.toDto());
		}
		return dtos;
	}

	public List<IpsDto> getIPSByIdentificacionRazonSocial(Integer tipoIdentId, String numeroIdentificacion, String razonSocial) {

		if(numeroIdentificacion== null){
			numeroIdentificacion="";
		}
		numeroIdentificacion = "%" + numeroIdentificacion + "%";
		
		if(razonSocial== null){
			razonSocial="";
		}
		razonSocial = "%" + razonSocial + "%";
		
		List<IpsDto> ipsDto = new Vector<>();
		List<Ips> ips = new Vector<>();
		ips = repo.findByTipoIdentificacionIdAndNumeroIdentificacionLikeAndRazonSocialLike(tipoIdentId,numeroIdentificacion,razonSocial);
		
		for (Ips entity : ips) {
			ipsDto.add(entity.toDto());
		}
		return ipsDto;
		
		
	}

}