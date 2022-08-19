package com.machado.fabiano.loja.modelo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "livros")
public class Livro extends Produto {

	private String autor;
	private Integer numeroDePaginas;
	
	public Livro() {
	}
	
	Livro(String autor, Integer numeroDePaginas) {
		this.autor = autor;
		this.numeroDePaginas = numeroDePaginas;
	}
}
