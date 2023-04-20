package com.myspringboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.myspringboot.model.Categoria;
import com.myspringboot.model.Produto;
import com.myspringboot.model.dto.ProdutoDTO;
import com.myspringboot.repositories.CategoriaRepository;
import com.myspringboot.repositories.ProdutoRepository;
import com.myspringboot.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository pr;
	@Autowired
	private CategoriaRepository cr;

	public Produto find(Integer id) {
		Optional<Produto> obj = pr.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}
	
	public List<Produto> findAll() {
		return pr.findAll();
	}
	
	public Produto insert(Produto produto) {
		produto.setId(null);
		return pr.save(produto);
	}
	
	public List<Produto> insertAll(List<Produto> produtos){
		return pr.saveAll(produtos);
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = cr.findAllById(ids);
		return pr.search(nome, categorias, pageRequest);
	}
	
	public Page<Produto> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return pr.findAll(pageRequest);
	}
	
	public Produto toProduto(ProdutoDTO produtoDTO) {
		return new Produto(produtoDTO.getId(), produtoDTO.getNome(), produtoDTO.getPreco());
	}
}
