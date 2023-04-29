package com.myspringboot.model.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.myspringboot.model.Estado;

public class EstadoDTO implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=3, max=32, message="O campo exige entre 3 e 32 caracteres")
	private String nome;
	public EstadoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public EstadoDTO(Estado estado) {
		id = estado.getId();
		nome = estado.getNome();
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
		EstadoDTO other = (EstadoDTO) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
