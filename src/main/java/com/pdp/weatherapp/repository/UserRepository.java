package com.pdp.weatherapp.repository;

import com.pdp.weatherapp.entity.City;
import com.pdp.weatherapp.entity.User;
import com.pdp.weatherapp.entity.WeatherData;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

    void update(User user);

    void subscribeToCity(User user, City city);

    List<WeatherData> getWeatherForCity(User user, City city);
}
