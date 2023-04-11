package com.myspringboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myspringboot.model.Categoria;
import com.myspringboot.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository cr;
	
	public List<Categoria> buscar(){
		return cr.findAll();
	}
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> cat = cr.findById(id);
		return cat.orElse(null);	
	}

}
