package com.machado.fabiano.loja.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "itens_pedido")
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer quantidade;
	@Column(name = "preco_unitario")
	private BigDecimal precoUnitario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Produto produto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Pedido pedido;

	public ItemPedido() {
	}

	public ItemPedido(Pedido pedido, Produto produto, Integer quantidade) {
		this.pedido = pedido;
		this.produto = produto;
		this.quantidade = quantidade;
		this.precoUnitario = produto.getPreco();
	}
	
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public BigDecimal getValor() {
		return precoUnitario.multiply(new BigDecimal(quantidade));
	}
}
