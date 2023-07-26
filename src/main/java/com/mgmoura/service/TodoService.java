package com.mgmoura.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mgmoura.entities.Todo;
import com.mgmoura.repositories.TodoRepository;

import jakarta.validation.Valid;

@Controller
public class TodoService {
	
	@Autowired
	private TodoRepository todoRepository;
	
	@PostMapping
	public List<Todo> create(@RequestBody @Valid Todo todo) {
		todoRepository.save(todo);
		return list();
	}
	
	public List<Todo> list() {
		return todoRepository.findAll();
	}

	
	public List<Todo> update(Todo todo) {
		todoRepository.save(todo);
		return list();
	}
	
	public List<Todo> delete(Long id) {
		todoRepository.deleteById(id);
		return list();
	}

}




