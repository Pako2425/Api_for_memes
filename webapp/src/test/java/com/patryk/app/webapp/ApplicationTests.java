package com.patryk.app.webapp;

import com.patryk.app.webapp.Model.User;
import com.patryk.app.webapp.Model.UserRole;
import com.patryk.app.webapp.Repository.UsersRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AllArgsConstructor

class ApplicationTests {
	/*
	private UsersRepository usersRepository;
	private EntityManager entityManager;
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	void createNewUserTest() {
		User user = new User("name", "email", "password", UserRole.ROLE_USER);
		User savedUser = usersRepository.save(user);
		User foundUser = entityManager.find(User.class, savedUser.getId());
		Assertions.assertEquals(savedUser.getEmail(), foundUser.getEmail());
	}

	 */
}
