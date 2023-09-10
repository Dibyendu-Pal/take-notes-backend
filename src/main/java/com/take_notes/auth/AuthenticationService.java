package com.take_notes.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.take_notes.config.JwtService;
import com.take_notes.entities.User;
import com.take_notes.models.UserDetailsImpl;
import com.take_notes.repositories.UserRepository;

@Service
public class AuthenticationService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	public AuthenticationResponce register(RegisterRequest registerRequest) {

		User getUserByUsername = userRepository.findByUsername(registerRequest.getUsername()).orElse(null);

		if (getUserByUsername != null) {
			throw new UsernameNotFoundException("User Already Exists");
		}

		User user = User.builder().firstName(registerRequest.getFirstName()).lastName(registerRequest.getLastName())
				.username(registerRequest.getUsername()).password(passwordEncoder.encode(registerRequest.getPassword()))
				.build();

		userRepository.save(user);

		String jwtToken = jwtService.generateToken(new UserDetailsImpl(user));

		AuthenticationResponce authenticationResponce = new AuthenticationResponce(user, jwtToken);

		return authenticationResponce;
	}

	public AuthenticationResponce login(AuthenticationRequest authenticationRequest) {
		System.out.println("hello");

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUsername(), authenticationRequest.getPassword());

		Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		User user = userRepository.findByUsername(authenticationRequest.getUsername())
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

		System.out.println("here");

		String jwtToken = jwtService.generateToken(new UserDetailsImpl(user));

		AuthenticationResponce authenticationResponce = new AuthenticationResponce(user, jwtToken);

		return authenticationResponce;
	}
}
