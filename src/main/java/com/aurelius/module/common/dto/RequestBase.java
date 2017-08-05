package com.aurelius.module.common.dto;

public interface RequestBase {
	/**
	 * Compulsory validation for required fields per request. {@link NotNull.class} is not
	 * recommended due to the need to process {@link BindingResult.class}. Exception will
	 * instead by caught and processed by {@link GlobalControllerExceptionHandler.class}
	 */
	public void validate();
}
