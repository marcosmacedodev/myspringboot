package com.myspringboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myspringboot.model.Cidade;
import com.myspringboot.repositories.CidadeRepository;
import com.myspringboot.services.exceptions.ObjectNotFoundException;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cr;
	@Autowired
	private EstadoService es;;
	
	public Cidade find(Integer id) {

		Optional<Cidade> obj = cr.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cidade.class.getName()));

	}
	
	public List<Cidade> findAll(Integer estadoID){
		
		return cr.findAllByEstado(es.find(estadoID));
	}
	
	public Cidade insert(Cidade cidade) {
		cidade.setId(null);
		return cr.save(cidade);
	}
	
	public List<Cidade> insertAll(List<Cidade> cidades){
		return cr.saveAll(cidades);
	}
}
