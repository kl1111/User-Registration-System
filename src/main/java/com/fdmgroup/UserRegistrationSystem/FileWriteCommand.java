package com.fdmgroup.UserRegistrationSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class FileWriteCommand implements WriteCommand {

	public void writeUser(User user) {
		if (user == null)
			return;
		
		File file = new File(user.username + ".ser");
		
		OutputStream os = null;
		ObjectOutputStream oos = null;
		try {
			os = new FileOutputStream(file);
			oos = new ObjectOutputStream(os);
			oos.writeObject(user);
		} catch (Exception e) {
			return;
		} finally {
			try {
				oos.close();
				os.close();
			} catch (IOException e) {
				return;
			}
		}
	}

}







