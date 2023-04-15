package com.myspringboot.model.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

public class ClienteNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e/ou 80 caracteres.")
	private String nome;
	@Email(message="E-mail inválido")
	private String email;
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=11, max=14, message="O tamanho deve ser entre 11 e/ou 14 caracteres.")
	private String cpfouCnpJ;
	@Range(min=1, max=2, message="Só é permitido valores entre 1 (Pessoa Física) e 2 (Pessoa jurídica)")
	private Integer tipo;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e/ou 80 caracteres.")
	private String logradouro;
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=1, max=16, message="O tamanho deve ser entre 1 e/ou 16 caracteres.")
	private String numero;
	private String complemento;
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=32, message="O tamanho deve ser entre 5 e/ou 32 caracteres.")
	private String bairro;
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=8, max=12, message="O tamanho deve ser entre 8 e/ou 12 caracteres.")
	private String cep;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=8, max=14, message="O tamanho deve ser entre 8 e/ou 14 caracteres.")
	private String telefone1;
	private String telefone2;
	private String telefone3;
	
	@Positive(message="Somente valores positivos")
	private Integer cidadeId;

	public ClienteNewDTO() {
		super();
		// TODO Auto-generated constructor stub
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

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}
	
	
	

}
