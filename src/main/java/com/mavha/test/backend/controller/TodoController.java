package com.mavha.test.backend.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mavha.test.backend.exception.TodoNotFoundException;
import com.mavha.test.backend.model.Todo;
import com.mavha.test.backend.repository.TodoRepository;

@RestController
@RequestMapping("api")
class TodoController {

	@Autowired
	private TodoRepository repository;


	@GetMapping("/todos")
	List<Todo> todos() {
		return repository.findAll();
	}
	
	@GetMapping("/todos/{id}/{descripcion}/{estado}")
	List<Todo> todosPorFiltros(@PathVariable Long id, @PathVariable String descripcion,@PathVariable String estado) {
		List<Todo>  list = repository.findAll();
		List<Todo> students = list.stream()
		        .filter(p -> p.getId() == id || p.getDescripcion().contains(descripcion) || p.getEstado().compareTo(estado) == 0 )
		        .collect(Collectors.toCollection(ArrayList::new));
		return students;
	}

	@PostMapping("/todos")
	Todo nuevoTodo(@RequestBody Todo newEmployee) {
		return repository.save(newEmployee);
	}
	@PutMapping("/todos/{id}/{estado}")
	Todo cambioEstado(@PathVariable Long id, @PathVariable String estado) {
		return repository.findById(id)
			.map(todo -> {
				todo.setEstado(estado);
				return repository.save(todo);
			})
			.orElseThrow(() -> new TodoNotFoundException(id));
	}
}