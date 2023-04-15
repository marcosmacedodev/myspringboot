package com.myspringboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.myspringboot.model.Cliente;
import com.myspringboot.model.Pedido;
import com.myspringboot.model.dto.PedidoDTO;
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
	
	public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return pr.findAll(pageRequest);
	}
	
	public Pedido toPedido(PedidoDTO pedidoDTO) {
		return new Pedido(pedidoDTO.getId(), pedidoDTO.getInstante());
	}

}
