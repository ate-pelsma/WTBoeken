package com.workingtalent.library.repository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.workingtalent.library.entities.User;

import jakarta.annotation.PostConstruct;

@Component
public class IUserDataInitializer {

    private final PasswordEncoder passwordEncoder;
    private final IUserRepository userRepository;

    public IUserDataInitializer(PasswordEncoder passwordEncoder, IUserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }
}