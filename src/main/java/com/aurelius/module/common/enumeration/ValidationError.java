package com.aurelius.module.common.enumeration;

import lombok.Getter;

@Getter
public enum ValidationError {
	/* Errors generated before completing facade, usually in servlet layer */
	UNKNOWN_ERROR (10000, "Unknown Error"),
	RESOURCE_NOT_FOUND(10001, "Resource Not Found"),
	INCOMPLETE_PARAMETER(10002, "Incomplete parameter"),
	
	/* Errors generated when validating request */
	INCOMPLETE_REQUEST(20000, "Incomplete request"),
	INVALID_USERNAME(20001, "Invalid username"),
	INVALID_FULL_NAME(20002, "Invalid fullname"),
	INVALID_ADDRESS(20003, "Invalid address"),
	
	/* Errors generated that are related to business requirements */
	USER_ALREADY_EXISTS(30000, "User already exists");
	
	private int errorCode;
	private String errorDesc;
	
	ValidationError(int errorCode, String errorDesc) {
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
	}
}
