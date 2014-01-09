package com.conexia.saludcoop.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.conexia.saludcoop.web.controller.ayuda.EnviarMailException;

/**
 * Servicio para enviar un mail.
 * @author crios
 *
 */
@Service("mailService")
public class MailService {
	/**
	 * Clase configurada atravez de spring para enviar un mail personalizado.
	 */
	@Autowired
	private MailSender mailSender;
	/**
	 * Clase para enviar un mail simpe.
	 */
	
	private SimpleMailMessage alertMailMessage;

	/**
	 * Metodo para enviar el mail.
	 * @param body cuerpo del mail.
	 * @throws EnviarMailException
	 */
	public void sendMail(String body) throws EnviarMailException {
		
		SimpleMailMessage message = new SimpleMailMessage(alertMailMessage);
		String mailMesa = alertMailMessage.getTo()[0];
		String [] mailTo = {mailMesa};
		message.setTo(mailTo);
		message.setText(body);
		try {
			mailSender.send(message);
		} catch (Exception e) {
			throw new EnviarMailException(e.getCause()) ;
		}
    
		
	}
	
	/**
	 * Metodo para enviar el mail.
	 * @param subject asunto del mail.
	 * @param body cuerpo del mail
	 * @param to correo/s de los destinatarios del mensaje..
	 * @throws EnviarMailException
	 */
	public void sendMail(String subject, String body, String... to) throws EnviarMailException {
		
		SimpleMailMessage message = new SimpleMailMessage(alertMailMessage);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		try {
			mailSender.send(message);
		} catch (Exception e) {
			throw new EnviarMailException(e.getCause()) ;
		}
    
		
	}
	/**
	 * metodo para enviar el mail, el remitente como el destinatario estan configurado atravez de spring.
	 * @param alert mensaje a enviar.
	 */
	public void sendAlertMail(String alert) {
		
		SimpleMailMessage mailMessage = new SimpleMailMessage(alertMailMessage);
		mailMessage.setText(alert);
		mailSender.send(mailMessage);
		
	}
	
	/**
	 * @return the alertMailMessage
	 */
	public SimpleMailMessage getAlertMailMessage() {
		return alertMailMessage;
	}
	/**
	 * @param alertMailMessage the alertMailMessage to set
	 */
	public void setAlertMailMessage(SimpleMailMessage alertMailMessage) {
		this.alertMailMessage = alertMailMessage;
	}
	
}
