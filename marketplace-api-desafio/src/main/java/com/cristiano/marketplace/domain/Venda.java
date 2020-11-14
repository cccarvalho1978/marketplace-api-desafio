package com.cristiano.marketplace.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "VENDA")
public class Venda implements Serializable  {

	private static final long serialVersionUID = 1559081622732455448L;
	
	private Long id;
	private Vendedor vendedor;
	private Comprador comprador;
	private Date dataVenda;
	private List<ItemVenda> itensVenda;
	
	
	
	@Id
	@Column(name="ID_VENDA") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name = "ID_VENDEDOR", nullable = false)
	public Vendedor getVendedor() {
		return vendedor;
	}
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	
	@ManyToOne
	@JoinColumn(name = "ID_COMPRADOR", nullable = false)
	public Comprador getComprador() {
		return comprador;
	}
	public void setComprador(Comprador comprador) {
		this.comprador = comprador;
	}
	
	@Column(name = "DATA_VENDA", nullable = false)
	public Date getDataVenda() {
		return dataVenda;
	}
	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}
	
	@OneToMany(mappedBy = "venda")
	public List<ItemVenda> getItensVenda() {
		return itensVenda;
	}
	public void setItensVenda(List<ItemVenda> itensVenda) {
		this.itensVenda = itensVenda;
	}
	
	

}
