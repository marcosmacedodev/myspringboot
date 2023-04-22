package com.myspringboot.model.enums;

public enum Perfil {

	ADMIN(1, "ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE");
	
	private Integer id;
	private String descricao;
	
	private Perfil(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Perfil toEnum(Integer id) {
		
		if(id == null) return null;
		
		for(Perfil perfil: Perfil.values()) {
			if(perfil.id.equals(id)) {
				return perfil;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + id);
	}
}
