package com.myspringboot.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemPedido implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@JsonIgnore
	@EmbeddedId	
	private ItemPedidoPK id = new ItemPedidoPK();
	
	private Double desconto;
	private Integer quantidade;
	private Double preco;
	
	public ItemPedido() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ItemPedido(Double desconto, Integer quantidade, Double preco) {
		super();
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}
	
	public void setPedido(Pedido pedido) {
		id.setPedido(pedido);
	}
	
	public void setProduto(Produto produto) {
		id.setProduto(produto);
	}
	@JsonIgnore
	public Pedido getPedido()
	{
		return id.getPedido();
	}
	public Produto getProduto()
	{
		return id.getProduto();
	}
	
	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public ItemPedidoPK getId() {
		return id;
	}

	public void setId(ItemPedidoPK id) {
		this.id = id;
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
		ItemPedido other = (ItemPedido) obj;
		return Objects.equals(id, other.id);
	}

}