package com.myspringboot.model;

public enum TipoCliente {
	PESSOA_FISICA(1, "Pessoa Física"), PESSOA_JURIDICA(2, "Pessoa Jurídica");
	
	private int id;
	private String descricao;
	
	private TipoCliente(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	public int getId() {
		return id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoCliente contains(int id) {
		for(TipoCliente tp : TipoCliente.values()) {
			if (tp.id == id)
			{
				return tp;
			}
		}
		return null;
	}
}
