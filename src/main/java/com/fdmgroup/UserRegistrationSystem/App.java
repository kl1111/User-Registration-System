package com.fdmgroup.UserRegistrationSystem;

import java.util.Scanner;

public class App {
    public static void main( String[] args ) {
    	logonMain();
    	//registerMain();
    }
    
    public static void logonMain() {
    	
    }
    
    public static void registerMain() {
    	RegistrationController regCtrl = new RegistrationController(
    			new UserFactory(), 
    			new FileWriteCommand(), 
    			new FileReadCommand());
    	
    	Scanner scanner = new Scanner(System.in);
    	
    	while (true) {
    		System.out.println("Username: ");
    		String username = scanner.next();
    		System.out.println("Password: ");
			String password = scanner.next();
    		System.out.println("Role: ");
			String role = scanner.next();
			try {
				regCtrl.registerNewUser(username, password, role);
				// get here, all OK!
				System.out.println("SUCCESS");
			} catch (UserRegistrationException e) {
				// get here, not OK!
				System.out.println("FAIL, TRY AGAIN ...");
			}
    	}
    }
}
