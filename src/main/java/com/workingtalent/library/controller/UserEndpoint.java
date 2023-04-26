package com.workingtalent.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.workingtalent.library.entities.User;
import com.workingtalent.library.service.UserService;

@RestController
public class UserEndpoint {
	
	@Autowired
	private UserService service;
	
	@PostMapping("/user/create")
	public void createUser(@RequestBody User user) {
		service.save(user);
	}
	
	@GetMapping("/allusers")
	public Iterable<User> findAll() {
		return service.findAll();
	}

}
