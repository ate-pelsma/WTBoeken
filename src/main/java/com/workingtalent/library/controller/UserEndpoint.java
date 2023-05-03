package com.workingtalent.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.workingtalent.library.entities.User;
import com.workingtalent.library.service.UserService;

@CrossOrigin(maxAge=3600)
@RestController
public class UserEndpoint {
	
	@Autowired
	private UserService userService;
	
	PasswordEncoder encoder;
	
	@GetMapping("/user/all")
	public Iterable<User> findAll() {
		return userService.findAll();
	}
	
//	@GetMapping("/user")
//	public User findById(@PathVariable("id") User user) {
//		return user;
//	}
	
	@PostMapping("/register")
	public ResponseEntity<Object> saveUser(@RequestBody User user) {
		userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
