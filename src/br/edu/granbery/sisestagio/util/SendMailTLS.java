package br.edu.granbery.sisestagio.util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailTLS {

	private static SendMailTLS instance = new SendMailTLS();

	public static SendMailTLS getInstance() {
		return instance;
	}

	public void SendMail(String vMsg, String vTo, String vTitulo) {
		final String username = "sisestagio@gmail.com";
		final String password = "sisestagio123";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session sessionEmail = Session.getInstance(props, new javax.mail.Authenticator() {

					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {

			Message message = new MimeMessage(sessionEmail);
			
			message.setFrom(new InternetAddress("sisestagio@gmail.com"));
			
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(vTo));
			
			message.setSubject(vTitulo);
			
			message.setText(vMsg);

			Transport.send(message);

			System.out.println("Sucesso");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
