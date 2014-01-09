package com.conexia.saludcoop.validador.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.conexia.saludcoop.common.enumerator.RegimenAfiliacion;
import com.conexia.saludcoop.validador.provider.MontosCopagoProvider;

/**
 * Listener de contexto para inicializar datos una vez generado el contexto de Spring.
 * 
 * @author Sebastián Matienzo
 */
@Service
public class SaludCoopContextInitialization implements ApplicationContextAware {

	/**
	 * Logger de la clase.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(SaludCoopContextInitialization.class);

	/**
	 * Proveedor de montos de copagos.
	 */
	@Autowired
	private MontosCopagoProvider montosCopagoProvider;

	@Override
	public void setApplicationContext(final ApplicationContext applicationContext) {

		SaludCoopContextInitialization.LOG.info("Inicializando montos de copagos.");

		try {
			/*this.montosCopagoProvider.getMontosCopagoPorAnio(-1, -1, RegimenAfiliacion.CONTRIBUTIVO);*/

			SaludCoopContextInitialization.LOG
			        .info("Inicialización de montos de copagos exitosa.");
		} catch (final Exception e) {
			SaludCoopContextInitialization.LOG.info("Error al inicializar montos de copagos: " 
					+ e.getMessage(), e);
		}
	}
}
