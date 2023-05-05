package com.workingtalent.library.repository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.workingtalent.library.entities.User;

@Component
public class IUserDataInitializer {

    private final PasswordEncoder passwordEncoder;
    private final IUserRepository userRepository;

    public IUserDataInitializer(PasswordEncoder passwordEncoder, IUserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        
        User user = new User();
        user.setEmail("Geoffry");
        user.setPassword(passwordEncoder.encode("password"));
        
    }
}