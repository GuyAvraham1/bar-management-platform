package com.guyavraham.barmanagement.controller;

import com.guyavraham.barmanagement.model.User;
import com.guyavraham.barmanagement.model.UserRole;
import com.guyavraham.barmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        if (userService.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/create-test-user")
    public ResponseEntity<String> createTestUser() {
        if (!userService.existsByUsername("testuser")) {
            User user = new User();
            user.setUsername("testuser");
            user.setPassword("password");
            user.setName("Test User");
            user.setRole(UserRole.BARTENDER);
            User savedUser = userService.createUser(user);
            return ResponseEntity.ok("Test user created. Username: testuser, Password: password, Encoded password: " + savedUser.getPassword());
        }
        return ResponseEntity.ok("Test user already exists");
    }
}