package com.cristiano.marketplace.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cristiano.marketplace.enums.AvaliacaoVenda;

@Entity
@Table(name = "AVALIACAO_ITEM_VENDA")
public class AvaliacaoItemVenda implements Serializable  {

	private static final long serialVersionUID = 1559081622732455448L;
	
	private Long id;
	private ItemVenda itemVenda;
	private AvaliacaoVenda avaliacao;
	
	@Id
	@Column(name="ID_AVALIACAO_ITEM_VENDA") 
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name = "ID_ITEM_VENDA", nullable = false)
	public ItemVenda getItemVenda() {
		return itemVenda;
	}
	public void setItemVenda(ItemVenda itemVenda) {
		this.itemVenda = itemVenda;
	}
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "AVALIACAO", nullable = false)
	public AvaliacaoVenda getAvaliacao() {
		return avaliacao;
	}
	public void setAvaliacao(AvaliacaoVenda avaliacao) {
		this.avaliacao = avaliacao;
	}
	
	
	

	

	
	

}
