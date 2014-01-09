package com.conexia.saludcoop.validador.businessRules;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.conexia.rules.Rule;

import com.conexia.saludcoop.common.dto.RespuestaDto;
import com.conexia.saludcoop.common.entity.maestro.Afiliado;
import com.conexia.saludcoop.common.enumerator.EstadosAfiliacionHabilitados;
import com.conexia.saludcoop.common.repository.AfiliadoRepository;
import com.conexia.saludcoop.common.util.I18NUtils;
import com.conexia.saludcoop.validador.constantes.ConstantesContexto;

@Component
@Rule(description = "Regla que verifica si el beneficiario existe y está habilitado.")
public class RN0001 extends RNProcess {
	
	private static Logger LOGGER = LoggerFactory.getLogger(RN0001.class);
	@Autowired
	private AfiliadoRepository afiliadoRepo;
	
	private boolean validarRegla(HashMap<String, Object> aContext) throws Exception {

		boolean result = false;
		
		Afiliado afiliado  = (Afiliado) aContext.get(ConstantesContexto.AFILIADO);
		if (afiliado == null){
			
			Integer tipo = (Integer) aContext.get(ConstantesContexto.TIPO_IDENTIFICACION);
			String numero = (String) aContext.get(ConstantesContexto.NRO_IDENTIFICACION);
			afiliado = afiliadoRepo.findOneByTipoIdentificacionIdAndNumeroIdentificacion(tipo, numero);
			if(afiliado == null){
				RespuestaDto rta = new RespuestaDto();
				Integer codRespuesta = new Integer(I18NUtils.getInstance().getText("respuestaTrx.codigoNoOk"));
				rta.setCodigoRespuesta(codRespuesta);
				rta.setMensajeRespuesta(I18NUtils.getInstance().getText("respuestaTrx.afiliadoInexistente")); 
				
				aContext.put(ConstantesContexto.RESPUESTA_TRX, rta);
				LOGGER.info("El afiliado no existe en la base de datos");
				return result;
			}
			else aContext.put(ConstantesContexto.AFILIADO, afiliado);
			
		}
		
		
		
		
		if(EstadosAfiliacionHabilitados.getEstadoAfiliacionByDescripcion(afiliado.getEstadoAfiliacion().getDescripcion())!= null)
			result = true;
		
		else{
			RespuestaDto rta = new RespuestaDto();
			
			Integer codRespuesta = new Integer(I18NUtils.getInstance().getText("respuestaTrx.codigoNoOk"));
			rta.setCodigoRespuesta(codRespuesta);
			rta.setMensajeRespuesta(I18NUtils.getInstance().getText("respuestaTrx.afiliadoDeshabilitado")); 
			
			aContext.put(ConstantesContexto.RESPUESTA_TRX, rta);
			LOGGER.info("El afiliado no existe en la base de datos");
		}
		return result;
	}

	/**
	 * Ejecuta la regla pasando los datos necesarios para su ejecucion.
	 */
	public int executeRegla(HashMap<String, Object> aContext) throws Exception {

		int execResult = RESULT_NOK;

		if (validarRegla(aContext)) {
			execResult = RESULT_OK;
		} 

		LOGGER.info("Se ejecuto con exito la regla" + this.getClass().getName());
		return execResult;
	}

}
