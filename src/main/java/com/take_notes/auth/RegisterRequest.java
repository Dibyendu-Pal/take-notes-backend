package com.take_notes.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequest {
	
	private String firstName;

    private String lastName;

    private String username;

    private String password;
}
