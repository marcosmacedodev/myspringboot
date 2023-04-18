package com.myspringboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myspringboot.model.Pagamento;
import com.myspringboot.repositories.PagamentoRepository;
import com.myspringboot.services.exceptions.ObjectNotFoundException;

@Service
public class PagamentoService {

	@Autowired
	private PagamentoRepository pr;
	
	public List<Pagamento> findAll() {
		return pr.findAll();
	}
	
	public Pagamento find(Integer id) {
		
		return pr.findById(id).
				orElseThrow(() -> new 
						ObjectNotFoundException("Objeto não encontrado! Id: " + id 
								+ ", Tipo: " + Pagamento.class.getName()));
	}
	
	public Pagamento insert(Pagamento pagamento) {
		
		return pr.save(pagamento);
	}
}
