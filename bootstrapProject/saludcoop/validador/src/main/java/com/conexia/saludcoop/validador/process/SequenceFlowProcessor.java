package com.conexia.saludcoop.validador.process;

import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.validador.constantes.ConstantesContexto;
import com.conexia.saludcoop.validador.manager.PropertiesManager;

/**
 * 
 * Clase de proceso que se encarga de ejecutar un conjunto de tareas preconfiguradas al estilo de un FlowController
 */
@Component
public class SequenceFlowProcessor {
	
	@Autowired
	private PropertiesManager prop;
	
	@Autowired
	private SequencesManager sequencesManager;
	
	public static final int ENDING_CODE_GENERIC_ERROR = 900;
	public static final int ENDING_CODE_TIMEOUT_TRANSACTION = 901;
	public static final int ENDING_CODE_SOCKET_TIMEOUT_TRANSACTION = 902;
	private static Logger LOGGER = LoggerFactory.getLogger(SequenceFlowProcessor.class);
	private int threshold = 0; //
		
	/** Realiza la configuracion inicial del objeto
	*/
	@PostConstruct
	public void configure() throws Exception {
		threshold = Integer.valueOf(prop.getTimeoutValidador());
	}
    
	
	/** Metodo que realiza la ejecucion de todos los procesos enlistados */
	@Transactional
	public int execute(HashMap<String, Object> aContext, boolean errorFree) {

		int result = 0, endCode;
		SequencedProcess actualProcess = null;
		String actionType = "", logString;

		long initialTime = 0;
		long actualTime = 0;
		long diffTime = -1;
		if(!this.sequencesManager.isConfigured()){
			try {
				this.sequencesManager.initialize();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				LOGGER.error(e.toString());
			}
		}
		if (errorFree) {
			try {
				actionType = (String) aContext.get(ConstantesContexto.TRANSACTION_TYPE);
				
				actualProcess = this.sequencesManager.getFirstProcess(actionType);

				// toma el tiempo inicial
				initialTime = System.currentTimeMillis();

				while (actualProcess != null) {
					logString = "===== Ejecutando SubProceso: " + actualProcess.getClass().getName() + " (SECUENCIA:" + actualProcess.getSequenceAsString() + " ORDEN:" + actualProcess.getOrderAsString() + ")";
					LOGGER.info(logString);

					LOGGER.info("===== ID: [" + actualProcess.getProcessId() + "]");

					endCode = actualProcess.execute(aContext, errorFree);
					actualProcess.setEndingCode(endCode);

					// el tiempo que tardo el processo.
					actualTime = System.currentTimeMillis();

					LOGGER.info("===== CODIGO DE FINALIZACION: " + actualProcess.getEndingCode());

					if (actualProcess.getEndingCode() >= ENDING_CODE_GENERIC_ERROR) { // Ocurrió algun error
						result = 1;
						break;
					}

					// calcula la demora.
					diffTime = (actualTime - initialTime);

					// sy el umbral esta activo, y si es menor o igual al tiempo
					// transcurrido, aborta.
					if ((threshold > 0) && (threshold <= diffTime)) {
						actualProcess.setEndingCode(ENDING_CODE_TIMEOUT_TRANSACTION);
						LOGGER.info("Tiempo de procesamiento excedido: " + String.valueOf(diffTime) + " ms.");
						result = 1;
						break;
					}

					actualProcess = this.sequencesManager.getNextProcess(actionType, actualProcess);
				}
			} catch (Exception ex) {
				result = 1;
				ex.printStackTrace();
				LOGGER.error(ex.toString());
			}

		} else {
			result = errorFree ? 0 : 1;
			LOGGER.info(" >> No se ha ejecutado el proceso " + this.getClass().getName() + " debido a errores en procesos anteriores.");
		}

		return result;
	}
	
}
