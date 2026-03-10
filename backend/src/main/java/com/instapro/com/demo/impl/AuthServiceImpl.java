package com.instapro.com.demo.impl;

import com.instapro.com.demo.entity.User;
import com.instapro.com.demo.repository.UserRepository;
import com.instapro.com.demo.security.JwtService;
import com.instapro.com.demo.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String signup(User user) {

        if (userRepo.findByUsername(user.getUsername()).isPresent()) {
            return "Username already exists";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");

        userRepo.save(user);
        return "Registered successfully";
    }

    @Override
    public String login(String username, String password) {

        User user = userRepo.findByUsername(username).orElse(null);

        if (user == null) return "invalid";

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return "invalid";
        }

        // ✅ FIX: ROLE_ hata diya
        return jwtService.generateToken(
                user.getUsername(),
                user.getRole()
        );
    }
}
