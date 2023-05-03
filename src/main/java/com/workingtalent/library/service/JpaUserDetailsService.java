package com.workingtalent.library.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.workingtalent.library.entities.SecurityUser;
import com.workingtalent.library.repository.IUserRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService{

	//Provide access to user data
	private final IUserRepository userRepository;
	
	public JpaUserDetailsService(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	//Look for the 'Optional' user (given there is one) by their email (username) in the database. If there is no user with that email, throw UsernameNotFoundException. 
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		return userRepository.findByEmail(email)
				.map(SecurityUser::new)
				.orElseThrow(() -> new UsernameNotFoundException("Username not found: " + email));
	}
	
}
