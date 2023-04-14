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

import com.myspringboot.model.Pedido;
import com.myspringboot.services.PedidoService;

@RequestMapping(value="/pedidos")
@RestController
public class PedidoController {
	
	@Autowired
	private PedidoService ps;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> getAll(){
		
		List<Pedido> pedidos = ps.findAll();
		return ResponseEntity.ok().body(pedidos);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> get(@PathVariable Integer id){
		Pedido pedido = ps.find(id);
		return ResponseEntity.ok().body(pedido);
	}
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody Pedido pedido){
		pedido.setId(null);
		pedido = ps.save(pedido);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedido.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	

}
