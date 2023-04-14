package com.myspringboot.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.myspringboot.model.Cliente;
import com.myspringboot.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService cs;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> getAll()
	{
		List<Cliente> obj = cs.findAll();
		return ResponseEntity.ok().body(obj);	
	}
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> get(@PathVariable Integer id)
	{
		Cliente obj = cs.find(id);
		return ResponseEntity.ok().body(obj);
	}
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody Cliente cliente){
		cliente.setId(null);
		cliente = cs.save(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody Cliente cliente, @PathVariable Integer id){
		cs.find(id);
		cliente.setId(id);
		cliente = cs.save(cliente);
		return ResponseEntity.ok().body(cliente);
	}
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable Integer id){
		Cliente cliente = cs.remove(id);
		return ResponseEntity.ok().body(cliente);
	}
}
