package com.conexia.saludcoop.web.manager;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.conexia.saludcoop.Globals;
import com.conexia.saludcoop.common.entity.security.User;
import com.conexia.saludcoop.security.dao.UserRepository;

@Service
@Transactional
public class UsuarioManager {

	/**
	 * Loger del servicio.
	 */
	private static Logger LOGGER = LoggerFactory.getLogger(UsuarioManager.class);
	
	@Autowired
	private UserRepository userDao;
	
	/**
	 * Metodo que recibe el user name y obtiene el objeto usuario.
	 * @param username a buscar.
	 * @return objeto usuario encontrado.
	 */
	public User loadUserByUsername(String username){
		User user = null;
		try {
			user = userDao.findOneByUsername(username);
		} catch (Exception e) {
			LOGGER.error(e.toString());
			return null;
		}
	return user;	
	}

	/**
	 * @param user
	 * @param password
	 */
	public void updatePassword(User user, String password) {
		try{
			String hash = new String(DigestUtils.md5DigestAsHex(password.getBytes("UTF-8")));
        	user.setPassword(hash);
        	
        	Calendar c = Calendar.getInstance();
        	c.add(Calendar.DAY_OF_MONTH, Globals.getInstance().getMaxDaysForExpirationTime());
        	
        	Date credentialsExpirationDate = c.getTime();
        	user.setCredentialsExpirationDate(credentialsExpirationDate);
        	
			userDao.save(user);
		} catch(UnsupportedEncodingException uee){
			LOGGER.error(uee.toString());
		}
	}

	/**
	 * Reinicia el contador de accesos erroneos para un usuario
	 * @param user - El objeto User que va a ser modificado 
	 */
	public void clearFailedLoginAttempts(User user) {
		user.setFailedLogins(0);
		userDao.save(user);
	}	
}