package com.myspringboot.model.enums;

public enum EstadoPagamento {

	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private Integer id;
	private String descricao;
	
	private EstadoPagamento(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static EstadoPagamento toEnum(Integer id) {
		if (id == null) return null;
		for(EstadoPagamento ep: EstadoPagamento.values()) {
			if(ep.id.equals(id)) {
				return ep;
			}
		}
		throw new IllegalArgumentException(id + " é inválido");
	}
}
