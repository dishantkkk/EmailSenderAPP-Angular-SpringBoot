package com.email.emailapi.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;


@Service
public class EmailService {

	
	
	public boolean sendMail(String subject,String message,String to) {
		boolean f=false;
		
		String from="<gmail-id>";
		String password="<custom app password created from gmail>";
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	    mailSender.setUsername(from);
	    mailSender.setPassword(password);
	   
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
		
		Session session= Session.getInstance(props,new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});
		
		session.setDebug(true);
		
	
		SimpleMailMessage msg = new SimpleMailMessage();
		
		
		MimeMessage m=new MimeMessage(session);
		try {
			msg.setTo(to);
			msg.setSubject(subject);
			msg.setText(message);
			
			mailSender.send(msg);
			f=true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return f;
	}
}
