package com.rpbuilder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.rpbuilder.model.User;
import com.rpbuilder.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // Show the registration form
    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";  // This is your registration page (create this HTML)
    }

    // Handle registration form submission
    @PostMapping("/register")
    public String registerUser(User user, Model model) {
        // Check if user already exists by username
        User existingUser = userService.findUserByUsername(user.getUsername());
        if (existingUser != null) {
            model.addAttribute("error", "Username already registered!");
            return "register";  // Redirect back to registration page with error
        }

        // Save the new user to the database without password encoding
        userService.saveUser(user);
        model.addAttribute("message", "User registered successfully!");
        return "login";  // Redirect to login page (create this HTML)
    }

    // Show login form
    @GetMapping("/user-login")
    public String showLoginForm() {
        return "login";  // This is your login page (create this HTML)
    }

    // Handle login (basic example)
    @PostMapping("/user-login")
    public String loginUser(String username, String password, Model model) {
        // Find the user by username
        User user = userService.findUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            model.addAttribute("user", user);
            return "dashboard";  // Redirect to user dashboard (create this HTML)
        } else {
            model.addAttribute("error", "Invalid credentials!");
            return "login";  // Redirect back to login page with error
        }
    }

    

}
