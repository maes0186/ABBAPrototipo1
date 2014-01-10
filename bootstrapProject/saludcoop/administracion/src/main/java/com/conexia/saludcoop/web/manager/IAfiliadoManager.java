package com.conexia.saludcoop.web.manager;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.AfiliadoDto;
import com.conexia.saludcoop.common.exception.ServiceException;

@Repository
public interface IAfiliadoManager {
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public abstract AfiliadoDto getAfiliadoByTipoNumeroDocumento(Integer tipoDocumento, String numeroDocumento) throws ServiceException;

	public abstract AfiliadoDto getAfiliadoByTipoNumeroDocumento(Integer tipoDocumento, String numeroDocumento, Long eps) throws ServiceException;

	public abstract AfiliadoDto getById(Long id);

	public abstract List<AfiliadoDto> getAfiliadosByNombres(String nombres, String apellidos) throws ServiceException;

	public abstract List<AfiliadoDto> getAfiliadosByNombres(String nombres, String apellidos, Long eps) throws ServiceException;

}