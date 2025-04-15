package com.rpbuilder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rpbuilder.model.User;
import com.rpbuilder.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Find user by username
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Save the user to the database
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
