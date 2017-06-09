package com.aurelius.module.user.repository.base;

import java.util.Collection;

public interface RepositoryBase<T> {
	
	T find(String id);
	
	Collection<T> findMany();
	
	String add(T t);
	
	void update(T t);
	
	void delete(String id);
}
