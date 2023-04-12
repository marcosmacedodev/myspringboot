package com.myspringboot.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer id;
	private String nome;
	private String email;
	private String cpfouCnpJ;
	private int tipo;
	
	@OneToMany(mappedBy="cliente")
	private List<Endereco> enderecos;
	
	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cliente(Integer id, String nome, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
	}
	
	public List<Endereco> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
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
		return TipoCliente.contains(tipo);
	}
	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getId();
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(id, other.id);
	}
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", email=" + email + "]";
	}
	
	

}
