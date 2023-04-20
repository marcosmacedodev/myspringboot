package com.myspringboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myspringboot.model.Estado;
import com.myspringboot.repositories.EstadoRepository;
import com.myspringboot.services.exceptions.ObjectNotFoundException;

@Service
public class EstadoService {
	@Autowired
	private EstadoRepository er;
	
	public Estado find(Integer id) {
		Optional<Estado> obj = er.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Estado.class.getName()));
	}
	
	public List<Estado> findAll(){
		return er.findAll();
	}
	
	public Estado insert(Estado estado) {
		estado.setId(null);
		return er.save(estado);
	}
	
	public List<Estado> insertAll(List<Estado> estados){
		return er.saveAll(estados);
	}
}
