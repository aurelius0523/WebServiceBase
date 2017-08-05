package com.aurelius.core;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.aurelius.module.common.dto.ErrorInfo;
import com.aurelius.module.common.enumeration.ValidationError;
import com.aurelius.module.common.exception.RequestValidationException;
import com.aurelius.module.common.exception.ValidationException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    @ExceptionHandler({ValidationException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST) 
	@ResponseBody 
	public ErrorInfo handleModelValidationException(ValidationException ex, HttpServletRequest servletRequest) {
    	String errorMessage = ex.getMessage();
    	ValidationError err = ValidationError.valueOf(errorMessage);
    	return ErrorInfo.builder()
				.code(String.valueOf(err.getErrorCode()))
				.message(err.getErrorDesc())
				.path(servletRequest.getRequestURI())
				.build();
	}
    
    @ExceptionHandler({RequestValidationException.class})
   	@ResponseStatus(HttpStatus.BAD_REQUEST) 
   	@ResponseBody 
   	public ErrorInfo handleRequestValidationException(RequestValidationException ex, HttpServletRequest servletRequest) {
       	return ErrorInfo.builder()
   				.code(String.valueOf(ValidationError.INCOMPLETE_PARAMETER.getErrorCode()))
   				.message(ex.getMessage())
   				.path(servletRequest.getRequestURI())
   				.build();
   	}
    
    @ExceptionHandler({MissingServletRequestParameterException.class})
   	@ResponseStatus(HttpStatus.BAD_REQUEST) 
   	@ResponseBody 
   	public ErrorInfo handleMissingServletRequestParameterException(MissingServletRequestParameterException ex, HttpServletRequest servletRequest) {
       	return ErrorInfo.builder()
   				.code(String.valueOf(ValidationError.INCOMPLETE_PARAMETER.getErrorCode()))
   				.message(ex.getMessage())
   				.path(servletRequest.getRequestURI())
   				.build();
   	}
    
    @ExceptionHandler({RuntimeException.class})
   	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) 
   	@ResponseBody 
   	public ErrorInfo handleMissingRuntimeException(RuntimeException ex, HttpServletRequest servletRequest) {
       	return ErrorInfo.builder()
   				.code(String.valueOf(ValidationError.UNKNOWN_ERROR.getErrorCode()))
   				.message(ex.getMessage())
   				.path(servletRequest.getRequestURI())
   				.build();
   	}
}
