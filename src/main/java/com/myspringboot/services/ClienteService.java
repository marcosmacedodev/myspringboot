package com.myspringboot.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myspringboot.model.Cidade;
import com.myspringboot.model.Cliente;
import com.myspringboot.model.Endereco;
import com.myspringboot.model.dto.ClienteDTO;
import com.myspringboot.model.dto.ClienteNewDTO;
import com.myspringboot.model.enums.TipoCliente;
import com.myspringboot.repositories.ClienteRepository;
import com.myspringboot.repositories.EnderecoRepository;
import com.myspringboot.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository cr;
	@Autowired
	private EnderecoRepository er;
	
	public List<Cliente> findAll(){
		return cr.findAll();
	}
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = cr.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	@Transactional
	public Cliente insert(Cliente newCliente) {
		newCliente.setId(null);
		newCliente = cr.save(newCliente);
		er.saveAll(newCliente.getEnderecos());
		return newCliente;
	}
	
	public Cliente update(Cliente cliente, Integer id) {
		Cliente clienteBD = find(id);
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
//		if (cliente.getCpfouCnpJ() != null) {
//			clienteBD.setCpfouCnpJ(cliente.getCpfouCnpJ());
//		}
//		if (cliente.getTipo() != null) {
//			clienteBD.setTipo(cliente.getTipo());
//		}
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
	
	public Cliente toCliente(ClienteNewDTO clienteNewDTO) {
		Cliente cli = new Cliente(null, clienteNewDTO.getNome(), clienteNewDTO.getEmail(), clienteNewDTO.getCpfouCnpJ(), TipoCliente.toEnum( clienteNewDTO.getTipo()));
		Endereco end = new Endereco(null, clienteNewDTO.getLogradouro(), clienteNewDTO.getNumero(), clienteNewDTO.getComplemento(), clienteNewDTO.getBairro(), clienteNewDTO.getCep());
		end.setCliente(cli);
		
		end.setCidade(new Cidade(clienteNewDTO.getCidadeId(), null));
		
		cli.setEnderecos(Arrays.asList(end));
		cli.getTelefones().add(clienteNewDTO.getTelefone1());
		
		if (clienteNewDTO.getTelefone2() != null) cli.getTelefones().add(clienteNewDTO.getTelefone2());
		if (clienteNewDTO.getTelefone3() != null) cli.getTelefones().add(clienteNewDTO.getTelefone3());
		return cli;
	}
	
	public Cliente toCliente(ClienteDTO clienteDTO) {
		return new Cliente(null, clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
	}

}
