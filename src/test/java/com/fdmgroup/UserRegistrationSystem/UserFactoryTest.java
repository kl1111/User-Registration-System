package com.fdmgroup.UserRegistrationSystem;

import static org.junit.Assert.*;

import org.junit.Test;

import com.fdmgroup.UserRegistrationSystem.User;
import com.fdmgroup.UserRegistrationSystem.UserFactory;

public class UserFactoryTest {

	private final String USERNAME = "Fred";
	private final String PASSWORD = "password";
	private final String ROLE = "admin";

	@Test
	public void test_CreateUser_ReturnsValidObject_WhenInvoked() {
		UserFactory userFactory = new UserFactory();
		User user = userFactory.createUser(USERNAME, PASSWORD, ROLE);
		assertNotNull(user);
	}

	@Test
	public void test_CreateUser_ReturnsUserFred_WhenUserNameFredRequested() {
		UserFactory userFactory = new UserFactory();
		User user = userFactory.createUser(USERNAME, PASSWORD, ROLE);
		assertEquals(USERNAME, user.username);
	}
	
	// lots more tests here
}
