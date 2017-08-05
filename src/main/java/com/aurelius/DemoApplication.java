package com.aurelius;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.aurelius.module.user.entity.UserEntity;
import com.aurelius.module.user.repository.UserRepository;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"com.aurelius"})
@EnableSwagger2
public class DemoApplication implements CommandLineRunner{
	private static final Logger logger = Logger.getLogger(DemoApplication.class);
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("DATASOURCE = " + dataSource);
		System.out.println("\n1.findAll()...");
        for (UserEntity customer : userRepository.findAll()) {
            System.out.println(customer.getFullName());
        }
	}
}
