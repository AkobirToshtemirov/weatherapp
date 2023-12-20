package com.pdp.weatherapp.controller;

import com.pdp.weatherapp.entity.User;
import com.pdp.weatherapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/auth")
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        // Logic for user login, authentication, and token generation
        // Example: userService.authenticateUser(user.getUsername(), user.getPassword());
        // Return token or appropriate response
        return "Login Successful";
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        // Logic for user registration
        // Example: userService.registerUser(user);
        // Return success message or appropriate response
        return "Registration Successful";
    }

    @PostMapping("/logout")
    public String logout() {
        // Logic for user logout
        // Example: userService.logoutUser();
        // Return success message or appropriate response
        return "Logout Successful";
    }
}
