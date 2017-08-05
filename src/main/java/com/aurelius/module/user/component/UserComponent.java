package com.aurelius.module.user.component;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.aurelius.module.user.entity.UserEntity;

public interface UserComponent {
	public boolean isUsernameUnique(String username);
	public Page<UserEntity> getList(Pageable pageable);
}
