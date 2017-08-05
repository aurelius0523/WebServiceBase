package com.aurelius.module.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.aurelius.module.user.dto.UserCreateRequest;
import com.aurelius.module.user.dto.UserDto;
import com.aurelius.module.user.dto.UserUpdateRequest;
import com.aurelius.module.user.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
	@Mapping(source = "id", target = "userId")
	UserDto entityToDto(UserEntity userEntity);
	
	UserEntity dtoToEntity(UserDto userDto);
	UserDto requestToDto(UserCreateRequest request);
	UserDto requestToDto(UserUpdateRequest request);
}
