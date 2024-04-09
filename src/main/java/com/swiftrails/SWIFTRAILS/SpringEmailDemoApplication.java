package com.swiftrails.SWIFTRAILS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.mail.MessagingException;

@SpringBootApplication
@EnableAutoConfiguration
//@ComponentScan(basePackages = {"com.swiftrails.SWIFTRAILS"})
//@EnableJpaRepositories(basePackages="com.swiftrails.SWIFTRAILS.repositories")
public class SwiftrailsSpringBootApplication {

//	@Autowired
//	private EmailSenderService senderService;
	
	public static void main(String[] args) {
		SpringApplication.run(SwiftrailsSpringBootApplication.class, args);
	}

}
