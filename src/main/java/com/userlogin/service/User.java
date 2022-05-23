package com.userlogin.service;

public class User {
	private String Name, Username, Password;
	
	public User() {
	}

	public void setName(String name) {
		Name = name;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public User(String name, String username, String password) {
		Name = name;
		Username = username;
		Password = password;
	}
}
