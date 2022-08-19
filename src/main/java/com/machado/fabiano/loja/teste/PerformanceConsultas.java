package com.machado.fabiano.loja.teste;

import javax.persistence.EntityManager;

import com.machado.fabiano.loja.modelo.Pedido;
import com.machado.fabiano.loja.util.JPAUtil;

public class PerformanceConsultas {

	public static void main(String[] args) {
		
		PopularBancoDeDados.popularBancoDeDados();
		
		EntityManager manager = JPAUtil.criaEntityManager();
		
		Pedido pedido = manager.find(Pedido.class, 1L);
		System.out.println(pedido.getData());
	}
}
