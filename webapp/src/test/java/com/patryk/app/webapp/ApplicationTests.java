package com.patryk.app.webapp;

import com.patryk.app.webapp.Model.User;
import com.patryk.app.webapp.Model.UserRole;
import com.patryk.app.webapp.Repository.CommentsRepository;
import com.patryk.app.webapp.Repository.ImagesRepository;
import com.patryk.app.webapp.Repository.MemesRepository;
import com.patryk.app.webapp.Repository.UsersRepository;
import com.patryk.app.webapp.Service.PostDAO;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AllArgsConstructor

class ApplicationTests {

	@Mock
	private UsersRepository usersRepository;

	@Mock
	private MemesRepository memesRepository;

	@Mock
	private ImagesRepository imagesRepository;

	@Mock
	private CommentsRepository commentsRepository;

	@InjectMocks
	private PostDAO postDAO;

	//private EntityManager entityManager;
	//private MockMvc mockMvc;



	@Test
	void contextLoads() {
	}

	//@Test
	//void createNewUserTest() {
	//	User user = new User("name", "email", "password", UserRole.ROLE_USER);
	//	User savedUser = usersRepository.save(user);
	//	User foundUser = entityManager.find(User.class, savedUser.getId());
	//	Assertions.assertEquals(savedUser.getEmail(), foundUser.getEmail());
	//}

	@Test
	void createNewPostDAOTest() {
		MockitoAnnotations.openMocks(postDAO);
		Assertions.assertNotNull(postDAO);

	}
}
