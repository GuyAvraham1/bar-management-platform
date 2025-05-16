package com.guyavraham.barmanagement.config;

import com.guyavraham.barmanagement.model.User;
import com.guyavraham.barmanagement.model.UserRole;
import com.guyavraham.barmanagement.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(UserService userService, PasswordEncoder passwordEncoder) {
        return args -> {
            // Create admin user if it doesn't exist
            if (!userService.existsByUsername("admin")) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword("admin"); // Will be encoded by the service
                admin.setName("Admin User");
                admin.setRole(UserRole.MANAGER);
                userService.createUser(admin);
                System.out.println("Admin user created");
            }
            else {
                System.out.println("Admin user already exists");
                User admin = userService.getUserByUsername("admin");
                System.out.println("Try logging in with: admin / admin");
                System.out.println("Stored password hash: " + admin.getPassword());

                //Test if passwords matches
                boolean matches = passwordEncoder.matches("admin", admin.getPassword());
                System.out.println("Password 'admin' matches stored hash: " + matches);
            }
        };
    }
}