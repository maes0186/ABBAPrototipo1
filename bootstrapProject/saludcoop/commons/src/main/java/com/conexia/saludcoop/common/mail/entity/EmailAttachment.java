package com.conexia.saludcoop.common.mail.entity;

/**
 * Representa un adjunto de un correo electrónico.
 * 
 * @author Sebastián Matienzo
 */
public class EmailAttachment {

	/**
	 * Nombre del adjunto.
	 */
	private String name;

	/**
	 * Contenido del adjunto.
	 */
	private byte[] content;
	
	/**
	 * Constructor por defecto.
	 */
	public EmailAttachment() {
		
	}
	
	/**
	 * Constructor completo.
	 * 
	 * @param name Nombre.
	 * @param content Contenido.
	 */
	public EmailAttachment(final String name, final byte[] content) {
		
		this.name = name;
		this.content = content;
	}

	/**
	 * Devuelve el valor de name.
	 * 
	 * @return El valor de name.
	 */
	public String getName() {

		return (this.name);
	}

	/**
	 * Asigna un nuevo valor a name.
	 * 
	 * @param name El valor a asignar a name.
	 */
	public void setName(final String name) {

		this.name = name;
	}

	/**
	 * Devuelve el valor de content.
	 * 
	 * @return El valor de content.
	 */
	public byte[] getContent() {

		return (this.content);
	}

	/**
	 * Asigna un nuevo valor a content.
	 * 
	 * @param content El valor a asignar a content.
	 */
	public void setContent(final byte[] content) {

		this.content = content;
	}
}
