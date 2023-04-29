package com.myspringboot.model.dto;

import java.io.Serializable;
import java.util.Objects;

import com.myspringboot.model.Cidade;

public class CidadeDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	public CidadeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CidadeDTO(Cidade cidade) {
		id = cidade.getId();
		nome = cidade.getNome();
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
		CidadeDTO other = (CidadeDTO) obj;
		return Objects.equals(id, other.id);
	}
}
