package com.workingtalent.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workingtalent.library.entities.User;
import com.workingtalent.library.repository.IUserRepository;
import com.workingtalent.library.service.UserService;

@CrossOrigin(maxAge=3600)
@RestController
@RequestMapping("/user")
public class UserEndpoint {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@GetMapping("/all")
	public Iterable<User> findAll() {
		return userService.findAll();
	}
	
//	@GetMapping("/user")
//	public User findById(@PathVariable("id") User user) {
//		return user;
//	}
	
	@PostMapping("/save")
	public ResponseEntity<Object> saveUser(@RequestBody User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping("/admin/{userid}")
	public void updateUserAdmin(@PathVariable long userid, @RequestBody User user) {
		User oldUser = userService.findUser(userid).get();
		userService.updateUserAdmin(user, oldUser);
	}
	
	@PutMapping("/update/{userid}")
	public void updateUser(@PathVariable long userid, @RequestBody User user) {
		User oldUser = userService.findUser(userid).get();
		userService.updateUser(user, oldUser);
	}
	
	@GetMapping("/inactive/{userid}")
	public void inactiveUser(@PathVariable long userid) {
		User user = userService.findUser(userid).get();
		userService.inactiveUser(user);
	}

}
