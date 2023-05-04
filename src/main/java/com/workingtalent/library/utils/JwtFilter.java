package com.workingtalent.library.utils;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.workingtalent.library.entities.SecurityUser;
import com.workingtalent.library.entities.User;
import com.workingtalent.library.repository.IUserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{

	@Autowired
	private IUserRepository userRepo;
	
	@Autowired
	private JwtUtils jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		
		//Get authorization header and validate.
		final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		if(!StringUtils.hasText(header) || (StringUtils.hasText(header) || !header.startsWith("Bearer "))) {
			chain.doFilter(request, response);
			return;
		}
		
		final String token = header.split(" ")[1].trim();
		
		//check if error
		User userDetails = userRepo
        		.findByEmail(jwtUtil.getUsernameFromToken(token))
        		.orElse(null);
		
		//Get jwt token and validate

		if(!jwtUtil.validateToken(token, userDetails)) {
			chain.doFilter(request, response);
			return;
		}
			
		UsernamePasswordAuthenticationToken	authenication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails == null ? List.of() : userDetails.getAuthorities());

		authenication.setDetails(
				new WebAuthenticationDetailsSource().buildDetails(request));
		
		SecurityContextHolder.getContext().setAuthentication(authenication);
		chain.doFilter(request, response);
		
	}
}
