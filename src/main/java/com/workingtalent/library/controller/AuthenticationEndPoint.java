package com.workingtalent.library.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.workingtalent.library.entities.LoginRequest;
import com.workingtalent.library.entities.User;
import com.workingtalent.library.utils.JwtUtils;

@RestController
public class AuthenticationEndPoint {

	@Autowired
	public JwtUtils jwtUtils;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

			User user = (User) authentication.getPrincipal();
			System.out.println(jwtUtils.generateToken(user));
			return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwtUtils.generateToken(user)).body(user);
		}
		catch(BadCredentialsException ex){
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}	
}
