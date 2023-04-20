package com.myspringboot.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.myspringboot.model.Pedido;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;
	
	@Autowired
	private TemplateEngine te;
	
	@Autowired 
	private JavaMailSender jms;
	
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
	
	protected String htmlFromTemplatePedido(Pedido pedido) {
		
		Context context = new Context();
		
		context.setVariable("pedido", pedido);
		
		return te.process("email/confirmacao_pedido", context);
		
	}
	
	@Override
	public void sendOrderConfirmationHtmlEmail(Pedido pedido) {
		// TODO Auto-generated method stub
		try {
			MimeMessage mm = prepareMimeMessageFromPedido(pedido);
			sendHtmlEmail(mm);
		}
		catch(MessagingException e) {
			this.sendOrderConfirmationEmail(pedido);
		}
		
	}

	protected MimeMessage prepareMimeMessageFromPedido(Pedido pedido) throws MessagingException {
		MimeMessage mm = jms.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mm, true);
		
		mmh.setTo(pedido.getCliente().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Pedido confirmado! Código: " + pedido.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplatePedido(pedido));
		
		return mm;
	}
}
