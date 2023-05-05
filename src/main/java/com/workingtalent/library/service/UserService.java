package com.workingtalent.library.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workingtalent.library.controller.AuthenticationEndPoint;
import com.workingtalent.library.entities.User;
import com.workingtalent.library.repository.IUserRepository;

@Service
public class UserService {
	
	@Autowired
	private IUserRepository userRepo;
	
	@Autowired
	public AuthenticationEndPoint autenticationEndPoint;

	public Iterable<User> findAll() {
		return userRepo.findAll();
	}
	
	public Optional<User> findUser(long id) {
		return userRepo.findById(id);
	}

	public void saveUser(User user) {
		user.setPassword(autenticationEndPoint.passwordEncoder.encode(user.getPassword()));
		userRepo.save(user);
	}
	
	public void updateUser(User user, User oldUser) {
		if (!user.getName().equals("")) oldUser.setName((user.getName()));
		if (!user.getPassword().equals("")) oldUser.setPassword(autenticationEndPoint.passwordEncoder.encode(user.getPassword()));
		if (!user.getEmail().equals("")) oldUser.setEmail((user.getEmail()));
		userRepo.save(oldUser);
	}
	
	public void updateUserAdmin(User user, User oldUser) {
		if (!user.getName().equals("")) oldUser.setName(user.getName());
		if (!user.getPassword().equals("")) oldUser.setPassword(autenticationEndPoint.passwordEncoder.encode(user.getPassword()));
		if (!user.getEmail().equals("")) oldUser.setEmail(user.getEmail());
		if (!user.getPermissions().equals("")) oldUser.setPermissions(user.getPermissions());
		userRepo.save(oldUser);
	}

	public void inactiveUser(User user) {
		user.setName("");
		user.setEmail("");
		user.setPassword("");
		user.setPermissions("0");
		userRepo.save(user);
	}

}