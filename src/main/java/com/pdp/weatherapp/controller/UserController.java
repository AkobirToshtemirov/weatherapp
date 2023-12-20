package com.pdp.weatherapp.controller;

import com.pdp.weatherapp.entity.User;
import com.pdp.weatherapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public User getUserById(@PathVariable Long id) {
        // Logic to get a user by ID
        // Example: userService.findById(id);
        // Return the user or appropriate response
        return userService.findById(id);
    }

    @PutMapping("/update")
    public String updateUser(@RequestBody User user) {
        // Logic to update a user
        // Example: userService.updateUser(user);
        // Return success message or appropriate response
        return "User updated successfully";
    }

    @PostMapping("/subscribe/{cityId}")
    public String subscribeToCity(@PathVariable Long cityId) {
        // Logic to subscribe to a city
        // Example: userService.subscribeToCity(cityId);
        // Return success message or appropriate response
        return "Subscribed to city successfully";
    }
}
