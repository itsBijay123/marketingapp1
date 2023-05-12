
package com.marketingapp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component   //it will tell springboot you manage the object of this class if autowired in controller layer don't apply its not work.
public class EmailServiceImpl implements EmailService {

	 @Autowired 
	 private JavaMailSender javaMailSender;
	 
	@Override
	public void sendEmail(String to, String sub, String msg) {
		
		// Creating a simple mail message
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(sub);
        mailMessage.setText(msg);
        
        javaMailSender.send(mailMessage);
	}

}
