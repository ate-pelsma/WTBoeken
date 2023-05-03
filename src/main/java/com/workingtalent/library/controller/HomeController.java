package com.workingtalent.library.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	//Test class to check Role functionality.
	@GetMapping("/")
	public String home() 
	{
		return "Hello anybody!";
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/user")
	public String user() 
	{
		return "Hello, user!";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/admin")
	public String admin()
	{
		return "Hello, admin!";
	}
	
}
