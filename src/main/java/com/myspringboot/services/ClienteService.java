package com.myspringboot.services;

//import java.awt.image.BufferedImage;
import java.io.IOException;
//import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.myspringboot.model.Cidade;
import com.myspringboot.model.Cliente;
import com.myspringboot.model.Endereco;
import com.myspringboot.model.dto.ClienteDTO;
import com.myspringboot.model.dto.ClienteNewDTO;
import com.myspringboot.model.enums.Perfil;
import com.myspringboot.model.enums.TipoCliente;
import com.myspringboot.repositories.ClienteRepository;
import com.myspringboot.repositories.EnderecoRepository;
import com.myspringboot.security.UserSS;
import com.myspringboot.services.exceptions.AuthorizationException;
import com.myspringboot.services.exceptions.FileException;
import com.myspringboot.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository cr;
	@Autowired
	private EnderecoRepository er;
	@Autowired
	private BCryptPasswordEncoder bcpe;
	@Autowired
	private DropBoxService dbs;
	@Value("${img.prefix.client.profile}")
	private String prefix;
	@Value("${img.profile.size}")
	private int size;
	//@Autowired
	//private ImageService ims;
	
	public Cliente findByEmail(String email) {
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso não autorizado");
		}
		Cliente cliente = cr.findByEmail(email);
		cliente = find(cliente.getId());
		return cliente;
	}
	
	public List<Cliente> findAll(){
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN))
		{
			throw new AuthorizationException("Acesso não autorizado.");
		}
		return cr.findAll();
	}
	
	public Cliente find(Integer id) {
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId()))
		{
			throw new AuthorizationException("Acesso não autorizado.");
		}
		Optional<Cliente> obj = cr.findById(id);
		Cliente cliente = obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		cliente.setImageUrl(dbs.getLink(cliente.getImageUrl()));
		return cliente;
	}

	@Transactional
	public Cliente insert(Cliente newCliente) {
		newCliente.setId(null);
		newCliente = cr.save(newCliente);
		er.saveAll(newCliente.getEnderecos());
		return newCliente;
	}
	
	public List<Cliente> insertAll(List<Cliente> clientes){
		return cr.saveAll(clientes);
	}
	
	public Cliente update(Cliente cliente, Integer id) {
		Cliente clienteBD = find(id);
		update(clienteBD, cliente);
		return cr.save(clienteBD);
	}
	
	public Cliente update(Cliente cliente) {
		return cr.save(cliente);
	}
	
	private void update(Cliente clienteBD, Cliente cliente) {
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
		Cliente cli = new Cliente(null, clienteNewDTO.getNome(), clienteNewDTO.getEmail(), clienteNewDTO.getCpfouCnpJ(), TipoCliente.toEnum( clienteNewDTO.getTipo()), bcpe.encode(clienteNewDTO.getSenha()));
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
		return new Cliente(null, clienteDTO.getNome(), clienteDTO.getEmail(), null, null, null);
	}
	
	private void check(final MultipartFile multipartFile) {
		if (multipartFile == null) {
			throw new FileException("A requesição está nula.");
		}
		
		if (multipartFile.isEmpty()) {
			throw new FileException("Selecione um arquivo para upload");
		}
		
		String ext = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
		//Long size = multipartFile.getSize();
		String contentType = multipartFile.getContentType();
		
		if (!"image/jpeg".equals(contentType) || !"jpg".equals(ext)) {
			throw new FileException("Somente tipo de conteúdo (image/jpeg) é permitido");
		}
	}
	
	public URI uploadProfilePicture(MultipartFile multipartFile) {
		check(multipartFile);
		UserSS user = UserService.authenticated();
		if(user == null) {
			throw new AuthorizationException("Acesso não autorizado");
		}

		Cliente cliente = find(user.getId());
		try {
			//BufferedImage buf = ims.getBufferedImage(multipartFile);
			//buf = ims.cropSquare(buf);
			//buf = ims.resize(buf, size);
			
			//String filename = prefix + user.getId() + ".jpg";
			String contentType = multipartFile.getContentType();
			//InputStream is = ims.getInputStream(buf, "jpg");
			String url = dbs.upload(multipartFile.getInputStream(), 
					multipartFile.getOriginalFilename(), contentType);
			cliente.setImageUrl(url);
			update(cliente);
			return new URI(dbs.getLink(url));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new FileException("Não foi possível ler o arquivo");
		}catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			throw new FileException("Não foi possível gerar a URI de imagem do cliente");
		}
	}
}
