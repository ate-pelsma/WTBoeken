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

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.workingtalent.library.service.JpaUserDetailsService;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class SecurityConfig{
	
	private final JpaUserDetailsService jpaUserDetailsService;
	
	public SecurityConfig(JpaUserDetailsService jpaUserDetailsService) {
		this.jpaUserDetailsService = jpaUserDetailsService;
	}
	
	@Bean
	public AuthenticationManager authManager(JpaUserDetailsService jpaUserDetailsService) 
	{
		System.out.println("Ik begin met autoriseren");
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
				//Load login request
				.userDetailsService(jpaUserDetailsService)
				.headers(headers -> headers.frameOptions());
				//Removes HTTP sessions
//				.formLogin()
////					.loginPage("/login")
//					.defaultSuccessUrl("/")
//					.permitAll()
//					.and()
//				.logout()
//					.logoutUrl("/logout")
//					.permitAll();
			
			return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
