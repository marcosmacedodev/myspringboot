package com.myspringboot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myspringboot.model.enums.Perfil;
import com.myspringboot.model.enums.TipoCliente;

@Entity
public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer id;
	private String nome;
	private String email;
	private String cpfouCnpJ;
	private Integer tipo;
	@JsonIgnore
	private String senha;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="PERFIS")
	private Set<Integer> perfis = new HashSet<>();
	
	@OneToMany(mappedBy="cliente", cascade=CascadeType.ALL)
	private List<Endereco> enderecos = new ArrayList<>();
	
	@ElementCollection
	@CollectionTable(name="TELEFONE")
	private Set<String> telefones = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="cliente")
	List<Pedido> pedidos = new ArrayList<>();
	
	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
		addPerfil(Perfil.CLIENTE);
	}
	public Cliente(Integer id, String nome, String email, String cpfouCnpJ, TipoCliente tipo, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfouCnpJ = cpfouCnpJ;
		this.tipo = (tipo == null) ? null : tipo.getId();
		this.senha = senha;
		addPerfil(Perfil.CLIENTE);
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCpfouCnpJ() {
		return cpfouCnpJ;
	}
	public void setCpfouCnpJ(String cpfouCnpJ) {
		this.cpfouCnpJ = cpfouCnpJ;
	}
	public TipoCliente getTipo() {
		return TipoCliente.toEnum(tipo);
	}
	public void setTipo(TipoCliente tipo) {
		this.tipo = (tipo == null) ? null :tipo.getId();
	}
	public List<Endereco> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	public Set<String> getTelefones() {
		return telefones;
	}
	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}
	public List<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Set<Perfil> getPerfis() {
		Set<Perfil> perfis = new HashSet<>();
		for(Integer id : this.perfis) {
			perfis.add(Perfil.toEnum(id));
		}
		return perfis;
	}
	
	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getId());
	}
	
	
}
