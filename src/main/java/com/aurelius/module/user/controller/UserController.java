package com.aurelius.module.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurelius.module.user.dto.UserDto;
import com.aurelius.module.user.entity.User;
import com.aurelius.module.user.service.UserService;
import com.aurelius.util.enumeration.ValidationContext;

//@Api("users")
@RestController
@RequestMapping("/users")
public class UserController {
	private UserService service;

	@Autowired
	public void setService(UserService service) {
		this.service = service;
	}

	@GetMapping("/")
    public ResponseEntity<User> getUserList() {
    	User ut = new User();
    	ut.setId("2");
    	ut.setName("kl");
    	return ResponseEntity.status(HttpStatus.OK).body(ut);
    }
    
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user,
    		Errors errors) {
    	service.getClass();
    	user.validate(ValidationContext.CREATE);
    	return ResponseEntity.status(HttpStatus.CREATED).body(new UserDto());
    }
    
    @DeleteMapping("/{userId}")
    public ResponseEntity<User> deleteUser(@Validated @RequestBody UserDto user, 
    	@PathVariable ("userId") String userId) {
    	
    	return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable ("userId") String userId,
    		@ModelAttribute @RequestBody UserDto user) {
    	
    	return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}