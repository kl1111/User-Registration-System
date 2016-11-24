package com.fdmgroup.UserRegistrationSystem;

public class RegistrationController {

	private UserFactory userFactory;
	private WriteCommand writeCommand;
	private ReadCommand readCommand;
//	private User lastUserAdded; // TEST TEST TEST !!!!
	
	public RegistrationController(UserFactory userFactory, WriteCommand writeCommand, ReadCommand readCommand) {
		this.userFactory = userFactory;
		this.writeCommand = writeCommand;
		this.readCommand = readCommand;
	}
	
	public void registerNewUser(String username, String password, String role) throws UserRegistrationException {
		if (username == null ||
			password == null ||
			role == null) {
			throw new NullPointerException();
		}
		
		User existingUser = readCommand.readUser(username);
		if (existingUser != null) {
			throw new UserRegistrationException();
		}
		
		User newUser = userFactory.createUser(username, password, role);
		writeCommand.writeUser(newUser);
	}
	
	

}
