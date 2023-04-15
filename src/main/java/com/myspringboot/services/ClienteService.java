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

	public Cliente create(Cliente newCliente) {
		newCliente.setId(null);
		return cr.save(newCliente);
	}
	
	public Cliente update(Cliente cliente, Integer id) {
		Cliente clienteBD = find(id);
		cliente.setId(id);
		validarCliente(clienteBD, cliente);
		return cr.save(clienteBD);
	}
	
	private void validarCliente(Cliente clienteBD, Cliente cliente) {
		if(cliente.getNome() != null) {
			clienteBD.setNome(cliente.getNome());
		}
		if (cliente.getEmail() != null) {
			clienteBD.setEmail(cliente.getEmail());
		}
		if (cliente.getCpfouCnpJ() != null) {
			clienteBD.setCpfouCnpJ(cliente.getCpfouCnpJ());
		}
		if (cliente.getTipo() != null) {
			clienteBD.setTipo(cliente.getTipo());
		}
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
