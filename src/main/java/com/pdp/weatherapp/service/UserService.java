package com.pdp.weatherapp.service;

import com.pdp.weatherapp.entity.City;
import com.pdp.weatherapp.entity.User;
import com.pdp.weatherapp.entity.WeatherData;

import java.util.List;

public interface UserService extends CrudService<User, Long> {
    User findByUsername(String username);

    void update(User user);

    void subscribeToCity(User user, City city);

    List<WeatherData> getWeatherForCity(User user, City city);
}