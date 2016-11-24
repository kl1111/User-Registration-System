package com.fdmgroup.UserRegistrationSystem;

public class LogonController {

	private ReadCommand readCommand;
	
	public LogonController(ReadCommand readCommand) {
		this.readCommand = readCommand;
	}

	public void logon(String username, String password) throws UserLogonException {
		if (username == null || username.isEmpty()) 
			throw new UserLogonException("No user provided");
		
		User user = readCommand.readUser(username);
		
		if (user == null)
			throw new UserLogonException("User not registered");
		
		if (!user.password.equals(password))
			throw new UserLogonException("Incorrect password");

		System.out.println("Logon successful");
		
	}

}
