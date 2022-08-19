package com.machado.fabiano.loja.modelo;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {

	@EmbeddedId
	private CategoriaId id;
	
	public Categoria() {
	}
	
	public Categoria(String nome) {
		this.id = new CategoriaId(nome, "Geral");
	}
	
	@Override
	public String toString() {
		return String.format("%s", this.id.getNome());
	}
	
}
