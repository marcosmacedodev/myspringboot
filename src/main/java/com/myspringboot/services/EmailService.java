package com.myspringboot.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.myspringboot.model.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido pedido) ;
	
	void sendEmail(SimpleMailMessage smg);
	
	void sendOrderConfirmationHtmlEmail(Pedido obj);
	
	void sendHtmlEmail(MimeMessage msg);

}
