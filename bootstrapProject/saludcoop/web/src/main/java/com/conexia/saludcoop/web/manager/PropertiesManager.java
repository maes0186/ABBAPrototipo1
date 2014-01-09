package com.conexia.saludcoop.web.manager;

import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	public String getUrlValidador(){
		String url = null;
		try {
			url = propRep.findOneByClaveAndAplicacion("rest_client", "web").getValor();		
		} catch (Exception e) {
			LOGGER.error(e.toString());
			return null;
		}
	return url;	
	}
	
	public String getCacheControl(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm"); 
		String cacheControl = sdf.format(new Date());
		try {
			cacheControl = propRep.findOneByClaveAndAplicacion("cache_control", "web").getValor();		
		} catch (Exception e) {
			LOGGER.error(e.toString());
		}
		return cacheControl;
	}
}
