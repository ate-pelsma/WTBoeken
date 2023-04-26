package com.workingtalent.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workingtalent.library.repository.IUserRepository;

@Service
public class UserService {
	
	@Autowired
	private IUserRepository repo;
	
	public void save() {
		System.out.println("In Service");
	}

}
