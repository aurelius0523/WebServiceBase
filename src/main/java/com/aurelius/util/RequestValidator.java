package com.aurelius.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.log4j.Logger;

import com.aurelius.module.common.exception.RequestValidationException;

public class RequestValidator {
	private static final Logger logger = Logger.getLogger(RequestValidator.class);
	
	private RequestValidator() {
		
	}
	
	/**
	 * Validates request input based on parameters marked with {@link NotNull.class}.
	 * Any null, empty string and empty collections will result in {@link RequestValidationException.class}
	 * being thrown with the message : "The following field(s) are required: field1, field2"
	 * @param request
	 */
	public static void validate(Object request) {
		Field[] fields = request.getClass().getDeclaredFields();

		if (fields.length != 0) {
			StringBuilder builder = new StringBuilder();
			
			for (Field field : fields) {
				if (field != null && field.isAnnotationPresent(NotNull.class)) {
					field.setAccessible(true);
					
					if (String.class.isAssignableFrom(field.getType())) {
						builder = buildErrorForStringFields(builder, request, field);
						
					} else if (Collection.class.isAssignableFrom(field.getType())) {
						builder = buildErrorForCollectionFields(builder, request, field);
						
					} 
				}
			}
			
			if (builder.length() != 0) {
				builder.insert(0, "The following field(s) are required : ");
				builder.setLength(builder.length() - 2);
				throw new RequestValidationException(builder.toString());
			}
		}
	}
	
	private static StringBuilder buildErrorForStringFields(StringBuilder builder, Object object, Field field) {
		String string = "";
		
		try {
			string = (String) field.get(object);
			
		} catch (IllegalArgumentException | IllegalAccessException e) {
			logger.error(e);
			throw new IllegalArgumentException(e);
		}
		
		if (string == null) {
			builder.append(field.getName() + ", ");
		} else {
			string = string.replace("\\s+", "");
			
			if (string.equals("")) {
				builder.append(field.getName() + ", ");
			}
		}
		
		return builder;
	}
	
	private static StringBuilder buildErrorForCollectionFields(StringBuilder builder, Object object, Field field) {
		List<?> list = new ArrayList<>();
		
		try {
			list = (List<?>) field.get(object);
			
			if (list == null || list.isEmpty()) {
				builder.append(field.getName() + ", ");
			}
			
		} catch (IllegalArgumentException | IllegalAccessException e) {
			logger.error(e);
			throw new IllegalArgumentException(e);
		}
		
		return builder;
	}
}
