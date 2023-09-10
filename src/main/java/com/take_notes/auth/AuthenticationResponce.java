package com.take_notes.auth;

import com.take_notes.entities.User;

import lombok.Data;

@Data
public class AuthenticationResponce {
	private String firstName;

	private String lastName;

	private String username;

	private String token;

	public AuthenticationResponce(User user, String token) {
		super();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.username = user.getUsername();
		this.token = token;
	}
}
