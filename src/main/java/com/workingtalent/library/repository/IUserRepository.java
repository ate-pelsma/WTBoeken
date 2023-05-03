package com.workingtalent.library.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.workingtalent.library.entities.User;

@Component
public interface IUserRepository extends CrudRepository<User, Long> {

	Optional<User> findByEmail(String email);

}
