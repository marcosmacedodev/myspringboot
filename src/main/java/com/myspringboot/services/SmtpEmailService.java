package com.myspringboot.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SmtpEmailService extends AbstractEmailService{
	
	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);
	
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private JavaMailSender jms;
	

	@Override
	public void sendEmail(SimpleMailMessage smg) {
		// TODO Auto-generated method stub
		LOG.info("Enviando envio de e-mail");
		mailSender.send(smg);
		LOG.info("E-mail enviado");
		
	}
	
	@Override
	public void sendHtmlEmail(MimeMessage mm) {
		LOG.info("Enviando envio de e-mail");
		jms.send(mm);
		LOG.info("E-mail enviado");
	}

}
