package com.aurelius.module.user.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.aurelius.module.user.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId>{
	public List<User> findAll();
	
}
