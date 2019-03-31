package com.mavha.test.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mavha.test.backend.model.Todo;


public interface TodoRepository extends JpaRepository<Todo, Long> {

	 public Todo findByDescripcion(String descripcion);
}