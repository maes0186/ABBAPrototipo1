package com.conexia.saludcoop.web.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.UsuarioEntidadDto;
import com.conexia.saludcoop.common.entity.security.UsuarioEntidad;
import com.conexia.saludcoop.common.repository.UsuarioEntidadRepository;
@Repository
@Transactional
public class UsuarioEntidadManager extends GeneralManager {
	@Autowired
	private UsuarioEntidadRepository usuarioEntidadRepo;

	public UsuarioEntidadDto getUsuarioEntidad(Long usuarioId){
	    //TODO: PREGUNTARLE A FACU COMO SE HACE ESTO ATTE: DARIO
		List<UsuarioEntidad> ue = usuarioEntidadRepo.findEntidadsById(usuarioId);
		UsuarioEntidad userEntidad = null;
		if(ue.size() > 0){
		    userEntidad = ue.get(0);
		}
		UsuarioEntidadDto ueDto = new UsuarioEntidadDto();
		
		if(userEntidad != null){
			ueDto = userEntidad.toDto();
		}
		return ueDto;
		
	}

	public UsuarioEntidadDto getUsuarioEntidadByUsuarioId(Long usuarioId){
		UsuarioEntidad ue = usuarioEntidadRepo.findOneByUsuarioId(usuarioId);

		UsuarioEntidadDto ueDto = new UsuarioEntidadDto();
		
		if(ue != null){
			ueDto = ue.toDto();
		}
		return ueDto;
	}
}
