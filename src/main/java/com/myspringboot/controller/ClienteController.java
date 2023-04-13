package com.myspringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myspringboot.model.Cliente;
import com.myspringboot.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService cs;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> GetAll()
	{
		List<Cliente> obj = cs.findAll();
		return ResponseEntity.ok().body(obj);	
	}
	@RequestMapping(value="/{id}")
	public ResponseEntity<?> Get(@PathVariable Integer id)
	{
		Cliente obj = cs.find(id);
		return ResponseEntity.ok().body(obj);
	}

}
