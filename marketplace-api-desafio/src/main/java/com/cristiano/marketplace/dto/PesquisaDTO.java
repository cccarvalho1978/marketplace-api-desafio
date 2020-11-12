package com.cristiano.marketplace.dto;

import java.util.Date;
import java.util.List;

public class PesquisaDTO {

	private Date dataPesquisa;
	private String termoPesquisado;
	private List<ProdutoPesquisaDTO> produtos;
	
	public Date getDataPesquisa() {
		return dataPesquisa;
	}
	public void setDataPesquisa(Date dataPesquisa) {
		this.dataPesquisa = dataPesquisa;
	}
	public String getTermoPesquisado() {
		return termoPesquisado;
	}
	public void setTermoPesquisado(String termoPesquisado) {
		this.termoPesquisado = termoPesquisado;
	}
	public List<ProdutoPesquisaDTO> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<ProdutoPesquisaDTO> produtos) {
		this.produtos = produtos;
	}

}
