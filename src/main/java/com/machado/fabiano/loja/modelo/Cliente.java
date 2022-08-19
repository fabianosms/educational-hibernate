package com.machado.fabiano.loja.modelo;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Embedded
	private DadosPessoais dadosPessoais;

	public Cliente() {
	}
	
	public Cliente(String nome, String cpf) {
		this.dadosPessoais = new DadosPessoais(nome, cpf);
	}
	
	public String getNome() {
		return this.dadosPessoais.getNome();
	}
	
	@Override
	public String toString() {
		return String.format("%s (%s)", this.dadosPessoais.getNome(), this.dadosPessoais.getCpf());
	}
}
