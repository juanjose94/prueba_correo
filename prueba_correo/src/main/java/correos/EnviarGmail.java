package correos;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import credenciales.credenciales;

public class EnviarGmail {

	public static void enviarMensaje(String recipient) throws Exception {

		System.out.println("Voy a enviar el correo");

		String host = "10.5.4.17";
		
		Properties propiedades = new Properties();

		propiedades.put("mail.setup.auth", "true");
		propiedades.put("mail.setup.starttls.enable", "true");
		propiedades.put("mail.setup.host", "smtp.gmail.com");
		propiedades.setProperty("mail.smtp.host", host);
		propiedades.put("mail.setup.port", "25");

		final String cuentaEmail = credenciales.cuenta_correo.getDescripcion();
		final String password = credenciales.contraseña_correo.getDescripcion();

		Session session = Session.getInstance(propiedades, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(cuentaEmail, password);
			}
		});

		Message mensaje = crearMensaje(session, cuentaEmail, recipient);
		Transport.send(mensaje);

		System.out.println("Listo papá");

	}

	public static Message crearMensaje(Session session, String cuentaEmail, String recipient) {
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(cuentaEmail));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			msg.setSubject("Esta es una prueba del asunto del mensaje");
			msg.setText("Esta es una prueba de contenido de texto");
			return msg;
		} catch (SendFailedException mex) {
			System.out.println(recipient + " es una dirección invalida");
		} catch (Exception ex) {
			Logger.getLogger(EnviarGmail.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

}
