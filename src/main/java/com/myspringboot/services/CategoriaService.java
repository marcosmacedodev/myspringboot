package com.myspringboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myspringboot.model.Categoria;
import com.myspringboot.repositories.CategoriaRepository;
import com.myspringboot.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository cr;
	
	public List<Categoria> findAll()
	{
		return cr.findAll();
	}
	
	public Categoria find(Integer id) 
	{
		Optional<Categoria> cat = cr.findById(id);
		return cat.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));	
	}
	
	public Categoria save(Categoria categoria) 
	{
		return cr.save(categoria);
	}
	

	
	public Categoria remove(Integer id){
		Categoria categoria = find(id);
		cr.delete(categoria);
		return categoria;
	}

}
