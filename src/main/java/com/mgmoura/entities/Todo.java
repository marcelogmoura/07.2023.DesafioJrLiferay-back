package com.mgmoura.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "todos")
public class Todo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@NotBlank
	@Column(name = "nome" , length = 50 , nullable = false)
	private String nome;
	
	@NotBlank
	@Column(name = "descricao" , length = 150 , nullable = false)
	private String descricao;
	

	@Column(name = "realizado")
	private String realizado;
	

	@Column(name = "prioridade" )
	private Integer prioridade;
	
	
	public Todo(String nome, String descricao, String realizado, Integer prioridade) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.realizado = realizado;
		this.prioridade = prioridade;
	}




	
	

}
