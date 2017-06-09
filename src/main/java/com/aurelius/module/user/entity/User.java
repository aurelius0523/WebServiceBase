package com.aurelius.module.user.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Entity
@Document(collection = "User")
public class User {
	private String id;
	private String name;
	
	public User() {
		
	}
	
	@Id
	@NotNull
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Field("_id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Field("name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
