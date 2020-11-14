package com.cristiano.marketplace.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ITEM_VENDA")
public class ItemVenda implements Serializable  {

	private static final long serialVersionUID = 1559081622732455448L;
	
	private Long id;
	private Venda venda;
	private Produto produto;
	private Integer quantidade;
	private BigDecimal valor;
	
	@Id
	@Column(name="ID_ITEM_VENDA") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name = "ID_VENDA", nullable = false)
	public Venda getVenda() {
		return venda;
	}
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
	@ManyToOne
	@JoinColumn(name = "ID_PRODUTO", nullable = false)
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	@Column(name = "QUANTIDADE", nullable = false)
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	@Column(name = "VALOR", nullable = false)
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	

	
	

}
