package com.myspringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myspringboot.model.Categoria;
import com.myspringboot.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaService cs;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> getAll() {
		List<Categoria> cats = cs.findAll();
		return ResponseEntity.ok().body(cats);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> get(@PathVariable Integer id) {
		Categoria cat = cs.find(id);
		return ResponseEntity.ok().body(cat);
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody Categoria categoria){
		Categoria cat = cs.save(categoria);
		return ResponseEntity.ok().body(cat);
	}
	
	public ResponseEntity<?> update(@RequestBody Categoria categoria, @PathVariable Integer id)
	{
		return null;
	}
	
	
	
}
