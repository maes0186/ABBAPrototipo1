package com.conexia.saludcoop.common.io.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.conexia.repository.access.FileRepositoryAccessConfiguration;
import com.conexia.repository.access.FileRepositoryAccessManager;
import com.conexia.repository.provider.impl.LocalDiskProvider;
import com.conexia.repository.provider.impl.LocalDiskProviderConfiguration;
import com.conexia.saludcoop.common.io.manager.RepositoryPropertiesManager;

/**
 * Único proveedor de repositorio de archivos alojados en el disco rígido del servidor.
 * 
 * @author Sebastián Matienzo
 */
@Service
public final class FileRepositoryProvider {

	/**
	 * Única instancia del proveedor en la aplicación.
	 */
	private FileRepositoryAccessManager instance = null;
	
	/**
	 * Administrador de propiedades del repositorio.
	 */
	@Autowired
	private RepositoryPropertiesManager manager;

	/**
	 * Constructor privado para evitar su instanciación.
	 */
	private FileRepositoryProvider() {

	}

	/**
	 * Obtiene la instancia del proveedor de repositorio de archivos.
	 * 
	 * @return Instancia del proveedor de repositorio de archivos.
	 */
	public FileRepositoryAccessManager getInstance() {

		if (this.instance == null) {
			this.instance = new FileRepositoryAccessManager();

			this.instance.configure(this.getConfigurationForInstance());
		}

		return (this.instance);
	}

	/**
	 * Obtiene la configuración para armar la instancia del proveedor.
	 * 
	 * @return Configuración para armar la instancia del proveedor.
	 */
	private FileRepositoryAccessConfiguration<LocalDiskProviderConfiguration>
	        getConfigurationForInstance() {

		final FileRepositoryAccessConfiguration<LocalDiskProviderConfiguration> config =
		        new FileRepositoryAccessConfiguration<LocalDiskProviderConfiguration>();
		config.setEncodedFileNameLength(this.manager.getEncodedFileNameLength());
		config.setEncryptionEnabled(this.manager.isEncryptionEnabled());
		config.setEncryptionKeyLength(this.manager.getEncryptionKeyLength());
		config.setFileCacheEnabled(this.manager.isFileCacheEnabled());
		config.setMaxFilesCached(this.manager.getMaxFilesCached());

		config.setFileRepositoryProvider(new LocalDiskProvider());
		config.getFileRepositoryProvider().setConfiguration(new LocalDiskProviderConfiguration());
		config.getFileRepositoryProvider().getConfiguration().setPath(this.manager.getFileRepositoryPath());

		return (config);
	}
}
