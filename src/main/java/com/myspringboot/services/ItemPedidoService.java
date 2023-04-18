package com.myspringboot.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myspringboot.model.ItemPedido;
import com.myspringboot.repositories.ItemPedidoRepository;
import com.myspringboot.services.exceptions.ObjectNotFoundException;

@Service
public class ItemPedidoService {

	@Autowired
	private ItemPedidoRepository ipr;
	
	public List<ItemPedido> findAll() {
		return ipr.findAll();
	}
	
	public ItemPedido find(Integer id) {
		
		return ipr.findById(id).
				orElseThrow(() -> new 
						ObjectNotFoundException("Objeto não encontrado! Id: " + id 
								+ ", Tipo: " + ItemPedido.class.getName()));
	}
	
	public ItemPedido insert(ItemPedido itemPedido) {
		
		return ipr.save(itemPedido);
	}
	
	public List<ItemPedido> insertAll(Set<ItemPedido> pedidos){
		return ipr.saveAll(pedidos);
	}
}
