package com.aurelius.module.common.dto;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class ErrorInfo {
	private String code;
	private String message;
	private String url;
}
