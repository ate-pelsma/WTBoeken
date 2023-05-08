package com.workingtalent.library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.workingtalent.library.entities.User;

@Component
public interface IUserRepository extends JpaRepository<User, Long> {

	
	//Find a user by email
	Optional<User> findByEmail(String email);
	
}
