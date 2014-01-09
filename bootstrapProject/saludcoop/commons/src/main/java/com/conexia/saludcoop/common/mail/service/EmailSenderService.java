package com.conexia.saludcoop.common.mail.service;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.conexia.saludcoop.common.mail.entity.EmailAttachment;
import com.conexia.saludcoop.common.mail.entity.EmailServerConfiguration;
import com.conexia.saludcoop.common.mail.exception.CannotSendEmailException;

/**
 * Realiza el envío de Emails.
 * 
 * @author Sebastián Matienzo
 */
@Service
public class EmailSenderService implements IEmailSenderService {
	
	/**
	 * Proveedor de configuración de envío de correo electrónico.
	 */
	@Autowired
	private EmailSenderConfigurationProvider configurationProvider;

	@Override
	public final void sendEmail(final String toAddress, final String subject, 
			final String body) throws CannotSendEmailException {

		this.sendEmail(toAddress, subject, body, null);
	}
	
	@Override
	public void sendEmail(final String toAddress, final String subject, 
			final String body, final List<EmailAttachment> attachments) throws CannotSendEmailException {

		if (toAddress == null || subject == null || body == null) {
			throw new IllegalArgumentException("Todos los parámetros son obligatorios.");
		}

		try {
			/**
			 * Genera una parte del cuerpo de mensaje Mime a enviar. Esto es útil por si también se
			 * quiere adjuntar archivos, o componer el cuerpo mediante varios Htmls (es un BodyPart
			 * por cada elemento del contenido).
			 */
			final MimeBodyPart htmlContent = new MimeBodyPart();
			htmlContent.setContent(body, "text/html");

			final Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(htmlContent);
			
			if (attachments != null) {
				for (EmailAttachment bs : attachments) {
					final MimeBodyPart attachmentPart = new MimeBodyPart();
					final ByteArrayDataSource byteArrayDataSource =
					        new ByteArrayDataSource(bs.getContent(), "application/octet-stream");
					attachmentPart.setDataHandler(new DataHandler(byteArrayDataSource));
					attachmentPart.setFileName(bs.getName());
					multipart.addBodyPart(attachmentPart);
				}
			}

			this.sendEmail(toAddress, subject, multipart);

		} catch (final Exception e) {
			throw new CannotSendEmailException(e.getMessage(), e);
		}
	}

	@Override
	public void sendEmail(final String toAddress, final String subject, 
			final Multipart multipart) throws CannotSendEmailException {
		
		try {
			final EmailServerConfiguration serverConfiguration = this.configurationProvider.getInstance();
			
			/**
			 * Dispone las siguientes propiedades: - host: dirección del servidor SMTP del cual
			 * enviar emails. - starttls: que debe hacer un envío seguro (lo necesita el SMTP de
			 * Gmail que usa Conexia). - auth: para que haga autenticación con usuario y clave en el
			 * envío de Emails.
			 */
			final Properties prop = new Properties();
			prop.put("mail.smtp.host", serverConfiguration.getHostAddress());
			prop.put("mail.smtp.auth", "true");
			prop.setProperty("mail.smtp.port", serverConfiguration.getPort().toString());

			if (serverConfiguration.isSslRequired()) {
				prop.put("mail.smtp.ssl.enable", "true");
				prop.put("mail.smtp.starttls.enable", "true");

				prop.setProperty("mail.smtp.socketFactory.port", serverConfiguration.getPort()
				        .toString());
				prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			}

			/**
			 * Obtiene la sesión de SMTP a usar para enviar correo.
			 */
			final Session smtpSession =
			        Session.getInstance(prop, new SmtpAuthenticator(serverConfiguration));

			/**
			 * Convierto los Strings de direcciones de Email, en direcciones modelo RFC 822. Nota:
			 * RFC 822 es el formato estandar de mensajes de texto de Internet. Un documento
			 * detallado se puede hallar en: http://www.faqs.org/rfcs/rfc822.html
			 */
			final InternetAddress toAddressParsed = new InternetAddress(toAddress);

			/**
			 * Crea un objeto que se utiliza para setear parametros de un mensaje del tipo Mime. El
			 * mismo permite parsear y almacenar el nivel mas alto de los encabezados RFC 822 de un
			 * mensaje.
			 */
			final MimeMessage myMessage = new MimeMessage(smtpSession);

			myMessage.addRecipient(Message.RecipientType.TO, toAddressParsed);
			myMessage.setSentDate(new Date());
			myMessage.setSubject(subject);
			
			myMessage.setFrom(new InternetAddress(serverConfiguration.getUsername()));

			myMessage.setContent(multipart);
			myMessage.setSentDate(new Date());

			Transport.send(myMessage);
		} catch (final Exception e) {
			throw new CannotSendEmailException(e);
		}
	}

	/**
	 * Autenticador de SMTP utilizado para conectarse mediante usuario y clave.
	 * 
	 * @author Sebastián Matienzo
	 * 
	 */
	private class SmtpAuthenticator extends javax.mail.Authenticator {

		/**
		 * Configuración de servidor SMTP.
		 */
		private EmailServerConfiguration smtpServerConfiguration;

		/**
		 * Constructor por defecto.
		 * 
		 * @param smtpServerConfiguration Configuración del servidor SMTP.
		 */
		public SmtpAuthenticator(final EmailServerConfiguration smtpServerConfiguration) {

			this.smtpServerConfiguration = smtpServerConfiguration;
		}

		@Override
		public PasswordAuthentication getPasswordAuthentication() {

			return (new PasswordAuthentication(this.smtpServerConfiguration.getUsername(),
			        this.smtpServerConfiguration.getPassword()));
		}
	}
}
