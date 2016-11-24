package com.fdmgroup.UserRegistrationSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class FileReadCommand implements ReadCommand {

	public User readUser(String username) {
		User user = null;
		
		File file = new File(username + ".ser");
		if (!file.exists()) {
			return null;
		}
		
		InputStream is = null;
		ObjectInputStream ois = null;
		try {
			is = new FileInputStream(file);
			ois = new ObjectInputStream(is);
			user = (User) ois.readObject();
		} catch (Exception e) {
			return null;
		} finally {
			try {
				ois.close();
				is.close();
			} catch (IOException e) {
				return null;
			}
		}

		return user;
	}

}
