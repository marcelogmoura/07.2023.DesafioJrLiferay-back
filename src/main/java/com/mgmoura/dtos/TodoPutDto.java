package com.mgmoura.dtos;

import lombok.Data;

@Data
public class TodoPutDto {
	
	private Long id;
	private String nome;
	private String descricao;
	private String realizado;
	private Integer prioridade;

}
