package com.fdmgroup.UserRegistrationSystem;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.fdmgroup.UserRegistrationSystem.LogonController;
import com.fdmgroup.UserRegistrationSystem.ReadCommand;
import com.fdmgroup.UserRegistrationSystem.User;
import com.fdmgroup.UserRegistrationSystem.UserFactory;
import com.fdmgroup.UserRegistrationSystem.UserLogonException;

public class LogonControllerTest {

	private ReadCommand readCommand;
	private LogonController logonController;
	private User user;
	
	private static final String USERNAME = "Alan";
	private static final String PASSWORD = "password";
	private static final String ROLE = "trainer";
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Before
	public void before() {
		readCommand = mock(ReadCommand.class);
		logonController = new LogonController(readCommand);
		UserFactory userFactory = new UserFactory();
		user = userFactory.createUser(USERNAME, PASSWORD, ROLE);
	}
	
	@Test
	public void test_Logon_ThrowsUserLogonException_WhenUserIsNull() throws UserLogonException {
		expectedEx.expect(UserLogonException.class);
		expectedEx.expectMessage("No user provided");
		String nullUsername = null;
		String nullPassword = null;
		logonController.logon(nullUsername, nullPassword);
	}
	
	@Test
	public void test_Logon_ThrowsUserLogonException_WhenUserNotRegistered() throws UserLogonException {
		expectedEx.expect(UserLogonException.class);
		expectedEx.expectMessage("User not registered");
		logonController.logon(USERNAME, PASSWORD);
	}	

	@Test
	public void test_Logon_ReadCommandReadUserCalled_WhenUserNameNotNull() throws UserLogonException {
		when(readCommand.readUser(USERNAME)).thenReturn(user);
		logonController.logon(USERNAME, PASSWORD);
		verify(readCommand, times(1)).readUser(USERNAME);
		// success
	}

	@Test
	public void test_Logon_ThrowUserLogonException_WhenPasswordIncorrect() throws UserLogonException {
		expectedEx.expect(UserLogonException.class);
		expectedEx.expectMessage("Incorrect password");
		when(readCommand.readUser(USERNAME)).thenReturn(user);
		logonController.logon(USERNAME, "incorrect password");
	}
		
	@Test
	public void test_Logon_Succeeds_WhenUsernamePasswordCorrect() throws UserLogonException {
		when(readCommand.readUser(USERNAME)).thenReturn(user);
		logonController.logon(USERNAME, PASSWORD);
		// success
	}
}











