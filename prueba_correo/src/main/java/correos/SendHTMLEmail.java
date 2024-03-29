package correos;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendHTMLEmail {
	public static void main(String[] args) {
		
		System.out.println("Enviando correo con contenido HTML");
		// Recipient's email ID needs to be mentioned.
		String to = "juan.garcia@confiar.com.co";

		// Sender's email ID needs to be mentioned
		String from = "juan.garcia@confiar.com.co";
		final String username = "juan.garcia@confiar.com.co";// change accordingly
		final String password = "@Pollozilla942019";// change accordingly

		// Assuming you are sending email through relay.jangosmtp.net
		String host = "10.5.4.17";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.ssl.trust", host);
		props.put("mail.smtp.port", "25");

		// Get the Session object.
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// Set Subject: header field
			message.setSubject("Testing Subject");

			// Send the actual HTML message, as big as you like
			message.setContent("<h1>This is actual message embedded in HTML tags</h1>", "text/html");

			// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
