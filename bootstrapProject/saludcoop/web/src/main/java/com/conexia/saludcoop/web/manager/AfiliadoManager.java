package com.conexia.saludcoop.web.manager;

import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.AfiliadoDto;
import com.conexia.saludcoop.common.entity.maestro.Afiliado;
import com.conexia.saludcoop.common.exception.ServiceException;
import com.conexia.saludcoop.common.repository.AfiliadoRepository;
import com.conexia.saludcoop.web.vo.utils.ParserVO;

/**
 * @author nobregon
 * 
 */
@Repository
@Transactional
public class AfiliadoManager extends GeneralManager implements IAfiliadoManager {

	@Autowired
	private AfiliadoRepository afiliadoRepo;

	@Autowired
	private ParserVO parser;
	private static Logger LOGGER = LoggerFactory.getLogger(AfiliadoManager.class);

	public AfiliadoManager() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.conexia.saludcoop.web.manager.IAfiliadoManager#
	 * getBeneficiarioByTipoNumeroDocumento(java.lang.Integer, java.lang.String)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public AfiliadoDto getAfiliadoByTipoNumeroDocumento(final Integer tipoDocumento, final String numeroDocumento, Long eps) throws ServiceException {
		Afiliado afiliado = null;
		try {

			afiliado = afiliadoRepo.findOneByTipoIdentificacionIdAndNumeroIdentificacionAndEpsId(tipoDocumento, numeroDocumento, eps);
			if(afiliado != null){
				return afiliado.toDto();		
			}else{
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public AfiliadoDto getAfiliadoByTipoNumeroDocumento(final Integer tipoDocumento, final String numeroDocumento) throws ServiceException {
		Afiliado afiliado = null;
		try {
			
			afiliado = afiliadoRepo.findOneByTipoIdentificacionIdAndNumeroIdentificacion(tipoDocumento, numeroDocumento);
			if(afiliado != null){
				return afiliado.toDto();		
			}else{
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public AfiliadoDto getById(Long id) {
		Afiliado a = afiliadoRepo.findOneById(id);

		return a.toDto();
	}

	@Override
	public List<AfiliadoDto> getAfiliadosByNombres(String nombres, String apellidos) throws ServiceException {

		List<AfiliadoDto> afiliadoDtos = new Vector<>();
		List<Afiliado> afiliadoesEncontrados = afiliadoRepo.findByNombreYApellido(getLikeParameter(nombres), getLikeParameter(apellidos));
		for (Afiliado afiliado : afiliadoesEncontrados) {
			afiliadoDtos.add(afiliado.toDto());
		}

		return afiliadoDtos;
	}

	@Override
	public List<AfiliadoDto> getAfiliadosByNombres(String nombres, String apellidos, Long eps) throws ServiceException {
		List<AfiliadoDto> afiliadoDtos = new Vector<>();
		List<Afiliado> afiliadoesEncontrados = afiliadoRepo.findByNombreYApellido(getLikeParameter(nombres), getLikeParameter(apellidos), eps);
		for (Afiliado afiliado : afiliadoesEncontrados) {
			afiliadoDtos.add(afiliado.toDto());
		}

		return afiliadoDtos;
	}
}