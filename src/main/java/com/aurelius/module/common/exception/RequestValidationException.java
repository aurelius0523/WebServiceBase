package com.aurelius.module.common.exception;

public class RequestValidationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public RequestValidationException() {
		super("Request Validation Exception");
	}

	public RequestValidationException(String message) {
		super(message);
	}
}
