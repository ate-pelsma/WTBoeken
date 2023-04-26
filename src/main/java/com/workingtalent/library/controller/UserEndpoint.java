package com.workingtalent.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.workingtalent.library.entities.User;
import com.workingtalent.library.service.UserService;

@RestController
public class UserEndpoint {
	
	@Autowired
	private UserService service;
	
	@GetMapping("/createuser/{name}")
	public void makeNew(@PathVariable("name") String name) {
		User user = new User();
		user.setName(name);
		service.save(user);
	}
	
	@GetMapping("/allusers")
	public Iterable<User> findAll() {
		return service.findAll();
	}

}
