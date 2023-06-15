package com.floriculture.mysql;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Flores")
public class Flores {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
     
	@NotNull
	@NotBlank
	@NotEmpty
	@Column(name = "Nome")
	private String nome;
	
	@NotNull
	@Column(name = "Quantidade")
	private int quantidade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Flores(@NotNull @NotBlank @NotEmpty String nome, @NotNull @NotBlank @NotEmpty int quantidade) {
		super();
		this.nome = nome;
		this.quantidade = quantidade;
	}


	public Flores() {
		
	}
	
	

	
	
	
	
	

}
