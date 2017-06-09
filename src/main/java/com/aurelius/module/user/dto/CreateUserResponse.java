package com.aurelius.module.user.dto;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "user", description = "user model")
public class CreateUserResponse {
	private UserDto user;

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}
}
