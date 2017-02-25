package quickforms.controller;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	static public String sendEmail(String m_to, String m_subject, String message) throws Exception
	{
		//final String from = d_email;
		//final String password = pwd;
		class SMTPAuthenticator extends Authenticator
		{

			public PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication("lisaandaustin@achamney.com", "thecaptain");
			}
		}

		//String d_uname = "email";
		//String d_password = "password";
		String d_host = "smtp.lycos.com";
		String d_port = "587"; //465,587

		Properties props = new Properties();
		props.put("mail.smtp.user", "lisaandaustin@achamney.com");
		props.put("mail.smtp.host", d_host);
		props.put("mail.smtp.port", d_port);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", d_port);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");

		SMTPAuthenticator auth = new SMTPAuthenticator();
		Session session = Session.getInstance(props, auth);
		session.setDebug(true);

		MimeMessage msg = new MimeMessage(session);

		//msg.setText(message);
		// Send the actual HTML message, as big as you like
        
        msg.setContent(message,"text/html" );
		msg.setSubject(m_subject);
		msg.setFrom(new InternetAddress("lisaandaustin@achamney.com"));
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(m_to));

		Transport transport = session.getTransport("smtps");
		transport.connect(d_host, 465, "lisaandaustin@achamney.com", "thecaptain");
		transport.sendMessage(msg, msg.getAllRecipients());
		transport.close();
		return "{\"status\":\"success\"}";
	}
	public static void main(String[] args){
		try {
			sendEmail(null,"hi","hi");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
