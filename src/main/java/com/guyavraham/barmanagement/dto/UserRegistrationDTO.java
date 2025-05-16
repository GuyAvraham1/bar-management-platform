package com.guyavraham.barmanagement.dto;

import com.guyavraham.barmanagement.model.UserRole;
import lombok.Data;

@Data
public class UserRegistrationDTO {
    private String username;
    private String password;
    private String name;
    private UserRole role;
}