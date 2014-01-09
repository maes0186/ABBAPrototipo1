package com.conexia.saludcoop.common.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.conexia.saludcoop.common.mail.entity.EmailServerConfiguration;
import com.conexia.saludcoop.common.mail.manager.EmailSenderConfigurationPropertiesManager;

/**
 * Único proveedor de configuración de envío de correo electrónico.
 * 
 * @author Sebastián Matienzo
 */
@Service
public class EmailSenderConfigurationProvider {

	/**
	 * Única instancia del proveedor en la aplicación.
	 */
	private EmailServerConfiguration instance = null;
	
	/**
	 * Administrador de propiedades de configuración de correo electrónico.
	 */
	@Autowired
	private EmailSenderConfigurationPropertiesManager manager;

	/**
	 * Constructor privado para evitar su instanciación.
	 */
	private EmailSenderConfigurationProvider() {

	}

	/**
	 * Obtiene la instancia del proveedor de repositorio de archivos.
	 * 
	 * @return Instancia del proveedor de repositorio de archivos.
	 */
	public EmailServerConfiguration getInstance() {

		if (this.instance == null) {
			this.instance = new EmailServerConfiguration();
			
			this.instance.setUsername(this.manager.getUsername());
			this.instance.setPassword(this.manager.getPassword());
			this.instance.setHostAddress(this.manager.getHostAddress());
			this.instance.setPort(this.manager.getPort());
			this.instance.setSslRequired(this.manager.isSslRequired());
		}

		return (this.instance);
	}
}
