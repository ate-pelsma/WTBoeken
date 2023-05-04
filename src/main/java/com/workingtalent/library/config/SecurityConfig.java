package com.workingtalent.library.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
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
						.anyRequest().authenticated())
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
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
