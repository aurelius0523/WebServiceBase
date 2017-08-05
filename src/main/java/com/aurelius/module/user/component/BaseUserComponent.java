package com.aurelius.module.user.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.aurelius.module.user.entity.UserEntity;
import com.aurelius.module.user.repository.UserRepository;

@Component
public class BaseUserComponent implements UserComponent {
	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean isUsernameUnique(String username) {
		return userRepository.findByUsername(username) == null;
	}

	@Override
	public Page<UserEntity> getList(Pageable pageable) {
		return userRepository.findAll(pageable);
	}
}
