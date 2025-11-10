package com.example.registro.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = 8688966555366639104L;

	public EmailAlreadyExistsException(String message) { super(message); }
}
