
package com.boots.service;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
	public static void sendMessage(String to, String code) {
        final String from = " ";
		
        final String password = " ";//
		
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.mail.ru");
		properties.put("mail.smtp.user" , from);
		properties.put("mail.smtp.password" , password);
		
		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from,password);
			}
		});
		try {
			Message emailMessage = new MimeMessage(session);
			emailMessage.setText("Ваш пароль для входа:"+code);
			emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			emailMessage.setFrom(new InternetAddress(from));
			emailMessage.setSubject("Пароль для входа");
			Transport.send(emailMessage);
			System.out.println("Sent message");
			
		}catch(Exception e) {e.printStackTrace();}
	}
}
