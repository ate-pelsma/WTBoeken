package com.workingtalent.library.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.workingtalent.library.entities.User;
import com.workingtalent.library.repository.IUserRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService{

	//Provide access to user data
	@Autowired
	private final IUserRepository userRepository;
	
	public JpaUserDetailsService(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	//Look for the 'Optional' user (given there is one) by their email (username) in the database. If there is no user with that email, throw UsernameNotFoundException. 
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> userOpt = userRepository.findByEmail(email);
		return userOpt.orElseThrow(() -> new UsernameNotFoundException("Bad credentials"));
				
	}
	
}
