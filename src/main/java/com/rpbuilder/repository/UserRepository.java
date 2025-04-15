package com.rpbuilder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rpbuilder.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);  // Custom query to find user by username
}
