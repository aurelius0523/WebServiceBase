package com.aurelius.module.user.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.aurelius.module.common.dto.RequestBase;
import com.aurelius.util.RequestValidator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @ApiModel
public class UserCreateRequest implements RequestBase {
	@NotNull
	@ApiModelProperty(required = true)
	private String fullName;
	
	@NotNull
	@ApiModelProperty(required = true)
	private String username;
	
	@NotNull
	private List<String> phoneNumbers;
	private String address;
	
	@Override
	public void validate() {
		RequestValidator.validate(this);
	}
}
