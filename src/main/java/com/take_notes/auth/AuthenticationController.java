package com.take_notes.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping("/register")
	@ResponseStatus(value = HttpStatus.CREATED)
	public AuthenticationResponce register(@RequestBody RegisterRequest registerRequest) {
		return authenticationService.register(registerRequest);
	}

	@PostMapping("/login")
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public AuthenticationResponce login(@RequestBody AuthenticationRequest authenticationRequest) {
		return authenticationService.login(authenticationRequest);
	}
}
