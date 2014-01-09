package com.conexia.saludcoop.validador.businessRules;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ar.com.conexia.rules.Rule;

import com.conexia.saludcoop.common.entity.maestro.Afiliado;

@Component
@Rule(description = "Verificar y marcar afiliado con complicaciones de Artritis Rematoidea.")
public class RN0006 extends RNProcess {

	private static Logger LOGGER = LoggerFactory.getLogger(RN0006.class);
	private boolean validarRegla(HashMap<String, Object> aContext) throws Exception {

		boolean result = true;
		Afiliado afiliado = (Afiliado) aContext.get("afiliado");
		if (afiliado.getPrimerNombre().contains("C"))
			result = true;
		return result;
	}

	/**
	 * Ejecuta la regla pasando los datos necesarios para su ejecucion.
	 */
	public int executeRegla(HashMap<String, Object> aContext) throws Exception {

		return 0;
//		int execResult = RESULT_OK;
//		RespuestaDto rta = new RespuestaDto();
//		if (validarRegla(aContext)) {
//			execResult = RESULT_OK;
//			rta.setCodigoRespuesta(1);
//			rta.setMensajeRespuesta("Afiliacion valida");
//			aContext.put("respuesta", rta);
//			LOGGER.info("Se ejecuto con exito la regla " + this.getClass().getName());
//		} else {
//			execResult = RESULT_NOK;
//			rta.setCodigoRespuesta(0);
//			rta.setMensajeRespuesta("Afiliado invalido");
//			aContext.put("respuesta", rta);
//			LOGGER.info("Se ejecuto con errores la regla  " + this.getClass().getName());
//		}
//
//		return execResult;
	}

}