package com.aurelius.util;

import java.util.HashMap;
import java.util.Map;

import com.aurelius.module.common.enumeration.ValidationError;
import com.aurelius.module.common.exception.ValidationException;

import lombok.Getter;

public final class DtoValidator { 
	@Getter
	private Map<Object, ValidationError> fieldToErrorMessage = new HashMap<>();
	
	public DtoValidator add(Object field, ValidationError validationError) {
		fieldToErrorMessage.put(field, validationError);
		return this;
	}
	
	public void validate() {
		if (!fieldToErrorMessage.isEmpty()) {
			fieldToErrorMessage.forEach((field, validationError) -> {
				if (field == null || Validator.isCollectionEmpty(field)) {
					throw new ValidationException(validationError);
				}
			});
		}
	}
}
