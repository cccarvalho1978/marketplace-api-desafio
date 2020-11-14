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
@Table(name = "NOTICIA_CATEGORIA")
public class NoticiaCategoria implements Serializable  {

	private static final long serialVersionUID = 1559081622732455448L;
	
	private Long id;
	private Categoria categoria;
	private Date dataPesquisa;
	private Integer totalNoticia;
	
	@Id
	@Column(name="ID_NOTICIA_CATEGORIA") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
    @ManyToOne
	@JoinColumn(name = "ID_CATEGORIA", nullable = false)
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	@Column(name = "DATA_PESQUISA", nullable = false)
    public Date getDataPesquisa() {
        return dataPesquisa;
    }
    public void setDataPesquisa(Date dataPesquisa) {
        this.dataPesquisa = dataPesquisa;
    }
    
	@Column(name = "TOTAL_NOTICIA", nullable = false)
	public Integer getTotalNoticia() {
		return totalNoticia;
	}
	public void setTotalNoticia(Integer totalNoticia) {
		this.totalNoticia = totalNoticia;
	}
	
}
