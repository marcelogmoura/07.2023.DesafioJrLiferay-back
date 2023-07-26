package com.mgmoura.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mgmoura.dtos.TodoPostDto;
import com.mgmoura.dtos.TodoPostRequestDto;
import com.mgmoura.dtos.TodoResponseDto;
import com.mgmoura.entities.Todo;
import com.mgmoura.repositories.TodoRepository;
import com.mgmoura.service.TodoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/todos")
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	@Autowired
	private TodoRepository todoRepository;
	
	@PostMapping  // ok
	public ResponseEntity<TodoResponseDto> create(@RequestBody @Valid TodoPostDto dto){
		
		TodoResponseDto response = new TodoResponseDto();
		
		try {
			
			Todo todo = new Todo();
			todo.setNome(dto.getNome());
			todo.setDescricao(dto.getDescricao());
			todo.setPrioridade(dto.getPrioridade());
			todo.setRealizado(dto.getRealizado());
			
			todoRepository.save(todo);
			
			response.setStatus(HttpStatus.CREATED); // 201
			response.setMensagem("ToDo cadastrado com sucesso.");
			response.setTodo(todo);
						
			
		}catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setMensagem(e.getMessage());
						
		}
		return ResponseEntity.status(response.getStatus().value()).body(response);
	}
	
	
	@PutMapping // ok
	public ResponseEntity<TodoResponseDto> update(@RequestBody TodoPostRequestDto dto){
		
		TodoResponseDto response = new TodoResponseDto();
		
		try {
			Optional<Todo> todo = todoRepository.findById(dto.getId());
			
			if(todo.isEmpty()) {
				response.setStatus(HttpStatus.BAD_REQUEST);
				response.setMensagem("ToDo não encontrado");
				
			}else {
				Todo item = todo.get();
				
				item.setNome(dto.getNome());
				item.setDescricao(dto.getDescricao());
				item.setPrioridade(dto.getPrioridade());
				item.setRealizado(dto.getRealizado());
				
				todoRepository.save(item);
				
				response.setStatus(HttpStatus.OK); // 200
				response.setMensagem("ToDO atualizado com sucesso.");
				response.setTodo(item);
			}
			
		}catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setMensagem(e.getMessage());
		}
		
		return ResponseEntity.status(response.getStatus().value()).body(response);
	}



	
	@GetMapping // ok
	public ResponseEntity<List<Todo>> list(){
		
		try {
			List<Todo> todos = todoRepository.findAll();
			
			if(todos.size()> 0) {
				return ResponseEntity.status(200).body(todos);
				
			}else {
				return ResponseEntity.status(204).body(null);
			}
			
		}catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}

	}

	@GetMapping("{id}")
	public ResponseEntity<Todo> getById(@PathVariable("id") Long id) {
		
		try {
			Optional<Todo> todo = todoRepository.findById(id);
			
			if(todo.isPresent()) {
				return ResponseEntity.status(200).body(todo.get());				
		}else {
			return ResponseEntity.status(204).body(null);
		}
			
		}catch (Exception e) {
			return ResponseEntity.status(500).body(null);
			
		}
	}



	
	@DeleteMapping("{id}") // ok
	public ResponseEntity<TodoResponseDto> delete(@PathVariable("id") Long id){
		
		TodoResponseDto response = new TodoResponseDto();
		
		try {
			Optional<Todo> todo = todoRepository.findById(id);
			
			if(todo.isEmpty()) {
				response.setStatus(HttpStatus.BAD_REQUEST);
				response.setMensagem("ToDo não localizado, ID incorreto.");
				
			}else {
				todoRepository.delete(todo.get());
				
				response.setStatus(HttpStatus.OK);
				response.setMensagem("ToDo excluído com sucesso.");
				response.setTodo(todo.get());
			}
			
		}catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setMensagem(e.getMessage());
		}
		
		return ResponseEntity.status(response.getStatus().value()).body(response);
	}

}


