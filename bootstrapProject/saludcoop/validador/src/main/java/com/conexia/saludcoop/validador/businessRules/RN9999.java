/****/
package com.conexia.saludcoop.validador.businessRules;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ar.com.conexia.rules.EndingCode;
import ar.com.conexia.rules.Rule;

import com.conexia.saludcoop.common.dto.RespuestaDto;
import com.conexia.saludcoop.common.util.I18NUtils;
import com.conexia.saludcoop.validador.constantes.ConstantesContexto;

/**
 * Regla 'Dumb', hecha al solo efecto de facilitar el armado del xml
 * flowcontroller 
 * 
 * @author Adrian Muñoz
 */
@Component
@Rule(description="Regla 'Dumb', hecha al solo efecto de facilitar el armado del xml flowcontroller ")
public class RN9999 extends RNProcess {
	
	private static Logger LOGGER = LoggerFactory.getLogger(RN9999.class);
	@EndingCode(description="Única salida")
	private static int RESULT_OK = 0;
  

	/** Realiza la ejecucion del proceso de identificacion **/
	public int executeRegla( HashMap<String, Object> aContext ) throws  Exception {

		RespuestaDto rta = (RespuestaDto) aContext.get(ConstantesContexto.RESPUESTA_TRX);
		
		if (rta == null) {
			rta = new RespuestaDto();
			
			Integer codRespuesta = new Integer(I18NUtils.getInstance().getText("respuestaTrx.codigoOk"));
			rta.setCodigoRespuesta(codRespuesta);
			rta.setMensajeRespuesta(I18NUtils.getInstance().getText("respuestaTrx.transaccionOk")); 
			aContext.put(ConstantesContexto.RESPUESTA_TRX, rta);
		}
		LOGGER.info("Se ejecuto la regla 9999. Fin del proceso");
		return RESULT_OK;
	}
}
