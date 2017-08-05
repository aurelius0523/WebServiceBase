package com.aurelius.module.common.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.aurelius.module.common.enumeration.ValidationContext;

public abstract class DtoBase {
	public abstract void validate(ValidationContext context);
	
	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
