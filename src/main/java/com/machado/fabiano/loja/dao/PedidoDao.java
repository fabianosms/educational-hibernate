package com.machado.fabiano.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.machado.fabiano.loja.modelo.Pedido;
import com.machado.fabiano.loja.vo.RelatorioDeVendasVo;

public class PedidoDao {

	private EntityManager manager;
	
	public PedidoDao(EntityManager manager) {
		this.manager = manager;
	}
	
	public void cadastrar(Pedido pedido) {
		this.manager.persist(pedido);
	}
	
	public BigDecimal valorTotalVendido() {
		String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
		return manager.createQuery(jpql, BigDecimal.class).getSingleResult();
	}
	
	public List<RelatorioDeVendasVo> gerarRelatorioDeVendas() {
		String jpql = "SELECT new com.machado.fabiano.loja.vo.RelatorioDeVendasVo("
				+ "produto.nome, "
				+ "SUM(item.quantidade), "
				+ "MAX(pedido.data)) "
				+ "FROM Pedido pedido "
				+ "JOIN pedido.itens item "
				+ "JOIN item.produto produto "
				+ "GROUP BY produto.nome "
				+ "ORDER BY SUM(item.quantidade) DESC";
		return manager.createQuery(jpql, RelatorioDeVendasVo.class)
				.getResultList();
	}
	
	public List<Pedido> buscarTodos() {
		String jpql = "SELECT p FROM Pedido p";
		return manager.createQuery(jpql, Pedido.class).getResultList();
	}
	
	public Pedido buscarPedidoComCliente(Long id) {
		return manager.createQuery("SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = :id", Pedido.class)
				.setParameter("id", id)
				.getSingleResult();
	}
}
