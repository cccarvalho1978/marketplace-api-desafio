package com.cristiano.marketplace.dto;

import javax.validation.constraints.NotNull;

import com.cristiano.marketplace.domain.Produto;

public class ProdutoDTO {
	
	private String nome;
	private Long categoriaId;
	private String descricao;
	
	@NotNull(message = "Nome do Produto não pode ser nulo!")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@NotNull(message = "Categoria não pode ser nulo!")
	public Long getCategoriaId() {
		return categoriaId;
	}
	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}
	
	@NotNull(message = "Descrição do Produto não pode ser nulo!")
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
