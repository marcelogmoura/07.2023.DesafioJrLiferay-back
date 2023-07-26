package com.mgmoura.dtos;

import org.springframework.http.HttpStatus;

import com.mgmoura.entities.Todo;

import lombok.Data;

@Data
public class TodoResponseDto {
	
	private HttpStatus status;
	private String mensagem;
	private Todo todo;

}
