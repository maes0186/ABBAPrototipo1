package com.conexia.saludcoop.validador.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.repository.PropertiesRepository;

@Service
@Transactional
public class PropertiesManager {

	/**
	 * Loger del servicio.
	 */
	private static Logger LOGGER = LoggerFactory.getLogger(PropertiesManager.class);
	
	@Autowired
	private PropertiesRepository propRep;
	
	public String getTimeoutValidador(){
		String timeout = null;
		try {
			timeout = propRep.findOneByClaveAndAplicacion("timeout", "validador").getValor();		
		} catch (Exception e) {
			LOGGER.error(e.toString());
			return null;
		}
	return timeout;	
	}
}
