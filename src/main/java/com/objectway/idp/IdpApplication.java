package com.objectway.idp;

import com.objectway.idp.DTO.UserMTDTO;
import com.objectway.idp.model.User;
import com.objectway.idp.model.UserMT;
import com.objectway.idp.repository.UserMTRepository;
import com.objectway.idp.repository.UserRepository;
import com.objectway.idp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class IdpApplication implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;
	@Autowired
	UserMTRepository userMTRepository;

	public static void main(String[] args) {
		SpringApplication.run(IdpApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		System.out.println("Application started...");
		// CREATION OF KEEP USERS
		// the following makes sure to create at least one user in DB
		User user1 = userRepository.findUserByUsername("test");
		User user2 = userRepository.findUserByUsername("bob.red");
		User user3 = userRepository.findUserByUsername("jane.green");
		if (user1 == null) {
			userRepository.save(new User("test", "pwd123"));
		}
		if (user2 == null) {
			userRepository.save(new User("bob.red", "pwd123"));
		}
		if (user3 == null) {
			userRepository.save(new User("jane.green", "pwd123"));
		}

		// CREATION OF MOVIE TRACKER USERS
//		userMTRepository.insert(new UserMT(new UserMTDTO("test", "pwd")));
	}
}
