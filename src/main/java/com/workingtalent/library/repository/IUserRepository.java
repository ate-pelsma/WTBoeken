package com.workingtalent.library.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.workingtalent.library.entities.User;

@Component
public interface IUserRepository extends CrudRepository<User, Long> {

	//Find a user by email
	Optional<User> findByEmail(String email);

}
