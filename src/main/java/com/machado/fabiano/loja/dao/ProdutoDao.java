package com.machado.fabiano.loja.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.machado.fabiano.loja.modelo.Produto;

public class ProdutoDao {

	private EntityManager manager;

	public ProdutoDao(EntityManager manager) {
		this.manager = manager;
	}

	public void cadastrar(Produto produto) {
		this.manager.persist(produto);
	}

	public Produto buscarPorId(Long id) {
		return manager.find(Produto.class, id);
	}

	public List<Produto> buscarTodos() {
		String jpql = "SELECT p FROM Produto p";
		return manager.createQuery(jpql, Produto.class).getResultList();
	}

	public List<Produto> buscarPorNome(String nome) {
		String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
//		String jpql = "SELECT p FROM Produto p WHERE p.nome LIKE CONCAT('%', :nome, '%')";
		return manager.createQuery(jpql, Produto.class).setParameter("nome", nome).getResultList();
	}

	public List<Produto> buscarPorCategoria(String nome) {
//		String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome";
//		String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = ?1";
//		return manager.createQuery(jpql, Produto.class)
		return manager.createNamedQuery("Produto.buscarProdutoPorCategoria", Produto.class).setParameter("nome", nome)
//				.setParameter(1, nome)
				.getResultList();
	}

	public BigDecimal buscarPrecoPorDescricaoDoProduto(String descricao) {
		String jpql = "SELECT p.preco FROM Produto p WHERE p.descricao = :descricao";
		return manager.createQuery(jpql, BigDecimal.class).setParameter("descricao", descricao).getSingleResult();
	}

	public List<Produto> buscarPorParametros(String nome, BigDecimal preco, LocalDate dataDeCadastro) {

			String jpql = "SELECT p FROM Produto p WHERE 1=1 ";
			
			if (nome != null && !nome.trim().isEmpty()) { 
				jpql += "AND p.nome = :nome ";
			}
			if (preco != null) {
				jpql += "AND p.preco = :preco ";
			}
			if (dataDeCadastro != null) {
				jpql += "AND p.dataDeCadastro = :dataDeCadastro";
			}
			
			TypedQuery<Produto> query = manager.createQuery(jpql, Produto.class);
			
			if (nome != null && !nome.trim().isEmpty()) {
				query.setParameter("nome", nome);
			}
			if (preco != null) {
				query.setParameter("preco", preco);
			}
			if (dataDeCadastro != null) {
				query.setParameter("dataDeCadastro", dataDeCadastro);
			}
			
			return query.getResultList();
	}
	
	public List<Produto> buscarPorParametrosComCriteria(String nome, BigDecimal preco, LocalDate dataDeCadastro) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
		Root<Produto> from = query.from(Produto.class);
		
		Predicate filtros = builder.and();
		
		if (nome != null && !nome.trim().isEmpty()) {
			filtros = builder.and(filtros, builder.equal(from.get("nome"), nome));
		}
		if (preco != null) {
			filtros = builder.and(filtros, builder.equal(from.get("preco"), preco));
		}
		if (dataDeCadastro != null) {
			filtros = builder.and(filtros, builder.equal(from.get("dataDeCadastro"), dataDeCadastro));
		}
		
		query.where(filtros);
		
		return manager.createQuery(query).getResultList();
	}
}
