package com.machado.fabiano.loja.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "produtos")
@NamedQuery(name = "Produto.buscarProdutoPorCategoria", query = "SELECT p FROM Produto p WHERE p.categoria.id.nome = :nome")
@Inheritance(strategy = InheritanceType.JOINED)
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String descricao;
	private BigDecimal preco;
	@Column(name = "data_de_cadastro")
	private LocalDate dataDeCadastro = LocalDate.now();
	
	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "id_categoria")
	private Categoria categoria;
//	@Enumerated(EnumType.STRING)
//	private CategoriaEnum categoriaEnum;

	public Produto() {
	}

	public Produto(String nome, String descricao, BigDecimal preco, Categoria categoria) {
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.categoria = categoria;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public Categoria getCategoria() {
		return categoria;
	}

//	public void setCategoriaEnum(CategoriaEnum categoriaEnum) {
//		this.categoriaEnum = categoriaEnum;
//	}

}
