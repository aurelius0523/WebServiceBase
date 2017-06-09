package com.aurelius.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.aurelius.module.common.dto.validation.ValidationError;
import com.aurelius.module.common.exception.ModelValidationException;

import lombok.Getter;

public class DtoValidator {
	
	@Getter
	private Map<Object, ValidationError> fieldToErrorMessage = new HashMap<>();
	
	public DtoValidator add(Object field, ValidationError validationError) {
		fieldToErrorMessage.put(field, validationError);
		return this;
	}
	
	public void validate() throws ModelValidationException {
		if (!fieldToErrorMessage.isEmpty()) {
			fieldToErrorMessage.forEach((field, validationError) -> {
				if (field == null || isCollectionEmpty(field)) {
					throw new ModelValidationException(validationError.getErrorCode() + ":" + validationError.getErrorDesc());
				}
			});
		}
	}
	
	private boolean isCollectionEmpty(Object field) {
		return field instanceof Collection && ((Collection<?>) field).isEmpty();
	}
}
