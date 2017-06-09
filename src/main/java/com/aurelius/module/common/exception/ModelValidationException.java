package com.aurelius.module.common.exception;

public class ModelValidationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ModelValidationException() {
		super("Model validation failed");
	}

	public ModelValidationException(String message) {
		super(message);
	}
}
