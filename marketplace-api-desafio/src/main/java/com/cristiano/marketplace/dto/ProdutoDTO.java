package com.cristiano.marketplace.dto;

import com.cristiano.marketplace.domain.Produto;

public class ProdutoDTO {
	
	private String nome;
	private Long categoriaId;
	private String descricao;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getCategoriaId() {
		return categoriaId;
	}
	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	public Produto transformToEntity() {
		Produto produto = new Produto(nome,descricao,categoriaId);
		return produto;
	}

	
	
}
