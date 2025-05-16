package com.guyavraham.barmanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello! Welcome to the Bar Management Platform!";
    }

    @GetMapping("/logout-page")
    public String logoutPage() {
        return "<html><body>"
                + "<h2>Logout from Bar Management System</h2>"
                + "<form action='/logout' method='post'>"
                + "<input type='hidden' name='_csrf' value=''/>"
                + "<button type='submit'>Logout</button>"
                + "</form>"
                + "</body></html>";
    }

}
