package com.aurelius.util;

import java.util.Collection;

public class Validator {
	private Validator() {
		
	}
	
	public static boolean isNullOrEmpty(Object field) {
		if (field == null) {
			return true;
			
		} else if (field instanceof String) {
			String string = (String) field;
			string = string.replace("\\s+", "");
			
			if (string.equals("")) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isCollectionEmpty(Object field) {
		return field instanceof Collection && ((Collection<?>) field).isEmpty();
	}
}
