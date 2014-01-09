package com.conexia.saludcoop.validador.process;

import java.net.URI;
import java.util.Hashtable;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;

import com.conexia.saludcoop.validador.util.StringUtilities;

/**
 * 
 *         Clase de proceso que se encarga de ejecutar un conjunto de tareas preconfiguradas al estilo de un FlowController pero con la particularidad de que el procesamiento puede ser no secuencial entre procesos
 *  
 */
@Component
@Scope("singleton")
public class SequencesManager {

	private static final String KEY_SEPARATOR = "_";

	private Hashtable<String, SequencedProcess> processesMap;
	private Hashtable<String, String> actionsMap;
	private Hashtable<String, String> nextSequenceMap;
	private boolean configured = false;

	
	public SequencesManager(){
		processesMap = new Hashtable<String, SequencedProcess>();
		actionsMap = new Hashtable<String, String>();
		nextSequenceMap = new Hashtable<String, String>();
	}
	
	public final static int PROCESS_ID_LENGTH = 7;
	public final static int SEQUENCE_LENGHT = 30;
	public final static int ORDER_LENGTH = 2;
	public final static int ENDING_CODE_LENGTH = 3;

	// ------------------------------------------------

	/**
	 * @return Returns the configured.
	 */
	public boolean isConfigured() {
		return configured;
	}

	/**
	 * @param configured The configured to set.
	 */
	private void setConfigured(boolean configured) {
		this.configured = configured;
	}

	/**
	 * Realiza la configuracion inicial del objeto, basandose para ello en un documento XML
	 */
	public boolean initialize() throws Exception {

		Document xmlDoc = new Document();
		SAXBuilder builder = new SAXBuilder();
		String configurationFile = "fcprocessor.xml";
		xmlDoc = builder.build(new URI(getClass().getClassLoader().getResource(configurationFile).toString()).getPath());
		
		Element processesElement, actionsElement;

		try {
			// Obtiene los elementos xml a parsear
			actionsElement = xmlDoc.getRootElement().getChild("actions");
			processesElement = xmlDoc.getRootElement().getChild("processes");

			// Pasa la informacion contenida en el xml a estructuras de tipo mapa
			if (actionsElement != null) {
				buildActionsMap(actionsElement);
			} else {
				throw new Exception("No se pudo encontrar el elemento 'actions' en el archivo de configuracion");
			}

			if (processesElement != null) {
				buildProcesses(processesElement);
			} else {
				throw new Exception("No se pudo encontrar el elemento 'processes' en el archivo de configuracion");
			}

			setConfigured(true);
			return isConfigured();

		} catch (Exception ex) {
			throw ex;
		}
	}

	/**
	 * Construye y configura cada uno de las acciones especificadas en el archivo de configuracion y los ubica en la estructura de acciones (HashTable)
	 */
	private void buildActionsMap(Element xmlElement) throws Exception {

		List actionsList, processesList;
		Element actionElement, processElement;
		String actionTypeStr, sequenceStr, processIdStr, orderStr, actionMapKey;
		int indiceAction, indiceProcess;

		// Levanta las acciones del XML
		actionsList = xmlElement.getChildren("action");
		indiceAction = 0;

		while (actionsList.listIterator(indiceAction).hasNext()) {
			actionElement = (Element) actionsList.listIterator(indiceAction).next();

			// Obtiene el tipo de acción o transacción
			actionTypeStr = actionElement.getAttributeValue("actionType");

			// Levanta la lista de procesos para la acción existentes en el XML
			processesList = actionElement.getChildren("internal_process");

			indiceProcess = 0;
			while (processesList.listIterator(indiceProcess).hasNext()) {
				processElement = (Element) processesList.listIterator(indiceProcess).next();

				// Obtiene y formatea los atributos del proceso
				sequenceStr = processElement.getAttributeValue("sequence");
				sequenceStr = StringUtilities.toRightAlignedFormat(sequenceStr, SEQUENCE_LENGHT, '0').toString();

				processIdStr = processElement.getAttributeValue("id");
				// processIdStr = StringUtilities.toRightAlignedFormat( processIdStr, 3, '0' ).toString();
				processIdStr = StringUtilities.toRightAlignedFormat(processIdStr, PROCESS_ID_LENGTH, '0').toString();

				orderStr = processElement.getAttributeValue("order");
				orderStr = StringUtilities.toRightAlignedFormat(orderStr, ORDER_LENGTH, '0').toString();

				// Genera la clave
				actionMapKey = actionTypeStr + KEY_SEPARATOR + sequenceStr + KEY_SEPARATOR + orderStr;

				// Agrega un nuevo par clave-valor al mapa de acciones
				actionsMap.put(actionMapKey, processIdStr);

				indiceProcess++;
			}
			indiceAction++;
		}

	}

	/**
	 * Construye y configura cada uno de los procesos especificados en el archivo de configuracion y los ubica en la estructura de procesos y de proxima secuencia (HashTable)
	 */
	private void buildProcesses(Element xmlElement) throws Exception {

		List nextSequenceList, processesList;
		Element nextSequenceElement, processElement;
		String actionTypeStr, nextSequenceIdStr, processIdStr, endingCodeStr, nextSequenceMapKey, orderStr, nextSequenceValueStr;
		int indiceNextSequence, indiceProcess;

		// Levanta los procesos de la estructura XML
		processesList = xmlElement.getChildren("internal_process");
		indiceProcess = 0;

		while (processesList.listIterator(indiceProcess).hasNext()) {
			processElement = (Element) processesList.listIterator(indiceProcess).next();

			// Obtiene los atributos del proceso
			processIdStr = processElement.getAttributeValue("id");
			processIdStr = StringUtilities.toRightAlignedFormat(processIdStr, PROCESS_ID_LENGTH, '0').toString();

			// Levanta los mapeo de secuencias existentes para el proceso
			nextSequenceList = processElement.getChildren("next_sequence");
			indiceNextSequence = 0;

			while (nextSequenceList.listIterator(indiceNextSequence).hasNext()) {
				nextSequenceElement = (Element) nextSequenceList.listIterator(indiceNextSequence).next();

				// Obtiene y mapea los atributos de la proxima secuencia
				actionTypeStr = nextSequenceElement.getAttributeValue("actionType");

				endingCodeStr = nextSequenceElement.getAttributeValue("processEndingCode");
				endingCodeStr = StringUtilities.toRightAlignedFormat(endingCodeStr, ENDING_CODE_LENGTH, '0').toString();

				nextSequenceIdStr = nextSequenceElement.getAttributeValue("nextSequenceId");
				nextSequenceIdStr = StringUtilities.toRightAlignedFormat(nextSequenceIdStr, SEQUENCE_LENGHT, '0').toString();

				orderStr = nextSequenceElement.getAttributeValue("order");
				orderStr = StringUtilities.toRightAlignedFormat(orderStr, ORDER_LENGTH, '0').toString();

				// Genera la clave
				nextSequenceMapKey = processIdStr + KEY_SEPARATOR + actionTypeStr + KEY_SEPARATOR + endingCodeStr;
				nextSequenceValueStr = nextSequenceIdStr + KEY_SEPARATOR + orderStr;

				nextSequenceMap.put(nextSequenceMapKey, nextSequenceValueStr);
				indiceNextSequence++;
			}

			// Crea el proceso y lo agrega al mapa de esta misma clase
			processesMap.put(processIdStr, createProcess(processElement));

			indiceProcess++;
		}

	}

	/** Construye y configura un proceso en base a una configuracion en XML */
	private SequencedProcess createProcess(Element aProcessElement) throws Exception {
		String className = null;
		SequencedProcess aProcess;

		className = aProcessElement.getAttributeValue("className");
		ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();

		aProcess = (SequencedProcess) ctx.getBean(className);

		// Configura y alista el proceso para su ejecución
		aProcess.configure(aProcessElement);

		return (SequencedProcess) aProcess;
	}

	/**
	 * Devuelve el primer proceso a ejecutar para un determinado tipo de accion o transaccion
	 */
	public SequencedProcess getFirstProcess(String tipoAccionStr) throws Exception {

		SequencedProcess originalProcess, newProcess = null;
		String actionIdentifierKey, actionId;

		// Obtiene el ID de la acción a ejecutarse

		String orderString = StringUtilities.toRightAlignedFormat("1", ORDER_LENGTH, '0').toString();
		String sequenceString = StringUtilities.toRightAlignedFormat("1", SEQUENCE_LENGHT, '0').toString();

		actionIdentifierKey = tipoAccionStr + KEY_SEPARATOR + sequenceString + KEY_SEPARATOR + orderString; // "01_01" se debe a que necesito el primer proceso de la primera secuencia
		actionId = actionsMap.get(actionIdentifierKey);

		// Obtiene una copia del proceso
		originalProcess = processesMap.get(actionId);
		newProcess = (SequencedProcess) originalProcess.clone();

		// Realiza las configuraciones finales
		newProcess.setSequenceFromString(sequenceString);
		newProcess.setOrderFromString(orderString);

		return newProcess;
	}

	/**
	 * Devuelve el siguiente proceso a ejecutar para un determinado tipo de accion o transaccion y el proceso ejecutado
	 */
	public SequencedProcess getNextProcess(String tipoAccionStr, SequencedProcess aProcess) throws Exception {

		SequencedProcess originalProcess, newProcess = null;
		String nextSequenceKey, actionIdentifierKey, actionId, nextSequenceOrderStr;
		String nextSequenceStr, nextOrderStr;

		try {
			// Obtengo la proxima secuencia a ejecutar
			nextSequenceKey = aProcess.getProcessId() + KEY_SEPARATOR + tipoAccionStr + KEY_SEPARATOR + aProcess.getEndingCodeAsString();

			// La proxima secuencia está compuesta de secuencia_orden
			nextSequenceOrderStr = nextSequenceMap.get(nextSequenceKey);

			if (nextSequenceOrderStr == null) {
				// Si no hay mapeo de próxima secuencia supone que continúa la secuencia actual
				// De este modo solo hay que configurar los cambios de secuencia
				nextSequenceStr = aProcess.getSequenceAsString();
				// nextOrderStr = StringUtilities.toRightAlignedFormat(String.valueOf( aProcess.getOrder() + 1 ), 2, '0').toString();
				nextOrderStr = StringUtilities.toRightAlignedFormat(String.valueOf(aProcess.getOrder() + 1), ORDER_LENGTH, '0').toString();

			} else {
				nextSequenceStr = nextSequenceOrderStr.split(KEY_SEPARATOR)[0];
				nextOrderStr = nextSequenceOrderStr.split(KEY_SEPARATOR)[1];
			}

			// Obtiene el id de la acción que se debe ejecutar
			actionIdentifierKey = tipoAccionStr + KEY_SEPARATOR + nextSequenceStr + KEY_SEPARATOR + nextOrderStr;
			actionId = actionsMap.get(actionIdentifierKey);

			// Si no hay mapeo de próxima acción finaliza el proceso
			if (actionId == null) {
				newProcess = null;

			} else {
				// Obtiene una copia del proceso a ejecutarse
				originalProcess = processesMap.get(actionId);
				// System.out.println(">> Clave del proceso a ejecutar: " + actionId);

				newProcess = (SequencedProcess) originalProcess.clone();

				// Realiza las configuraciones finales del proceso
				newProcess.setSequenceFromString(nextSequenceStr);
				newProcess.setOrderFromString(nextOrderStr);
			}

			return newProcess;

		} catch (Exception ex) {
			throw ex;
		}
	}

}
