package com.myspringboot.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.myspringboot.model.Pedido;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;
	@Override
	public void sendOrderConfirmationEmail(Pedido pedido) {
		// TODO Auto-generated method stub
		SimpleMailMessage smg = prepareSimpleMailMessageFromPedido(pedido);
		sendEmail(smg);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido pedido) {
		// TODO Auto-generated method stub
		SimpleMailMessage smg = new SimpleMailMessage();
		smg.setTo(pedido.getCliente().getEmail());
		smg.setFrom(sender);
		smg.setSubject("Pedido confirmado! Código: " + pedido.getId());
		smg.setSentDate(new Date(System.currentTimeMillis()));
		smg.setText(pedido.toString());
		return smg;
	}

}
