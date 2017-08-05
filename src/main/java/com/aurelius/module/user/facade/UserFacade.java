package com.aurelius.module.user.facade;

import org.springframework.data.domain.Page;

import com.aurelius.module.user.dto.UserDto;

public interface UserFacade {
	public UserDto createUser (UserDto userDto);
	public Page<UserDto> getUserList (int page, int size);
}
