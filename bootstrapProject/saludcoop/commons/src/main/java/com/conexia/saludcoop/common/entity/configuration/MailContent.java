package com.conexia.saludcoop.common.entity.configuration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.conexia.common.persistence.entity.Identifiable;

/**
 * Representa el contenido de un correo a ser enviado por el sistema.
 * 
 * @author Julio Sejtman
 * 
 */
@Entity
@Table(name = "mail_content", schema = "configuracion")
public class MailContent implements Identifiable<Long> {

	public static final Long AVISO_DIRECTOR_REGIONAL_NO_IPS_PARA_DIRECCIONAR_PROCEDIMIENTO = 1l;
	
	/**
	 * Identificador de la entidad.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Texto del cuerpo del mensaje el correo.
	 */
	@Column(name = "body", nullable = false, length = 700)
	private String body;

	/**
	 * Asunto del correo.
	 */
	@Column(name = "subject", nullable = false, length = 100)
	private String subject;

	public Long getId() {
		return id;
	}

	protected void setId(Long id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
}
