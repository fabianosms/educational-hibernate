package com.machado.fabiano.loja.dao;

import javax.persistence.EntityManager;

import com.machado.fabiano.loja.modelo.Cliente;

public class ClienteDao {

	private EntityManager manager;
	
	public ClienteDao(EntityManager manager) {
		this.manager = manager;
	}
	
	public void cadastrar(Cliente cliente) {
		this.manager.persist(cliente);
	}
	
	public Cliente buscarPorId(Long id) {
		return manager.find(Cliente.class, id);
	}
}
