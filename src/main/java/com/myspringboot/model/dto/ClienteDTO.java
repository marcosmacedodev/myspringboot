package com.myspringboot.model.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.myspringboot.model.Cliente;
import com.myspringboot.model.enums.TipoCliente;

public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotEmpty(message="Preenchimento obrigatório.")
	@Length(min=5,max=80, message="O tamanho deve ser entre 5 e 80 caracteres.")
	private String nome;
	@NotEmpty(message="Preenchimento obrigatório.")
	@Email(message="E-mail inválido")
	private String email;
	private String cpfouCnpJ;
	private Integer tipo;
	
	public ClienteDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ClienteDTO(Cliente cliente) {
		super();
		id = cliente.getId();
		nome = cliente.getNome();
		email = cliente.getEmail();
		cpfouCnpJ = cliente.getCpfouCnpJ();
		tipo = cliente.getTipo().getId();
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
		ClienteDTO other = (ClienteDTO) obj;
		return Objects.equals(id, other.id);
	}
}
