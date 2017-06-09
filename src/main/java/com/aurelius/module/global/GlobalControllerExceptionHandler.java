package com.aurelius.module.global;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.aurelius.module.common.dto.ErrorInfo;
import com.aurelius.module.common.exception.ModelValidationException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    @ExceptionHandler({ModelValidationException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST) 
	@ResponseBody 
	public ErrorInfo handleModelValidationException(ModelValidationException ex) {
    	String errorMessage = ex.getMessage();
    	String[] codeAndMessage = errorMessage.split(":");
		return ErrorInfo.builder()
				.code(codeAndMessage[0])
				.message(codeAndMessage[1])
				.url("")
				.build();
	}
}
