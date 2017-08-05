package com.aurelius.module.common.exception;

import com.aurelius.module.common.enumeration.ValidationError;

public class ValidationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ValidationException() {
		super("Validation Exception");
	}

	public ValidationException(String message) {
		super(message);
	}
	
	public ValidationException(ValidationError validationError) {
		super(validationError.toString());
	}
}
