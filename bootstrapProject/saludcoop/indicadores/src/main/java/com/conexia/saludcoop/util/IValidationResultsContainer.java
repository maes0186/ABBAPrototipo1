package com.conexia.saludcoop.util;

/**
 * Interfaz que debe implementar todo contenedor de validación de resultados.
 * 
 * @author Sebastián Matienzo
 */
public interface IValidationResultsContainer {

	/**
	 * Agrega un mensaje de validación a un campo de un formulario.
	 * 
	 * @param object Campo al cual agregar el mensaje.
	 * @param message Mensaje a agregar.
	 */
	void addFieldValidationMessage(String object, String message);

	/**
	 * Agrega un mensaje de validación general a un formulario.
	 * 
	 * @param message Mensaje a agregar.
	 */
	void addGeneralValidationMessage(String message);

	/**
	 * Verifica si hay al menos un error.
	 * 
	 * @return True si hay al menos un error (sin importar si es general o por campo); caso
	 *         contrario, False.
	 */
	boolean containsErrors();
}
