import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import fi.webshop.email.MyMailSender;




public class SendEmail {
	
	
	
	public static void main(String[] args) throws IOException {
		
		
		
		
		final MyMailSender mailSender = new MyMailSender();
		
		      // Recipient's email ID needs to be mentioned.
		    String to = "jussi.isokangas@kolumbus.fi";

		      // Sender's email ID needs to be mentioned
		      String from = "jussi.isokangas@gmail.com";

		      // Assuming you are sending email from localhost
		      String host = "smtp.gmail.com";
		      Properties properties = new Properties();
		      // Setup mail server
		      properties.setProperty("mail.smtp.host", host);
		      
		     
				properties.put("mail.smtp.socketFactory.port", "465");
				properties.put("mail.smtp.socketFactory.class",
						"javax.net.ssl.SSLSocketFactory");
				properties.put("mail.smtp.auth", "true");
				properties.put("mail.smtp.port", "465");

		      // Get system properties
		      Session session = Session.getDefaultInstance(properties,
		  			new javax.mail.Authenticator() {
		  				protected PasswordAuthentication getPasswordAuthentication() {
		  					return new PasswordAuthentication(mailSender.getUsername(),mailSender.getPassword());
		  				}
		  			});      

		   
		      try{
		         // Create a default MimeMessage object.
		         MimeMessage message = new MimeMessage(session);

		         // Set From: header field of the header.
		         message.setFrom(new InternetAddress(from));

		         // Set To: header field of the header.
		         message.addRecipient(Message.RecipientType.TO,
		                                  new InternetAddress(to));

		         // Set Subject: header field
		         message.setSubject("This is the Subject Line!");

		         // Now set the actual message
		         message.setText("This is actual message");

		         // Send message
		         Transport.send(message);
		         System.out.println("Sent message successfully....");
		      }catch (MessagingException mex) {
		         mex.printStackTrace();
		      }
	
		
	
		
		
		
	
		
	}
}
		
		
		
		
		
		   
	     

