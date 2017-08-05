package com.aurelius.module.user.dto;

import com.aurelius.module.common.dto.DtoBase;
import com.aurelius.module.common.enumeration.ValidationContext;
import com.aurelius.module.common.enumeration.ValidationError;
import com.aurelius.util.DtoValidator;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@ApiModel (value = "User")
@Getter @Setter
public class UserDto extends DtoBase {
	private String userId;
	private String fullName;
	private String address;
	private String username;
	
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
