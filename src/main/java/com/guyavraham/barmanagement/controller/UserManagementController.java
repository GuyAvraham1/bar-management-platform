package com.guyavraham.barmanagement.controller;

import com.guyavraham.barmanagement.dto.UserRegistrationDTO;
import com.guyavraham.barmanagement.model.User;
import com.guyavraham.barmanagement.model.UserRole;
import com.guyavraham.barmanagement.service.UserService;
import com.guyavraham.barmanagement.util.HtmlBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
public class UserManagementController {

    private final UserService userService;

    @Autowired
    public UserManagementController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register-form")
    public String getRegistrationForm() {
        String content = "<h2>Register New User</h2>" +
                "<form action='/api/admin/users/register' method='post'>" +
                "Username: <input type='text' name='username' required><br><br>" +
                "Password: <input type='password' name='password' required><br><br>" +
                "Name: <input type='text' name='name' required><br><br>" +
                "Role: <select name='role'>" +
                "<option value='BARTENDER'>Bartender</option>" +
                "<option value='MANAGER'>Manager</option>" +
                "</select><br><br>" +
                "<button type='submit'>Register User</button>" +
                "</form>";

        return HtmlBuilder.wrapHtml("Register User", content, true);
    }

    @PostMapping("/register")
    public String registerUser(UserRegistrationDTO registrationDTO) {
        if (userService.existsByUsername(registrationDTO.getUsername())) {
            return "<html><body>"
                    + "<h2>Registration Failed</h2>"
                    + "<p>Username already exists.</p>"
                    + "<a href='/api/admin/users/register-form'>Try again</a>"
                    + "</body></html>";
        }

        User user = new User();
        user.setUsername(registrationDTO.getUsername());
        user.setPassword(registrationDTO.getPassword());
        user.setName(registrationDTO.getName());
        user.setRole(registrationDTO.getRole());

        userService.createUser(user);

        return "<html><body>"
                + "<h2>User Registered Successfully</h2>"
                + "<p>Username: " + user.getUsername() + "</p>"
                + "<p>Role: " + user.getRole() + "</p>"
                + "<a href='/api/admin/users/register-form'>Register another user</a> | "
                + "<a href='/api/admin/users/list'>View all users</a>"
                + "</body></html>";
    }

    @GetMapping("/list")
    public String listUsers() {
        List<User> users = userService.getAllUsers();

        StringBuilder html = new StringBuilder();
        html.append("<html><body>");
        html.append("<h2>User List</h2>");
        html.append("<table border='1'>");
        html.append("<tr><th>ID</th><th>Username</th><th>Name</th><th>Role</th></tr>");

        for (User user : users) {
            html.append("<tr>");
            html.append("<td>").append(user.getId()).append("</td>");
            html.append("<td>").append(user.getUsername()).append("</td>");
            html.append("<td>").append(user.getName()).append("</td>");
            html.append("<td>").append(user.getRole()).append("</td>");
            html.append("</tr>");
        }

        html.append("</table>");
        html.append("<br><a href='/api/admin/users/register-form'>Register new user</a>");
        html.append("</body></html>");

        return html.toString();
    }

}