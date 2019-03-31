package com.mavha.test.backend;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.mavha.test.backend.repository.TodoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BackendApplicationTests {

	@Autowired
	private ApplicationContext context;
	
	@Test
	public void contextLoads() {
		assertNotNull(context.getBean(TodoRepository.class));
	}





}
