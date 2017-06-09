package com.aurelius.module.user.dto;

import java.util.List;

import com.aurelius.module.common.dto.BaseDto;
import com.aurelius.module.common.dto.validation.ValidationError;
import com.aurelius.util.DtoValidator;
import com.aurelius.util.enumeration.ValidationContext;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@ApiModel
@Getter @Setter
public class UserDto extends BaseDto {
	private String userId;
	private String fullName;
	private String address;
	private List<String> username;
	
	public void validate(ValidationContext context) {
		if (context == ValidationContext.CREATE) {
			new DtoValidator()
				.add(this.address, ValidationError.INVALID_ADDRESS)
				.add(this.fullName, ValidationError.INVALID_FULL_NAME)
				.add(this.username, ValidationError.INVALID_USERNAME)
				.validate();
		} 
	}
}
