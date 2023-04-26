package com.workingtalent.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workingtalent.library.service.UserService;

@RestController
public class UserEndpoint {
	
	@Autowired
	private UserService service;
	
	@GetMapping("/UserCreate/{permission}")
	public void makeNew() {
		System.out.println("In Endpoint");
		service.save();
	}

}
