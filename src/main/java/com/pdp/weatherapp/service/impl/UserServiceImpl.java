package com.pdp.weatherapp.service.impl;

import com.pdp.weatherapp.entity.City;
import com.pdp.weatherapp.entity.User;
import com.pdp.weatherapp.entity.WeatherData;
import com.pdp.weatherapp.repository.UserRepository;
import com.pdp.weatherapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void update(User user) {
        userRepository.update(user);
    }

    @Override
    public void subscribeToCity(User user, City city) {
        userRepository.subscribeToCity(user, city);
    }

    @Override
    public List<WeatherData> getWeatherForCity(User user, City city) {
        return userRepository.getWeatherForCity(user, city);
    }
}