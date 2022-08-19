package com.machado.fabiano.loja.vo;

import java.time.LocalDate;

public class RelatorioDeVendasVo {

	private String nomeDoProduto;
	private Long quantidadeVendida;
	private LocalDate dataDaUltimaVenda;
	
	public RelatorioDeVendasVo(String nomeDoProduto, Long quantidadeVendida, LocalDate dataDaUltimaVenda) {
		this.nomeDoProduto = nomeDoProduto;
		this.quantidadeVendida = quantidadeVendida;
		this.dataDaUltimaVenda = dataDaUltimaVenda;
	}

	@Override
	public String toString() {
		return "RelatorioDeVendasVo [nomeDoProduto = " + nomeDoProduto + ", quantidadeVendida = " + quantidadeVendida
				+ ", dataDaUltimaVenda = " + dataDaUltimaVenda + "]";
	}
}