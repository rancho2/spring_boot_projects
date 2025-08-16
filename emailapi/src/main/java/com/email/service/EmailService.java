package com.email.service;

import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import javax.mail.Transport;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	public boolean sendEmail(String subject,String message,String to)
	{
		boolean f=false;
		String from="richa15oct@gmail.com";
		//variable for gmail
		String host="smtp.gmail.com";
		
		//get the system properties
		Properties properties = System.getProperties();
		System.out.println("PROPERTIES "+properties);
		
		//setting important information to properties object
		//host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		//step 1:to get the Session object...
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("richa15oct@gmail.com", "yxhi nfol ibuv hbfa");
				//return new PasswordAuthentication("richa15oct@gmail.com", "***");
			}
		});
		session.setDebug(true);
		
		//Step 2: compose the message [text, multi media]
		MimeMessage m = new MimeMessage(session);
		try {
			//from email
			m.setFrom(from);
			
			//adding recipient to message
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			//adding subject to message
			m.setSubject(subject);
			
			//attachment
			
			//file path
			String path="C:\\Users\\Richa Mishra\\Downloads\\contact_logo.png";
			
			//text
			//file
			
			MimeBodyPart textMime = new MimeBodyPart();
			
			MimeBodyPart fileMime = new MimeBodyPart();
			
			MimeMultipart mimeMultipart = new MimeMultipart();
			
			try {
				textMime.setText(message);
				File file = new File(path);
				fileMime.attachFile(file);
				
				mimeMultipart.addBodyPart(textMime);
				mimeMultipart.addBodyPart(fileMime);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
			m.setContent(mimeMultipart);
			
			//send
			
			//Step 3 : send the message using Transport class
			
			Transport.send(m);
			System.out.println("Sent success..............................");
			f=true;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return f;
	}
	
}
