package com.myspringboot.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myspringboot.model.Cidade;
import com.myspringboot.model.Estado;
import com.myspringboot.model.dto.CidadeDTO;
import com.myspringboot.model.dto.EstadoDTO;
import com.myspringboot.services.CidadeService;
import com.myspringboot.services.EstadoService;

@RestController
@RequestMapping(value="/estados")
public class EstadoController {
	
	@Autowired
	private EstadoService es;
	@Autowired
	private CidadeService cs;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> getAll(){
		List<Estado> estados = es.findAll();
		List<EstadoDTO> estadosDTO = estados.stream().map(estado -> new EstadoDTO(estado)).collect(Collectors.toList());
		return ResponseEntity.ok().body(estadosDTO);
	}
	
	@RequestMapping(value="/{id}/cidades", method=RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> getAll(@PathVariable(value="id") Integer estadoId){
		List<Cidade> cidades = cs.findAll(estadoId);
		List<CidadeDTO> cidadesDTO = cidades.stream().map(cidade -> new CidadeDTO(cidade)).collect(Collectors.toList());
		return ResponseEntity.ok().body(cidadesDTO);
	}
}
