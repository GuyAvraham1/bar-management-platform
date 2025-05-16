package com.guyavraham.barmanagement.controller;

import com.guyavraham.barmanagement.util.HtmlBuilder;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {

    @GetMapping("/hello")
    public String hello() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = auth != null && auth.isAuthenticated() &&
                                 !(auth instanceof AnonymousAuthenticationToken);

        String content = "<h2>Welcome to the Bar Management Platform!</h2>";
        return HtmlBuilder.wrapHtml("Home", content, isAuthenticated);
    }

    // Logout page is now redundant since we have the navbar
    // You can remove this method
}