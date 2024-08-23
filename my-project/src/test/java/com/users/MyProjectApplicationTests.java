package com.users;

import com.users.entity.UserEntity;
import com.users.repository.UserRepository;
import com.users.service.UserService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.core.internal.Function;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MyProjectApplicationTests {


	@Inject
	private UserService userService;

	@Inject
	private UserRepository userRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void getUserTest() {

	}

	@Test
	public void createUserSuccessTest() {
		UserEntity newUser = prepareUserEntityData();
		assertNotNull(userRepository.saveUser(newUser));
	}

	@Test
	public void createUserNotSuccessTest() {
		UserEntity newUser = null;

		Long isCreated = userRepository.saveUser(newUser);
		assertNull(isCreated);
	}

	@Test
	public void createdAndConsultUserTest() {
		UserEntity newUser = prepareUserEntityData();

		Long idCreated = userRepository.saveUser(newUser);
		UserEntity user = userRepository.findByUserId(idCreated);

		assertEquals(idCreated, user.getUserId());
		assertEquals(newUser.getFirstName(), user.getFirstName());
		assertEquals(newUser.getLastName(), user.getLastName());
		assertEquals(newUser.getEmailAddress(), user.getEmailAddress());
	}

	@Test
	public void updateUserTest() {
		UserEntity newUser = prepareUserEntityData();

		Long idCreated = userRepository.saveUser(newUser);
		UserEntity userToUpdate = userRepository.findByUserId(idCreated);

		newUser.setFirstName("newFirstName");
		newUser.setLastName("newLasttName");
		newUser.setEmailAddress("newEmail");

		Boolean isUpdate = userRepository.updateUser(newUser,idCreated);

		assertTrue(isUpdate);

		assertEquals(idCreated, userToUpdate.getUserId());
		assertNotEquals(newUser.getFirstName(), userToUpdate.getFirstName());
		assertNotEquals(newUser.getLastName(), userToUpdate.getLastName());
		assertNotEquals(newUser.getEmailAddress(), userToUpdate.getEmailAddress());
	}

	public UserEntity prepareUserEntityData() {
		UserEntity userEntity = new UserEntity();
		userEntity.setFirstName("nombreXXX");
		userEntity.setLastName("apellidoXXX");
		userEntity.setEmailAddress("valorXXX@correo.com");

		return userEntity;
	}
}
