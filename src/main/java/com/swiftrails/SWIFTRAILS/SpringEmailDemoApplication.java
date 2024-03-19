package com.swiftrails.SWIFTRAILS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import javax.mail.MessagingException;

@SpringBootApplication
@ComponentScan(basePackages = "com.swiftrails.SWIFTRAILS")
public class SpringEmailDemoApplication {

	@Autowired
	private EmailSenderService senderService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringEmailDemoApplication.class, args);
	}
	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() throws MessagingException {
		senderService.sendEmail("monilmody9@gmail.com",
				"This is email body",
				"This is email subject");

	}
}
