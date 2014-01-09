package com.conexia.saludcoop.common.mail.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.repository.PropertiesRepository;

/**
 * Administrador de valores de configuración de envío de Emails.
 * 
 * @author Sebastián Matienzo
 */
@Service
@Transactional
public class EmailSenderConfigurationPropertiesManager {

	/**
	 * Logger del servicio.
	 */
	private static Logger LOGGER = LoggerFactory.getLogger(EmailSenderConfigurationPropertiesManager.class);
	
	/**
	 * Repositorio de propiedades.
	 */
	@Autowired
	private PropertiesRepository propRep;
	
	/**
	 * Obtiene el nombre de usuario con el cual conectarse al servidor de Emails.
	 * 
	 * @return Nombre de usuario con el cual conectarse al servidor de Emails.
	 */
	public String getUsername() {
		
		String value = null;
		
		try {
			value = this.propRep.findOneByClaveAndAplicacion("email_config_username", "web").getValor();		
		} catch (final Exception e) {
			EmailSenderConfigurationPropertiesManager.LOGGER.error("Error al leer propiedades en BD: "
					+ e.getMessage(), e);
		}
		
		return (value);	
	}
	
	/**
	 * Obtiene la clave de usuario con el cual conectarse al servidor de Emails.
	 * 
	 * @return Clave de usuario con el cual conectarse al servidor de Emails.
	 */
	public String getPassword() {
		
		String value = null;
		
		try {
			value = this.propRep.findOneByClaveAndAplicacion("email_config_password", "web").getValor();		
		} catch (final Exception e) {
			EmailSenderConfigurationPropertiesManager.LOGGER.error("Error al leer propiedades en BD: "
					+ e.getMessage(), e);
		}
		
		return (value);	
	}
	
	/**
	 * Obtiene la IP que corresponde al servidor de Emails.
	 * 
	 * @return IP que corresponde al servidor de Emails.
	 */
	public String getHostAddress() {
		
		String value = null;
		
		try {
			value = this.propRep.findOneByClaveAndAplicacion("email_config_hostAddress", "web").getValor();		
		} catch (final Exception e) {
			EmailSenderConfigurationPropertiesManager.LOGGER.error("Error al leer propiedades en BD: "
					+ e.getMessage(), e);
		}
		
		return (value);	
	}

	/**
	 * Obtiene el puerto de conexión.
	 * 
	 * @return Puerto de conexión.
	 */
	public Integer getPort() {
		
		String value = null;
		
		try {
			value = this.propRep.findOneByClaveAndAplicacion("email_config_port", "web").getValor();		
		} catch (final Exception e) {
			EmailSenderConfigurationPropertiesManager.LOGGER.error("Error al leer propiedades en BD: "
					+ e.getMessage(), e);
		}
		
		if (value == null) {
			return (null);
		}
		
		return (Integer.parseInt(value));	
	}
	
	/**
	 * Obtiene el indicador de si es requerido SSL.
	 * 
	 * @return Indicador de si es requerido SSL.
	 */
	public Boolean isSslRequired() {
		
		String value = null;
		
		try {
			value = this.propRep.findOneByClaveAndAplicacion("email_config_sslRequired", "web").getValor();		
		} catch (final Exception e) {
			EmailSenderConfigurationPropertiesManager.LOGGER.error("Error al leer propiedades en BD: "
					+ e.getMessage(), e);
		}
		
		if (value == null) {
			return (null);
		}
		
		return (Boolean.parseBoolean(value));	
	}
}
