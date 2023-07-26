package com.mgmoura.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoPostDto {
	
	private String nome;
	private String descricao;
	private String realizado;
	private Integer prioridade;

	
}
