package com.cristiano.marketplace.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="PRODUTO")
public class Produto implements Serializable {

	private static final long serialVersionUID = 4632952524092578395L;
	
	private Long id;
	private String nome;
	private String descricao;
	private Categoria categoria;
	private Long score;
	private Date dataCriacao;
	
	
	public Produto() {
	}
	
	public Produto(Long id, String nome, String descricao, Date dataCriacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.dataCriacao = dataCriacao;
	}

	public Produto(String nome, String descricao, Long categoriaId) {
		this.nome = nome;
		this.descricao = descricao;
		this.categoria = new Categoria(categoriaId);
	}

	@Id
	@Column(name="ID_PRODUTO") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name = "NOME", nullable = false)
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(name = "DESCRICAO", nullable = false)
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    
    @ManyToOne
	@JoinColumn(name = "ID_CATEGORIA", nullable = false)
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
    @Column(name = "SCORE", nullable = true)
    public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	@Column(name = "DATA_CRIACAO", nullable = false)
    public Date getDataCriacao() {
        return dataCriacao;
    }
    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
