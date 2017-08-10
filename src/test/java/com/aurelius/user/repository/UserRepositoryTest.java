package com.aurelius.user.repository;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.aurelius.module.user.entity.UserEntity;
import com.aurelius.module.user.repository.UserRepository;
import com.aurelius.util.populator.Populator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase(replace=Replace.NONE)
@WebAppConfiguration
public class UserRepositoryTest {
	@Autowired
	TestEntityManager entityManager;
	
	@Autowired
	UserRepository userRepository;

	@Test
	public void whenFindByUsername_thenReturnUser() {
		UserEntity userEntity = new UserEntity();
		userEntity.setUsername("aurelius");
		entityManager.persist(userEntity);
		entityManager.flush();
		
		UserEntity returnedEntity = userRepository.findByUsername(userEntity.getUsername());
		
		assertThat(returnedEntity.getUsername())
			.isEqualTo(userEntity.getUsername());
	}
	
	@Test
	public void testPopulator() throws JsonProcessingException {
		Populator populator = new Populator.Builder()
				.withStringCharacterCount(2)
				.build();
		
		UserEntity entity = populator.populate(UserEntity.class);
		System.out.println(new ObjectMapper().writeValueAsString(entity));
	}
}
