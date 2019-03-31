package com.mavha.test.backend.exception;

public class TodoNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9048860323754357881L;

	public TodoNotFoundException(Long id) {
		super("Could not find todo " + id);
	}
}
