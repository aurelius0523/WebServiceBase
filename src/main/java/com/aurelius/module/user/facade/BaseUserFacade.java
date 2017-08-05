package com.aurelius.module.user.facade;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.aurelius.module.bank.entity.BankEntity;
import com.aurelius.module.common.enumeration.ValidationError;
import com.aurelius.module.common.exception.ValidationException;
import com.aurelius.module.user.component.UserComponent;
import com.aurelius.module.user.dto.UserDto;
import com.aurelius.module.user.entity.UserEntity;
import com.aurelius.module.user.mapper.UserMapper;
import com.aurelius.module.user.repository.UserRepository;

@Component
@Transactional
public class BaseUserFacade implements UserFacade {
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserComponent userComponent;
	
	public UserDto createUser (UserDto userDto) {
		if (userDto == null) {
			throw new ValidationException(ValidationError.INCOMPLETE_REQUEST);
		}
		
		if (!userComponent.isUsernameUnique(userDto.getUsername())) {
			throw new ValidationException(ValidationError.USER_ALREADY_EXISTS);
		}
		
		UserEntity user = userMapper.dtoToEntity(userDto);
		
		BankEntity bankEntity = new BankEntity();
		bankEntity.setBankName("CIMB");
		bankEntity.setCreatedAt(new Date());
		
		Set<BankEntity> banks = new HashSet<>();
		banks.add(bankEntity);
		user.setBanks(banks);
		
		UserEntity savedEntity = userRepository.save(user);
		
		return userMapper.entityToDto(savedEntity);
	}

	@Override
	public Page<UserDto>getUserList(int page, int size) {
		Pageable pageable = new PageRequest(page, size);
		
		Page<UserEntity> userEntityPages = userRepository.findAll(pageable);
		
		return userEntityPages.map(userMapper::entityToDto);
	}
}
