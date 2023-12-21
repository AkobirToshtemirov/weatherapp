package com.pdp.weatherapp.controller;

import com.pdp.weatherapp.entity.City;
import com.pdp.weatherapp.entity.User;
import com.pdp.weatherapp.entity.WeatherData;
import com.pdp.weatherapp.service.CityService;
import com.pdp.weatherapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final CityService cityService;

    @Autowired
    public UserController(UserService userService, CityService cityService) {
        this.userService = userService;
        this.cityService = cityService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public User getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PreAuthorize("@userService.isAuthorized(#user.id)")
    @PutMapping("/update")
    public String updateUser(@RequestBody User user) {
        userService.update(user);
        return "User updated successfully";
    }

    @PostMapping("/{userId}/subscribe/{cityId}")
    public String subscribeToCity(@PathVariable Long userId, @PathVariable Long cityId) {
        User user = userService.findById(userId);
        City city = cityService.findById(cityId);
        userService.subscribeToCity(user, city);
        return "Subscribed to city successfully";
    }

    @GetMapping("/cities")
    public List<City> getAllCities() {
        return cityService.findAll();
    }

    @GetMapping("/{userId}/weather/{cityId}")
    public List<WeatherData> getWeatherForCity(@PathVariable Long userId, @PathVariable Long cityId) {
        User user = userService.findById(userId);
        City city = cityService.findById(cityId);
        return userService.getWeatherForCity(user, city);
    }
}
