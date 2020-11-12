package com.cristiano.marketplace.dto;

import java.util.Date;

import com.cristiano.marketplace.domain.Produto;

public class ProdutoPesquisaDTO {
	
	private Long id;
	private String nome;
	private String descricao;
	private Date dataCriacao;
	private Long score;
	
	public ProdutoPesquisaDTO(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.dataCriacao = produto.getDataCriacao();
		this.score = produto.getScore();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public Long getScore() {
		return score;
	}
	public void setScore(Long score) {
		this.score = score;
	}
}
