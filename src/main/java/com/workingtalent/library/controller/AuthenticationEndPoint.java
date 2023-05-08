package com.workingtalent.library.controller;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.workingtalent.library.entities.LoginRequest;
import com.workingtalent.library.entities.User;
import com.workingtalent.library.utils.JwtUtils;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class AuthenticationEndPoint {

	@Autowired
	public JwtUtils jwtUtils;
	
	@Autowired
	public PasswordEncoder passwordEncoder;
	
	@Autowired
	public AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
			User user = (User) authentication.getPrincipal();
	        String token = jwtUtils.generateToken(user);
	        Map<String, String> response = new HashMap<>();
	        response.put("authorization", token);
	        response.put("user", user.toString());
	        return ResponseEntity.ok(response);
		}
		catch(BadCredentialsException ex){
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}	
}