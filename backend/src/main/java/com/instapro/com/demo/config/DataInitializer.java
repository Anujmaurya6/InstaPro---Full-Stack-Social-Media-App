package com.instapro.com.demo.config;

import com.instapro.com.demo.entity.User;
import com.instapro.com.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initAdmin(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepo.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin@123"));
                admin.setEmail("admin@gmail.com");
                admin.setMobile("9999999999");
                admin.setRole("ADMIN");
                userRepo.save(admin);
                System.out.println("✅ Admin created");
            } else {
                System.out.println("⚡ Admin already exists");
            }
        };
    }
}