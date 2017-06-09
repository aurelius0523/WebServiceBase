package com.aurelius.module.common.dto.validation;

import lombok.Getter;

@Getter
public enum ValidationError {
	UNKNOWN_ERROR (10000, "Unknown Error"),
	INVALID_USERNAME(20001, "Invalid username"),
	INVALID_FULL_NAME(20002, "Invalid fullname"),
	INVALID_ADDRESS(20003, "Invalid address");
	
	private int errorCode;
	private String errorDesc;
	
	ValidationError(int errorCode, String errorDesc) {
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
	}
}
