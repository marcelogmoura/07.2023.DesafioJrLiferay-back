package com.mgmoura;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.mgmoura.entities.Todo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ToDoApplicationTests {

	@Autowired
	private WebTestClient webTestClient;
	
	@Test
	void testCreateTodoSuccess() {
		var todo = new Todo("Teste #01", "desc #01", "Sim", 1);
		
		webTestClient
			.post()
			.uri("/todos")
			.bodyValue(todo)
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$").isArray()
			.jsonPath("$.length()").isEqualTo(1)
			.jsonPath("$[0].nome").isEqualTo(todo.getNome())
			.jsonPath("$[0].descricao").isEqualTo(todo.getDescricao())
			.jsonPath("$[0].realizado").isEqualTo(todo.getRealizado())  // isRealizado()
			.jsonPath("$[0].prioridade").isEqualTo(todo.getPrioridade());
		
	}
	
	@Test
	void testCreateTodoFailure() {
		
		webTestClient
			.post()
			.uri("/todos")
			.bodyValue(new Todo("", "", "", 0))
			.exchange()
			.expectStatus().isBadRequest();
		
	}

}
