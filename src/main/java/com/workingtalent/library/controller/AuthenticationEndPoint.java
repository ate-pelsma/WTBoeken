package com.workingtalent.library.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.workingtalent.library.entities.LoginRequest;
import com.workingtalent.library.utils.JwtUtils;

@RestController
public class AuthenticationEndPoint {

	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public void AuthController(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
		System.out.println(loginRequest);
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		System.out.println(authentication);
		System.out.println("Hallo");
	    String token = JwtUtils.generateToken(authentication);
		System.out.println("hallo");
		
		return ResponseEntity.ok(Map.of("token", token, "message", "Login succesfull!"));
	}	
}
