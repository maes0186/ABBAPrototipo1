package com.conexia.saludcoop.common.io.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.repository.PropertiesRepository;

/**
 * Administrador de valores del repositorio de archivos.
 * 
 * @author Sebastián Matienzo
 */
@Service
@Transactional
public class RepositoryPropertiesManager {
	
	

	/**
	 * Logger del servicio.
	 */
	private static Logger LOGGER = LoggerFactory.getLogger(RepositoryPropertiesManager.class);
	
	/**
	 * Repositorio de propiedades.
	 */
	@Autowired
	private PropertiesRepository propRep;
	
	/**
	 * Obtiene la longitud a usar para el nombre de los archivos.
	 * 
	 * @return Longitud del nombre de los archivos.
	 */
	public Integer getEncodedFileNameLength() {
		
		String value = null;
		
		try {
			value = this.propRep.findOneByClaveAndAplicacion("rep_arch_encodedFileNameLength", "web").getValor();		
		} catch (final Exception e) {
			RepositoryPropertiesManager.LOGGER.error("Error al leer propiedades en BD: "
					+ e.getMessage(), e);
		}
		
		if (value == null) {
			return (null);
		}
		
		return (Integer.parseInt(value));	
	}
	
	/**
	 * Obtiene el indicador de si debe encriptar archivos.
	 * 
	 * @return Indicador de si debe encriptar archivos.
	 */
	public Boolean isEncryptionEnabled() {
		
		String value = null;
		
		try {
			value = this.propRep.findOneByClaveAndAplicacion("rep_arch_encryptionEnabled", "web").getValor();		
		} catch (final Exception e) {
			RepositoryPropertiesManager.LOGGER.error("Error al leer propiedades en BD: "
					+ e.getMessage(), e);
		}
		
		if (value == null) {
			return (null);
		}
		
		return (Boolean.parseBoolean(value));	
	}
	
	/**
	 * Obtiene la longitud de la clave de encriptación a generar (si se habilita la encriptación).
	 * 
	 * @return Longitud de la clave de encriptación a generar (si se habilita la encriptación).
	 */
	public Integer getEncryptionKeyLength() {
		
		String value = null;
		
		try {
			value = this.propRep.findOneByClaveAndAplicacion("rep_arch_encryptionKeyLength", "web").getValor();		
		} catch (final Exception e) {
			RepositoryPropertiesManager.LOGGER.error("Error al leer propiedades en BD: "
					+ e.getMessage(), e);
		}
		
		if (value == null) {
			return (null);
		}
		
		return (Integer.parseInt(value));	
	}
	
	/**
	 * Obtiene el indicador de si debe usar un caché de archivos.
	 * 
	 * @return Indicador de si debe usar un caché de archivos.
	 */
	public Boolean isFileCacheEnabled() {
		
		String value = null;
		
		try {
			value = this.propRep.findOneByClaveAndAplicacion("rep_arch_fileCacheEnabled", "web").getValor();		
		} catch (final Exception e) {
			RepositoryPropertiesManager.LOGGER.error("Error al leer propiedades en BD: "
					+ e.getMessage(), e);
		}
		
		if (value == null) {
			return (null);
		}
		
		return (Boolean.parseBoolean(value));	
	}
	
	/**
	 * Obtiene la cantidad máxima de archivos en caché (si se habilita el caché).
	 * 
	 * @return Cantidad máxima de archivos en caché (si se habilita el caché).
	 */
	public Integer getMaxFilesCached() {
		
		String value = null;
		
		try {
			value = this.propRep.findOneByClaveAndAplicacion("rep_arch_maxFilesCached", "web").getValor();		
		} catch (final Exception e) {
			RepositoryPropertiesManager.LOGGER.error("Error al leer propiedades en BD: "
					+ e.getMessage(), e);
		}
		
		if (value == null) {
			return (null);
		}
		
		return (Integer.parseInt(value));	
	}
	
	/**
	 * Obtiene la ruta del repositorio de archivos en disco.
	 * 
	 * @return Ruta del repositorio de archivos en disco.
	 */
	public String getFileRepositoryPath() {
		
		String value = null;
		
		try {
			String os= System.getProperty("os.name").toLowerCase();
			if(os.contains("win")){
				value = this.propRep.findOneByClaveAndAplicacion("rep_arch_fileRepositoryPathWin", "web").getValor();		
			}else{
				value = this.propRep.findOneByClaveAndAplicacion("rep_arch_fileRepositoryPathLinux", "web").getValor();		
			}
			} catch (final Exception e) {
			RepositoryPropertiesManager.LOGGER.error("Error al leer propiedades en BD: "
					+ e.getMessage(), e);
		}
		
		return (value);	
	}
}
