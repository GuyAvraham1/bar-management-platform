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
public class AuthTestController {

    @GetMapping("/admin/test")
    public String adminOnly() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = auth != null && auth.isAuthenticated() &&
                                 !(auth instanceof AnonymousAuthenticationToken);

        String content = "<h2>Admin area</h2><p>Hello, " + auth.getName() + "!</p>";
        return HtmlBuilder.wrapHtml("Admin Area", content, isAuthenticated);
    }

    @GetMapping("/bartender/test")
    public String bartenderAccess() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = auth != null && auth.isAuthenticated() &&
                                 !(auth instanceof AnonymousAuthenticationToken);

        String content = "<h2>Bartender area</h2><p>Hello, " + auth.getName() + "!</p>";
        return HtmlBuilder.wrapHtml("Bartender Area", content, isAuthenticated);
    }

    @GetMapping("/authenticated")
    public String authenticatedOnly() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = auth != null && auth.isAuthenticated() &&
                                 !(auth instanceof AnonymousAuthenticationToken);

        String content = "<h2>Authenticated area</h2><p>Hello, " + auth.getName() + "!</p>";
        return HtmlBuilder.wrapHtml("Authenticated Area", content, isAuthenticated);
    }
}