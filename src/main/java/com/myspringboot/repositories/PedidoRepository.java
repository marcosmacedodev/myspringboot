package com.myspringboot.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myspringboot.model.Cliente;
import com.myspringboot.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	
	public Page<Pedido> findAllByCliente(Cliente cliente, Pageable page);

}
