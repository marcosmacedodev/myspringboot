package com.myspringboot.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.myspringboot.model.Pedido;
import com.myspringboot.model.dto.PedidoDTO;
import com.myspringboot.services.PedidoService;

@RequestMapping(value="/pedidos")
@RestController
public class PedidoController {
	
	@Autowired
	private PedidoService ps;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<PedidoDTO>> getAll(){
		
		List<Pedido> pedidos = ps.findAll();
		List<PedidoDTO> pedidosDTO = pedidos.stream().map(pedido -> new PedidoDTO(pedido)).collect(Collectors.toList());
		return ResponseEntity.ok().body(pedidosDTO);
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
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Pedido pedido, @PathVariable Integer id){
		ps.find(id);
		pedido.setId(id);
		ps.save(pedido);
		return ResponseEntity.noContent().build();
	}
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		ps.remove(id);
		return ResponseEntity.noContent().build();
	}
}
