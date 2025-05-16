package com.guyavraham.barmanagement.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthTestController {

    @GetMapping("/admin/test")
    public String adminOnly() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "Admin area - Hello, " + auth.getName() + "!";
    }

    @GetMapping("/bartender/test")
    public String bartenderAccess() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "Bartender area - Hello, " + auth.getName() + "!";
    }

    @GetMapping("/authenticated")
    public String authenticatedOnly() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "Authenticated area - Hello, " + auth.getName() + "!";
    }
}