/**
 *
 */
package com.conexia.saludcoop.validador.businessRules;

import java.util.HashMap;

import org.jdom.Element;
import org.springframework.stereotype.Component;

import ar.com.conexia.rules.EndingCode;

import com.conexia.saludcoop.common.dto.RespuestaDto;
import com.conexia.saludcoop.common.util.I18NUtils;
import com.conexia.saludcoop.validador.businessRules.exceptions.ReglaNoEjecutadaException;
import com.conexia.saludcoop.validador.constantes.ConstantesContexto;
import com.conexia.saludcoop.validador.process.SequencedProcess;
import com.conexia.saludcoop.validador.util.JDOMUtilities;

/**
 * @author afeito
 *
 */
@Component
public abstract class RNProcess extends SequencedProcess {

	@EndingCode(description="Se cumple la validación.")
	protected static int RESULT_OK = 0;
	
	@EndingCode(description="No se cumple la validación.")
	protected static int RESULT_NOK = 250;
	
    private String dbTransactionConnectionKey = "";
    /**
	 * @param dbTransactionConnectionKey the dbTransactionConnectionKey to set
	 */
	public void setDbTransactionConnectionKey(String dbTransactionConnectionKey) {
		this.dbTransactionConnectionKey = dbTransactionConnectionKey;
	}

	/**
	 * @return the dbTransactionConnectionKey
	 */
	public String getDbTransactionConnectionKey() {
		return dbTransactionConnectionKey;
	}

		
	/** Configura el string de conexion de cada regla**/
	public void configure ( Element processElement ) throws Exception {

		super.configure(processElement);
		this.setDbTransactionConnectionKey(JDOMUtilities.getAttribute(processElement, "dbTransactionConnectionKey"));
	}

	/** Es implementado en cada regla en particular **/
	protected abstract int executeRegla(HashMap<String, Object> context) throws Exception ;

	/** Realiza la ejecucion del proceso de identificacion *
	 * @throws Exception */
	public int execute( HashMap<String, Object> aContext, boolean errorFree ) throws ReglaNoEjecutadaException {

		
		int execResult = 250;
		try {
			execResult = this.executeRegla( aContext );
		} catch (Exception e) {
			e.printStackTrace();
			RespuestaDto rta = (RespuestaDto) aContext.get(ConstantesContexto.RESPUESTA_TRX);
			
			if (rta == null) {
				rta = new RespuestaDto();
				
				Integer codRespuesta = new Integer(I18NUtils.getInstance().getText("respuestaTrx.codigoNoOk"));
				rta.setCodigoRespuesta(codRespuesta);
				rta.setMensajeRespuesta(I18NUtils.getInstance().getText("respuestaTrx.transaccionNoOk")); 
				aContext.put(ConstantesContexto.RESPUESTA_TRX, rta);
			}
			
			throw new ReglaNoEjecutadaException(e);
		}	
		    	
		return execResult;
	}


}
