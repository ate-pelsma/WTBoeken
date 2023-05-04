package com.workingtalent.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.workingtalent.library.service.JpaUserDetailsService;
import com.workingtalent.library.utils.JwtFilter;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class SecurityConfig{
	
	@Autowired
	private JwtFilter jwtFilter;
	
	private final JpaUserDetailsService jpaUserDetailsService;
	
	public SecurityConfig(JpaUserDetailsService jpaUserDetailsService) {
		this.jpaUserDetailsService = jpaUserDetailsService;
	}
	
	@Bean
	public AuthenticationManager authManager(JpaUserDetailsService jpaUserDetailsService) 
	{
		var authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(jpaUserDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return new ProviderManager(authProvider);

	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
			http
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests( auth -> auth
						//Everyone can visit the start page ("/"), all other pages require a log in.
						.requestMatchers("/login").permitAll()
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
						.anyRequest().permitAll()) // TODO moet naar authenticated
				//Load login request
				.userDetailsService(jpaUserDetailsService)
				.headers(headers -> headers.frameOptions());
			
			return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
