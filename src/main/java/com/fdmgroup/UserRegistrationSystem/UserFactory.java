package com.fdmgroup.UserRegistrationSystem;

public class UserFactory {
	
//	private String username;
//	private String password;
//	private String role;
//	
//	public UserFactory(String username, String password, String role) {
//		this.username = username;
//		this.password = password;
//		this.role = role;
//	}
	
	public User createUser(String username, String password, String role) {
		User result = new User();
		result.username = username;
		result.password = password;
		result.role = role;
		return result;
	}
}
