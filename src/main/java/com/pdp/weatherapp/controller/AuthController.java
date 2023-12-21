package com.pdp.weatherapp.controller;

import com.pdp.weatherapp.dto.UserRegisterDto;
import com.pdp.weatherapp.entity.Role;
import com.pdp.weatherapp.entity.User;
import com.pdp.weatherapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerPage() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserRegisterDto dto) {
        User user = User.builder()
                .username(dto.username())
                .password(dto.password())
                .roles(List.of(Role.builder().roleCode("USER").build()))
                .build();

        userService.save(user);

        return "redirect:/auth/login";
    }

    @GetMapping("/login")
    public ModelAndView loginPage(@RequestParam(required = false) String error) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("auth/login");
        modelAndView.addObject("errorMessage", error);

        return modelAndView;
    }

    @GetMapping("/logout")
    public String logoutPage() {
        return "auth/logout";
    }

}
