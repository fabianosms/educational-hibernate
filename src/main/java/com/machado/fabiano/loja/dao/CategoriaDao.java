package com.machado.fabiano.loja.dao;

import javax.persistence.EntityManager;

import com.machado.fabiano.loja.modelo.Categoria;

public class CategoriaDao {

	private EntityManager manager;
	
	public CategoriaDao(EntityManager manager) {
		this.manager = manager;
	}
	
	public void cadastrar(Categoria categoria) {
		this.manager.persist(categoria);
	}
	
	private void atualizar(Categoria categoria) {
		this.manager.merge(categoria);
	}
	
	private void remover(Categoria categoria) {
		categoria = manager.merge(categoria);
		this.manager.remove(categoria);
	}
	
}
