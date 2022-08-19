package com.machado.fabiano.loja.teste;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.machado.fabiano.loja.dao.ClienteDao;
import com.machado.fabiano.loja.dao.PedidoDao;
import com.machado.fabiano.loja.dao.ProdutoDao;
import com.machado.fabiano.loja.modelo.Cliente;
import com.machado.fabiano.loja.modelo.ItemPedido;
import com.machado.fabiano.loja.modelo.Pedido;
import com.machado.fabiano.loja.modelo.Produto;
import com.machado.fabiano.loja.util.JPAUtil;
import com.machado.fabiano.loja.vo.RelatorioDeVendasVo;

public class CadastroDePedido {

	public static void main(String[] args) {

		PopularBancoDeDados.popularBancoDeDados();
		
		EntityManager manager = JPAUtil.criaEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(manager);
		ClienteDao clienteDao = new ClienteDao(manager);
		PedidoDao pedidoDao = new PedidoDao(manager);
		
		Produto produto1 = produtoDao.buscarPorId(1L);
		Produto produto2 = produtoDao.buscarPorId(2L);
		Produto produto3 = produtoDao.buscarPorId(3L);
		Cliente cliente = clienteDao.buscarPorId(1L);

		System.out.println("Aqui ó! " + cliente);
		
		manager.getTransaction().begin();
		
		Pedido pedido1 = new Pedido(cliente);
		pedido1.adicionarItem(new ItemPedido(pedido1, produto1, 2));
		pedido1.adicionarItem(new ItemPedido(pedido1, produto2, 5));
		
		Pedido pedido2 = new Pedido(cliente);
		pedido2.adicionarItem(new ItemPedido(pedido2, produto3, 1));
		pedido2.adicionarItem(new ItemPedido(pedido2, produto2, 1));
		
		Pedido pedido3 = new Pedido(cliente);
		pedido3.adicionarItem(new ItemPedido(pedido3, produto1, 1));
		
		pedidoDao.cadastrar(pedido1);
		pedidoDao.cadastrar(pedido2);
		pedidoDao.cadastrar(pedido3);
		
		manager.getTransaction().commit();
		
		BigDecimal totalVendido = pedidoDao.valorTotalVendido();
		System.out.println("Valor total: " + totalVendido);
		
		List<Pedido> lista = pedidoDao.buscarTodos();
		lista.forEach(p -> System.out.println(p.getCliente() + " - " + p.getValorTotal()));
		
		List<RelatorioDeVendasVo> relatorio = pedidoDao.gerarRelatorioDeVendas();
		relatorio.forEach(System.out::println);
	}
	
}
