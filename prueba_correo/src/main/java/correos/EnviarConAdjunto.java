package correos;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import credenciales.credenciales;

public class EnviarConAdjunto {

	public static void main(String[] args) {
		// Assuming you are sending email from localhost
		
		System.out.println("Enviando correo con adjunto");
		
		String emisor = "juan.garcia@confiar.com.co";

		// Sender's email ID needs to be mentioned
		String receptor = "juan.garcia@confiar.com.co";
		
		String host = credenciales.HOST.getDescripcion();

		// Get system properties
		Properties properties = System.getProperties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.ssl.trust", host);
	    properties.put("mail.smtp.user", "pepitoperez");
	    properties.put("mail.smtp.password", "pepitoperez");
		properties.put("mail.smtp.port", "25");

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);

		final String cuentaEmail = credenciales.cuenta_correo.getDescripcion();
		final String password = credenciales.contraseña_correo.getDescripcion();

		//final String cuentaEmail = "pepitoperez";
		//final String password = "pepitoperez";
		
		// Get the default Session object.
		
		
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(cuentaEmail, password);
			}
		});
		 

		//Session session = Session.getDefaultInstance(properties);
		
		try {
			// Correo normal.
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emisor));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receptor));
			message.setSubject("Prueba Correo con adjunto");

			// Para agregar el contenido del cuerpo
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText("Estamos probando JavaMail con adjuntos");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			// Correo normal con adjuntos

			messageBodyPart = new MimeBodyPart();

			String archivoAdjunto = "C:/Users/juan.garcia/Documents/Información/Tareas/Noviembre";
			
			DataSource source = new FileDataSource(archivoAdjunto);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(archivoAdjunto);
			multipart.addBodyPart(messageBodyPart);
			
			message.setContent(multipart);

			Transport.send(message);

			System.out.println("Sent message successfully....");
			
		}catch (SendFailedException mex) {
			System.out.println(emisor + " es una dirección invalida");
		}catch (MessagingException mex) {
			mex.printStackTrace();
		} 
	}
}
