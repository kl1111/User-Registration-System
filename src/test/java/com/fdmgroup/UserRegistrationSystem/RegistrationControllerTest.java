package com.fdmgroup.UserRegistrationSystem;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.UserRegistrationSystem.ReadCommand;
import com.fdmgroup.UserRegistrationSystem.RegistrationController;
import com.fdmgroup.UserRegistrationSystem.User;
import com.fdmgroup.UserRegistrationSystem.UserFactory;
import com.fdmgroup.UserRegistrationSystem.UserRegistrationException;
import com.fdmgroup.UserRegistrationSystem.WriteCommand;

public class RegistrationControllerTest {

	private UserFactory userFactory;
	private WriteCommand writeCommand;
	private ReadCommand readCommand;
	private RegistrationController registrationController;
	
	private final String STANDARD_USERNAME = "Fred";
	private final String STANDARD_PASSWORD = "password";
	private final String STANDARD_ROLE = "admin";
	
	@Before
	public void before() {
		userFactory = new UserFactory();
		writeCommand = mock(WriteCommand.class);
		readCommand = mock(ReadCommand.class);
		registrationController = new RegistrationController(userFactory, writeCommand, readCommand);
	}
	
	@Test(expected=NullPointerException.class)
	public void test_RegisterNewUser_ThrowsException_WhenArgumentsAreNull() throws UserRegistrationException {
		String username = null;
		String password = null;
		String role = null;		
		registrationController.registerNewUser(username, password, role);
	}

	@Test
	public void test_RegisterNewUser_CallsUserFactoryCreateUser_WhenArgumentsValid() throws UserRegistrationException {
		userFactory = mock(UserFactory.class);
		registrationController = new RegistrationController(userFactory, writeCommand, readCommand);
		registrationController.registerNewUser(STANDARD_USERNAME, STANDARD_PASSWORD, STANDARD_ROLE);
		verify(userFactory, times(1)).createUser(STANDARD_USERNAME, STANDARD_PASSWORD, STANDARD_ROLE);
	}
	
	@Test
	public void test_RegisterNewUser_CallsWriteCommandWriteUser_WhenArgumentValid() throws UserRegistrationException {
		registrationController.registerNewUser(STANDARD_USERNAME, STANDARD_PASSWORD, STANDARD_ROLE);
		verify(writeCommand, times(1)).writeUser((User)anyObject());
	}
	
	@Test(expected=UserRegistrationException.class)
	public void test_RegisterNewUser_ThrowsException_WhenSameUserAddedTwice() throws UserRegistrationException {
		when(readCommand.readUser(anyString())).thenReturn(new User());
		registrationController.registerNewUser(STANDARD_USERNAME, STANDARD_PASSWORD, STANDARD_ROLE);
		registrationController.registerNewUser(STANDARD_USERNAME, STANDARD_PASSWORD, STANDARD_ROLE);
	}
	
	@Test
	public void test_RegisterNewUser_CallsReadCommandReadUser_WhenArgumentValid() throws UserRegistrationException {
		registrationController.registerNewUser(STANDARD_USERNAME, STANDARD_PASSWORD, STANDARD_ROLE);
		verify(readCommand, times(1)).readUser(anyString());
	}
	
	@Test(expected=UserRegistrationException.class)
	public void test_RegisterNewUser_ThrowsException_WhenSameUserAddedTwiceToDifferentControllers() throws UserRegistrationException {
		when(readCommand.readUser(anyString())).thenReturn(new User());
		registrationController.registerNewUser(STANDARD_USERNAME, STANDARD_PASSWORD, STANDARD_ROLE);
		RegistrationController testCtrlr = new RegistrationController(userFactory, writeCommand, readCommand);
		testCtrlr.registerNewUser(STANDARD_USERNAME, STANDARD_PASSWORD, STANDARD_ROLE);
	}	
	
//	@Test(expected=UserRegistrationException.class)
//	public void test_RegisterNewUser_ThrowsException_WhenDupeUserAddOnFourthAttempt() throws UserRegistrationException {
////		when(readCommand.readUser(anyString())).thenReturn(new User());
////		registrationController.registerNewUser(STANDARD_USERNAME, STANDARD_PASSWORD, STANDARD_ROLE);
////		RegistrationController testCtrlr = new RegistrationController(userFactory, writeCommand, readCommand);
////		testCtrlr.registerNewUser(STANDARD_USERNAME, STANDARD_PASSWORD, STANDARD_ROLE);
//		
//		RegistrationController ctrlrOne = new RegistrationController(userFactory, writeCommand, readCommand);
//		RegistrationController ctrlrTwo = new RegistrationController(userFactory, writeCommand, readCommand);
//		RegistrationController ctrlrThree = new RegistrationController(userFactory, writeCommand, readCommand);
//		RegistrationController ctrlrFour = new RegistrationController(userFactory, writeCommand, readCommand);
//		
//		final String DUPENAME = "Joe";
//		ctrlrOne.registerNewUser(DUPENAME, STANDARD_PASSWORD, STANDARD_ROLE);
//		ctrlrTwo.registerNewUser("John", STANDARD_PASSWORD, STANDARD_ROLE);
//		ctrlrThree.registerNewUser("Jeff", STANDARD_PASSWORD, STANDARD_ROLE);
//		ctrlrFour.registerNewUser(DUPENAME, STANDARD_PASSWORD, STANDARD_ROLE);
//	}	
}
