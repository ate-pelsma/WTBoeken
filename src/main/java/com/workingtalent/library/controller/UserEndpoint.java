package com.workingtalent.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.workingtalent.library.entities.User;
import com.workingtalent.library.service.UserService;

@CrossOrigin(maxAge=3600)
@RestController
public class UserEndpoint {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/user/all")
	public Iterable<User> findAll() {
		return userService.findAll();
	}
	
	@PostMapping("/user/save")
	public void saveUser(@RequestBody User user) {
		userService.saveUser(user);
	}
	
	@PutMapping("/user/update/{userid}")
	public void updateUser(@PathVariable long userid, @RequestBody User user) {
		
	}

}
