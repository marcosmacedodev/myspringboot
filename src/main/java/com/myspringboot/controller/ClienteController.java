package com.myspringboot.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dropbox.core.DbxApiException;
import com.dropbox.core.DbxException;
import com.myspringboot.model.Cliente;
import com.myspringboot.model.dto.ClienteDTO;
import com.myspringboot.model.dto.ClienteNewDTO;
import com.myspringboot.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService cs;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> getAll()
	{
		List<Cliente> clientes = cs.findAll();
		List<ClienteDTO> clientesDTO = clientes.stream().map(cliente -> new ClienteDTO(cliente)).collect(Collectors.toList());
		return ResponseEntity.ok().body(clientesDTO);	
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> get(@PathVariable Integer id)
	{
		Cliente obj = cs.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> create(@Valid @RequestBody ClienteNewDTO clienteNewDTO){
		Cliente newCliente = cs.toCliente(clienteNewDTO);
		newCliente = cs.insert(newCliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable Integer id){
		Cliente cliente = cs.toCliente(clienteDTO);
		cs.update(cliente, id);
		return ResponseEntity.noContent().build();
	}
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		cs.remove(id);
		return ResponseEntity.noContent().build();
	}
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/pages", method=RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction){
		Page<Cliente> clientes = cs.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> clientesDTO = clientes.map(cliente -> new ClienteDTO(cliente));
		return ResponseEntity.ok().body(clientesDTO);
	}
	
	@RequestMapping(value="/picture", method=RequestMethod.POST)
	public ResponseEntity<Void> upload(@RequestParam("file") MultipartFile file) throws IOException, DbxApiException, DbxException{
	
		URI uri = cs.uploadProfilePicture(file);
		return ResponseEntity.created(uri).build();
	}
}
