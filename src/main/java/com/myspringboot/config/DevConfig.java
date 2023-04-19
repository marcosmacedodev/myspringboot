package com.myspringboot.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.myspringboot.services.DBService;
import com.myspringboot.services.EmailService;
import com.myspringboot.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {
	
	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		
		if(!"create".equalsIgnoreCase(strategy)) {
			return false;
		}
		
		dbService.instantiateDevDatabase();
		return true;
	}
	
	@Bean
	public EmailService stmEmailService() {
		return new SmtpEmailService();
	}

}
