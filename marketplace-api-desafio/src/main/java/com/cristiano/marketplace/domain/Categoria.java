package com.cristiano.marketplace.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "CATEGORIA")
public class Categoria implements Serializable  {

	private static final long serialVersionUID = 1559081622732455448L;
	
	private Long id;
	private String nome;
	
	public Categoria() {
		super();
	}
	
	public Categoria(Long categoriaId) {
		this.id = categoriaId;
	}
	
	@Id
	@Column(name="ID_CATEGORIA") 
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

}
