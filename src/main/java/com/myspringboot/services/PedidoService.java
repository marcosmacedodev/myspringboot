package com.myspringboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myspringboot.model.Cliente;
import com.myspringboot.model.Pedido;
import com.myspringboot.repositories.PedidoRepository;
import com.myspringboot.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pr;
	
	public List<Pedido> findAll(){
		return pr.findAll();
	}
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = pr.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	public Pedido save(Pedido pedido) {
		return pr.save(pedido);
	}
	
	public Pedido remove(Integer id) {
		Pedido pedido = find(id);
		pr.delete(pedido);
		return pedido;
	}

}
