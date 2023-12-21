package com.pdp.weatherapp.controller;

import com.pdp.weatherapp.entity.City;
import com.pdp.weatherapp.service.CityService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/cities")
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public String getAllCities(Model model) {
        List<City> cities = cityService.findAll();
        model.addAttribute("cities", cities);
        return "list-cities"; // Thymeleaf will look for list-cities.html
    }

    @GetMapping("/{id}")
    public String getCityById(@PathVariable Long id, Model model) {
        City city = cityService.findById(id);
        model.addAttribute("city", city);
        return "city-detail"; // Thymeleaf will look for city-detail.html
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String addCity(@ModelAttribute City city) {
        cityService.save(city);
        return "redirect:/admin/cities";
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateCity(@PathVariable Long id, @ModelAttribute City city) {
        city.setId(id);
        cityService.update(city);
        return "redirect:/admin/cities";
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteCity(@PathVariable Long id) {
        City city = cityService.findById(id);
        cityService.delete(city);
        return "redirect:/admin/cities";
    }
}