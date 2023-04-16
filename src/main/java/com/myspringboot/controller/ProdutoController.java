package com.myspringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myspringboot.controller.utils.Utils;
import com.myspringboot.model.Produto;
import com.myspringboot.model.dto.ProdutoDTO;
import com.myspringboot.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService ps;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value="nome", defaultValue="") String nome,
			@RequestParam(value="categorias", defaultValue="") String categorias,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction){
		
		Page<Produto> produtos = ps.search(Utils.decodeParam(nome), Utils.decodeIntList(categorias), page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> produtosDTO = produtos.map(produto -> new ProdutoDTO(produto));
		return ResponseEntity.ok().body(produtosDTO);
	}

}
