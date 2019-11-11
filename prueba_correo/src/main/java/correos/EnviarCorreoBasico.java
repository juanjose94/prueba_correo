package correos;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import credenciales.credenciales;

public class EnviarCorreoBasico {

	public static void main(String[] args) {

		// Recipient's email ID needs to be mentioned.
		String to = "juan.garcia@confiar.com.co";

		// Sender's email ID needs to be mentioned
		String from = "juan.garcia@confiar.com.co";

		// Assuming you are sending email from localhost
		String host = credenciales.HOST.getDescripcion();

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.put("mail.smtp.host", host);
		properties.setProperty("mail.smtp.host", host);

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject("Prueba Correo");

			// Now set the actual message
			message.setText("Estamos probando JavaMail");

			// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");
		} catch (SendFailedException mex) {
			System.out.println(to + " es una dirección invalida");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}
