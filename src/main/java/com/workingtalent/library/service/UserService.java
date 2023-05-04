package com.workingtalent.library.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workingtalent.library.entities.User;
import com.workingtalent.library.repository.IUserRepository;

@Service
public class UserService {
	
	@Autowired
	private IUserRepository userRepo;

	public Iterable<User> findAll() {
		return userRepo.findAll();
	}
	
	public Optional<User> findUser(long id) {
		return userRepo.findById(id);
	}

	public void saveUser(User user) {
		userRepo.save(user);
	}
	
	public void updateUser(User user, User oldUser) {
		oldUser.setName((user.getName()==null) ? oldUser.getName():user.getName());
		oldUser.setPassword((user.getPassword()==null) ? oldUser.getPassword():user.getPassword());
		oldUser.setEmail((user.getEmail()==null) ? oldUser.getEmail():user.getEmail());
		userRepo.save(oldUser);
	}
	
	public void updateUserAdmin(User user, User oldUser) {
		oldUser.setName((user.getName()==null) ? oldUser.getName():user.getName());
		oldUser.setPassword((user.getPassword()==null) ? oldUser.getPassword():user.getPassword());
		oldUser.setEmail((user.getEmail()==null) ? oldUser.getEmail():user.getEmail());
		oldUser.setPermissions((user.getPermissions()==null) ? oldUser.getPermissions():user.getPermissions());
		userRepo.save(oldUser);
	}

	public void inactiveUser(User user) {
		user.setName(" ");
		user.setEmail(" ");
		user.setPassword("inactaccpassword729");
		user.setPermissions("0");
		userRepo.save(user);
	}

}