package com.machado.fabiano.loja.teste;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import com.machado.fabiano.loja.dao.CategoriaDao;
import com.machado.fabiano.loja.dao.ClienteDao;
import com.machado.fabiano.loja.dao.ProdutoDao;
import com.machado.fabiano.loja.modelo.Categoria;
import com.machado.fabiano.loja.modelo.Cliente;
import com.machado.fabiano.loja.modelo.Produto;
import com.machado.fabiano.loja.util.JPAUtil;

public class PopularBancoDeDados {

	public static void popularBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES");
		Categoria informatica = new Categoria("INFORMÁTICA");
		Categoria videogames = new Categoria("VIDEOGAMES");

		Produto celular = new Produto();

		celular.setNome("Celular");
		celular.setDescricao("Celular Samsung Note 10+");
		celular.setPreco(new BigDecimal("2000"));
		celular.setCategoria(celulares);

		Produto mouse = new Produto("Mouse", "Mouse sem fio", new BigDecimal("50"), informatica);
		Produto videogame = new Produto("Mega Drive", "Mega Drive retrô", new BigDecimal("1500"), videogames);
		
		Cliente cliente = new Cliente("Fabiano", "123.456.789-10");
		
		EntityManager manager = JPAUtil.criaEntityManager();
		
		ProdutoDao produtoDao = new ProdutoDao(manager);
		CategoriaDao categoriaDao = new CategoriaDao(manager);
		ClienteDao clienteDao = new ClienteDao(manager);

		manager.getTransaction().begin();
		
		clienteDao.cadastrar(cliente);
		
		categoriaDao.cadastrar(celulares);
		categoriaDao.cadastrar(informatica);
		categoriaDao.cadastrar(videogames);
		
		produtoDao.cadastrar(celular);
		produtoDao.cadastrar(mouse);
		produtoDao.cadastrar(videogame);
		
		manager.getTransaction().commit();
		manager.close();
	}
}
