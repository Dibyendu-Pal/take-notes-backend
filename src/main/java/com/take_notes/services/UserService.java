package com.take_notes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.take_notes.config.JwtService;
import com.take_notes.entities.User;
import com.take_notes.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserRepository userRepository;

	public User getUserByToken(String token) {

		String jwtToken = token.substring(7);

		String username = jwtService.extractUsername(jwtToken);
		return userRepository.findByUsername(username).orElseThrow();
	}

	public User updateUser(int id, String token, User user) {
		User userById = userRepository.findById(id).orElseThrow();

		userById.setFirstName(user.getFirstName());
		userById.setLastName(user.getLastName());

		return userRepository.save(userById);
	}
}
