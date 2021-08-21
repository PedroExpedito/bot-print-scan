package com.pedroexpedito.bot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AllowList {
	static List<String> usernames = null;
	static String fullPath = null;
	static File usernamesFile;
	
	private AllowList() {}

	private static void generateList() {
		fullPath = System.getProperty("user.home") + "/usernames.csv";
		usernamesFile = new File(fullPath);
		usernames = new ArrayList<>();

		if (usernamesFile.isFile()) {
			BufferedReader csvReader;
			String row;
			try {
				csvReader = new BufferedReader(new FileReader(usernamesFile));
				while ((row = csvReader.readLine()) != null) {
					String[] data = row.split(",");
					for (String name : data) {
						usernames.add(name.trim());
					}
				}
				csvReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	}

	static boolean contains(String username) {
		if(usernames == null) {
			generateList();
		}
		return usernames.contains(username);
	}
}
