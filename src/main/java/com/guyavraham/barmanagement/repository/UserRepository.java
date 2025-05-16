package com.guyavraham.barmanagement.repository;

import com.guyavraham.barmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Find a user by their username
    User findByUsername(String username);

    // Check if a username exists
    boolean existsByUsername(String username);
}