package com.myspringboot.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.myspringboot.model.Categoria;
import com.myspringboot.model.dto.CategoriaDTO;
import com.myspringboot.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaService cs;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> getAll() {
		List<Categoria> categorias = cs.findAll();
		List<CategoriaDTO> categoriasDTO = categorias.
				stream().
				map(categoria -> new CategoriaDTO(categoria)).
									collect(Collectors.toList());
		return ResponseEntity.ok().body(categoriasDTO);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Categoria> get(@PathVariable Integer id) {
		Categoria categoria = cs.find(id);
		return ResponseEntity.ok().body(categoria);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> create(@Valid @RequestBody CategoriaDTO categoriaDTO){
		categoriaDTO.setId(null);
		Categoria categoria = cs.toCategoria(categoriaDTO);
		categoria = cs.save(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO categoriaDTO, @PathVariable Integer id){
		cs.find(id);
		Categoria categoria = cs.toCategoria(categoriaDTO);
		categoria.setId(id);
		cs.save(categoria);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		cs.remove(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/pages", method=RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(value="page", defaultValue="0")  Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Categoria> categorias = cs.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> categoriasDTO = categorias.map(categoria -> new CategoriaDTO(categoria));
		return ResponseEntity.ok().body(categoriasDTO);
	}
	
}
