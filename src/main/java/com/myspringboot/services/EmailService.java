package com.myspringboot.services;

import org.springframework.mail.SimpleMailMessage;

import com.myspringboot.model.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido pedido) ;
	
	void sendEmail(SimpleMailMessage smg);

}
