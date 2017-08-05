package com.aurelius.module.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurelius.module.user.dto.UserCreateRequest;
import com.aurelius.module.user.dto.UserDto;
import com.aurelius.module.user.entity.UserEntity;
import com.aurelius.module.user.facade.UserFacade;
import com.aurelius.module.user.mapper.UserMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("users")
@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserFacade userFacade;

	@Autowired
	private UserMapper userMapper;
	
	@ApiOperation(value = "createUser", tags = "users")
	@PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserCreateRequest userRequest) {
		userRequest.validate();
		
		UserDto userDto = userMapper.requestToDto(userRequest);
    	UserDto returnedDto = userFacade.createUser(userDto);
    	
    	return ResponseEntity.status(HttpStatus.CREATED).body(returnedDto);
    }

	@ApiOperation(value = "getUser", tags = "users")
	@GetMapping("/")
    public ResponseEntity<Page<UserDto>> getUserList(
    		@RequestParam(value = "page", required = true) int page,
    		@RequestParam(value = "size", required = true) int size) {
		
		Page<UserDto> pages = userFacade.getUserList(page, size);
    	return ResponseEntity.status(HttpStatus.OK).body(pages);
    }
    
   
    
    @DeleteMapping("/{userId}")
    public ResponseEntity<UserEntity> deleteUser(@Validated @RequestBody UserDto user, 
    	@PathVariable ("userId") String userId) {
    	
    	return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable ("userId") String userId,
    		@RequestBody UserDto user) {
    	
    	return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}