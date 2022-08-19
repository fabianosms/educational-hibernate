package com.machado.fabiano.loja.teste;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import com.machado.fabiano.loja.dao.CategoriaDao;
import com.machado.fabiano.loja.dao.ProdutoDao;
import com.machado.fabiano.loja.modelo.Categoria;
import com.machado.fabiano.loja.modelo.CategoriaId;
import com.machado.fabiano.loja.modelo.Produto;
import com.machado.fabiano.loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {

		cadastrarProduto();
		
		EntityManager manager = JPAUtil.criaEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(manager);
		
		Produto produto = produtoDao.buscarPorId(1L);
		System.out.println(produto.getPreco());
		
		List<Produto> lista = produtoDao.buscarTodos();
		lista.forEach(p -> System.out.println(p.getNome() + " - " + p.getDescricao() + " - " + p.getCategoria()));
		
		List<Produto> lista2 = produtoDao.buscarPorNome("Celular");
		lista2.forEach(p2 -> System.out.println(p2.getDescricao()));
		
		List<Produto> lista3 = produtoDao.buscarPorCategoria("INFORMÁTICA");
		lista3.forEach(p3 -> System.out.println(p3.getDescricao()));
		
		BigDecimal preco = produtoDao.buscarPrecoPorDescricaoDoProduto("Mouse sem fio");
		System.out.println(preco);
		
		produtoDao.buscarPorParametros(null, null, LocalDate.now());
		
		produtoDao.buscarPorParametrosComCriteria(null, null, LocalDate.now());
		
		manager.find(Categoria.class, new CategoriaId("CELULARES", "Geral"));
	}

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("CELULARES");
		Categoria informatica = new Categoria("INFORMÁTICA");

		Produto celular = new Produto();

		celular.setNome("Celular");
		celular.setDescricao("Celular Samsung Note 10+");
		celular.setPreco(new BigDecimal("2000"));
		celular.setCategoria(celulares);

		Produto mouse = new Produto("Mouse", "Mouse sem fio", new BigDecimal("50"), informatica);

//		EntityManagerFactory factory = Persistence.createEntityManagerFactory("loja");
//		EntityManager manager = factory.createEntityManager();
		EntityManager manager = JPAUtil.criaEntityManager();

		ProdutoDao produtoDao = new ProdutoDao(manager);
		CategoriaDao categoriaDao = new CategoriaDao(manager);

		manager.getTransaction().begin();
//		manager.persist(celular);
		categoriaDao.cadastrar(celulares);
		categoriaDao.cadastrar(informatica);
		produtoDao.cadastrar(celular);
		produtoDao.cadastrar(mouse);
		manager.getTransaction().commit();
		manager.close();
	}
}
