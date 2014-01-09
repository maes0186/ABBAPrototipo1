/**
 * 
 */
package com.conexia.saludcoop.web.manager;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.conexia.common.persistence.exception.DaoException;

import com.conexia.saludcoop.common.entity.security.RecoveryToken;
import com.conexia.saludcoop.common.entity.security.User;
import com.conexia.saludcoop.common.exception.ServiceException;
import com.conexia.saludcoop.security.dao.RecoveryTokenRepository;
import com.conexia.saludcoop.security.dto.RecoveryTokenDto;

/**
 * @author nobregon
 *
 */
@Service
@Transactional
public class RecoveryTokenManager {
	
	@Autowired
	RecoveryTokenRepository tokenDao;
	
	/**
	 * Logger del Servicio.
	 */
	private static final Logger LOG = LoggerFactory
			.getLogger(RecoveryTokenManager.class);
	
	/**
	 * Genera un nuevo token para la recuperacion de contrasenia
	 * @return token - Es el token generado
	 */
	public String generateTokenForUser(User user) throws ServiceException{
		char[] cars = {'a','b','c','d','e','f','r','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','1','2','3','4','5','6','7','8','9','0'};
		String randomToken=RandomStringUtils.random(20, cars);
		RecoveryTokenDto dto = new RecoveryTokenDto(null, randomToken, null, false,  user);
		tokenDao.save((RecoveryToken)dto.toEntity());
		return randomToken;
	}
	
	
	/**
	 * Guarda un nuevo token
	 */
	public Long addToken(RecoveryTokenDto token) throws ServiceException {
		RecoveryToken tokenRC = (RecoveryToken) token.toEntity();
		try{
			RecoveryToken id = tokenDao.save(tokenRC);
			return id.getId();
		}catch(Exception e){
			LOG.error(e.toString());
			throw new ServiceException("Error saving tokenRecuperacionContra", e);
		}
	}

	/**
	 * Retorna todos el ultimo token asociado a un usuario
	 */
	public RecoveryTokenDto getUserLastToken(User user) throws ServiceException {
		RecoveryTokenDto salida;
		
		try{
			RecoveryToken token = tokenDao.findOneByUserOrderByInsertedDesc(user);
			salida = (RecoveryTokenDto) token.toDTO();
			return salida;
		}catch(DaoException e){
			LOG.error(e.toString());
			return null;
		}
	}


}
