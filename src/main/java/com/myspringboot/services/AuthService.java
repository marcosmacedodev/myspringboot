package com.myspringboot.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.myspringboot.model.Cliente;
import com.myspringboot.repositories.ClienteRepository;
import com.myspringboot.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {
	
	
	@Autowired
	private ClienteRepository cr;
	
	@Autowired
	private BCryptPasswordEncoder bcpe;
	
	@Autowired
	EmailService es;
	
	Random rand = new Random();
	
	public void sendNewPassword(String email) {
		
		Cliente cliente = cr.findByEmail(email);
		
		if (cliente == null) {
			throw new ObjectNotFoundException("E-mail não encontrado!");
		}
		
		String novaSenha = newPassword();
		cliente.setSenha(bcpe.encode(novaSenha));
		
		cliente = cr.save(cliente);
		es.sendNewPasswordEmail(cliente, novaSenha);
	}

	private String newPassword() {
		char[] vet = new char[10];

		for(int it = 0; it < vet.length; it++) {
			vet[it] = randomChar();
		}
		return new String(vet);
	}
	
	private char randomChar(){
		
		byte v = (byte) rand.nextInt(3); 
		switch(v) 
		{
			//Gera um letra maíscula
			case 0: return (char) rand.nextInt(65, 91);
			//Gera uma letra minúscula
			case 1: return (char) rand.nextInt(97, 123);
			//Gera um digito entre 0..9
			case 2: return (char) rand.nextInt(48, 58);
		}
		return 0;
	}
}
