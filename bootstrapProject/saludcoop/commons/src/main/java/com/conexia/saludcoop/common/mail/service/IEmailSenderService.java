package com.conexia.saludcoop.common.mail.service;

import java.util.List;

import javax.mail.Multipart;

import org.springframework.stereotype.Service;

import com.conexia.saludcoop.common.mail.entity.EmailAttachment;
import com.conexia.saludcoop.common.mail.exception.CannotSendEmailException;

/**
 * Interfaz del servicio de envío de Emails.
 * 
 * @author Sebastián Matienzo
 */
@Service
public interface IEmailSenderService {

	/**
	 * Realiza el envío de un Email.
	 * 
	 * @param toAddress Dirección destino.
	 * @param subject Asunto.
	 * @param body Cuerpo del Email.
	 * @throws CannotSendEmailException En caso de problemas al enviar el Email.
	 */
	void sendEmail(final String toAddress, final String subject, 
			final String body) throws CannotSendEmailException;

	/**
	 * Realiza el envío de un Email.
	 * 
	 * @param toAddress Dirección destino.
	 * @param subject Asunto.
	 * @param body Cuerpo del Email.
	 * @param attachments Listado de adjuntos en forma de byte arrays.
	 * @throws CannotSendEmailException En caso de problemas al enviar el Email.
	 */
	void sendEmail(final String toAddress, final String subject, final String body, 
			final List<EmailAttachment> attachments) throws CannotSendEmailException;

	/**
	 * Envía un un correo electrónico recibiendo el {@link Multipart}.
	 * 
	 * @param toAddress Dirección destino.
	 * @param subject Asunto.
	 * @param multipart El contenido del email.
	 * @throws CannotSendEmailException En caso de problemas enviando el e-mail.
	 */
	void sendEmail(final String toAddress, final String subject, 
			final Multipart multipart) throws CannotSendEmailException;
}
