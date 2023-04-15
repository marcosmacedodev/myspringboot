package com.myspringboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.myspringboot.model.Cliente;
import com.myspringboot.model.dto.ClienteDTO;
import com.myspringboot.model.enums.TipoCliente;
import com.myspringboot.repositories.ClienteRepository;
import com.myspringboot.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository cr;
	
	public List<Cliente> findAll(){
		return cr.findAll();
	}
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = cr.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	public Cliente save(Cliente cliente) {
		return cr.save(cliente);
	}
	
	public Cliente remove(Integer id) {
		Cliente cliente = find(id);
		cr.delete(cliente);
		return cliente;
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return cr.findAll(pageRequest);
	}
	
	public Cliente toCliente(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), clienteDTO.getCpfouCnpJ(), clienteDTO.getTipo());
	}

}
