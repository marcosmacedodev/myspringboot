package com.myspringboot.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SmtpEmailService extends AbstractEmailService{
	
	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);
	
	@Autowired
	MailSender mailSender;
	

	@Override
	public void sendEmail(SimpleMailMessage smg) {
		// TODO Auto-generated method stub
		LOG.info("Enviando envio de e-mail");
		mailSender.send(smg);
		LOG.info("E-mail enviado");
		
	}

}
