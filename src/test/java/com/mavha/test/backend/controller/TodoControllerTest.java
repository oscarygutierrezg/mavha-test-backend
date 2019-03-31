package com.mavha.test.backend.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;

import com.mavha.test.backend.AbstractTest;
import com.mavha.test.backend.model.Todo;

public class TodoControllerTest extends AbstractTest{

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void testTodos() throws Exception {
		String uri = "/api/todos";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Todo[] productlist = super.mapFromJson(content, Todo[].class);
		assertTrue(productlist.length > 0);

	}

	@Test
	public void testTodosPorFiltros() throws Exception {
		String uri = "/api/todos/2/1/resuelta";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Todo[] productlist = super.mapFromJson(content, Todo[].class);
		assertTrue(productlist.length == 1);
	}

	@Test
	public void testNuevoTodo() throws Exception {
		String uri = "/api/todos";
		Todo todo = new Todo();
		todo.setDescripcion("Prepare presentation2");
		todo.setEstado("pendiente");

		String inputJson = super.mapToJson(todo);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Todo todoNew = super.mapFromJson(content, Todo.class);
		assertTrue(todoNew.getId() == 4);
	}
	
	@Test
	public void testCambioEstado() throws Exception {
		String uri = "/api/todos/1/resuelta";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Todo todoUpdated = super.mapFromJson(content, Todo.class);
		assertTrue(todoUpdated.getEstado().compareTo("resuelta") == 0);
	}
	
	@Test(expected = NestedServletException.class)
	public void testCambioEstadoException() throws Exception {
		String uri = "/api/todos/4/resuelta";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(500, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertTrue(content.contains("Could not find todo"));
	}

}
