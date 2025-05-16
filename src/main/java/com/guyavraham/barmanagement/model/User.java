package com.guyavraham.barmanagement.model;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.persistence.Table;

@Entity
@Table(name = "app_user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String name;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}