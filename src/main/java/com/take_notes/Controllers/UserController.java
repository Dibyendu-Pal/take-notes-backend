package com.take_notes.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.take_notes.entities.User;
import com.take_notes.services.UserService;

//@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public User getUserByToken(@RequestHeader("Authorization") String token) {
		return userService.getUserByToken(token);
	}
	
	@PutMapping("/{id}")
	public User updateUser(@RequestHeader("Authorization") String token, @PathVariable int id, @RequestBody User user) {
		return userService.updateUser(id,token,user);
	}
	
}
