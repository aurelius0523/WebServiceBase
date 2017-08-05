package com.aurelius.module.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurelius.module.user.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	public UserEntity findByUsername(String username);
}
