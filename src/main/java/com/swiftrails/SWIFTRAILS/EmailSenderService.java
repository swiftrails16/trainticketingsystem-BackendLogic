package com.swiftrails.SWIFTRAILS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailSenderService {

	 @Autowired
	    private JavaMailSender javaMailSender;

	    public void sendEmail(String to, String subject, String text) throws MessagingException {
	        jakarta.mail.internet.MimeMessage message = javaMailSender.createMimeMessage();
	        MimeMessageHelper helper;
			try {
				helper = new MimeMessageHelper(message, true);

	        helper.setTo(to);
	        helper.setSubject(subject);
	        helper.setText(text);

	        javaMailSender.send(message);
	    } catch (jakarta.mail.MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

}
