package com.myspringboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myspringboot.model.Endereco;
import com.myspringboot.repositories.EnderecoRepository;
import com.myspringboot.services.exceptions.ObjectNotFoundException;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository er;
	
	public List<Endereco> findAll() {
		return er.findAll();
	}
	
	public Endereco find(Integer id) {
		
		return er.findById(id).
				orElseThrow(() -> new 
						ObjectNotFoundException("Objeto não encontrado! Id: " + id 
								+ ", Tipo: " + Endereco.class.getName()));
	}
	
	public Endereco insert(Endereco endereco) {
		return er.save(endereco);
	}
	
	public List<Endereco> insertAll(List<Endereco> enderecos){
		return er.saveAll(enderecos);
	}
}
