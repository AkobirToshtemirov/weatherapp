package com.pdp.weatherapp.repository.impl;

import com.pdp.weatherapp.entity.City;
import com.pdp.weatherapp.entity.User;
import com.pdp.weatherapp.entity.WeatherData;
import com.pdp.weatherapp.mapper.UserRowMapper;
import com.pdp.weatherapp.repository.UserRepository;
import com.pdp.weatherapp.repository.WeatherDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final WeatherDataRepository weatherDataRepository;

    @Autowired
    public UserRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate, WeatherDataRepository weatherDataRepository) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.weatherDataRepository = weatherDataRepository;
    }

    @Override
    public User findById(Long id) {
        String sql = "SELECT * FROM user WHERE id = :id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);

        return namedParameterJdbcTemplate.queryForObject(sql, paramMap, new UserRowMapper());
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM user";

        return namedParameterJdbcTemplate.query(sql, new UserRowMapper());
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO user (username, password) VALUES (:username, :password)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("username", user.getUsername());
        paramMap.put("password", user.getPassword());

        namedParameterJdbcTemplate.update(sql, paramMap);
    }

    @Override
    public void delete(User user) {
        String sql = "DELETE FROM user WHERE id = :id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", user.getId());

        namedParameterJdbcTemplate.update(sql, paramMap);
    }

    @Override
    public User findByUsername(String username) {
        String sql = "SELECT * FROM user WHERE username = :username";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("username", username);

        return namedParameterJdbcTemplate.queryForObject(sql, paramMap, new UserRowMapper());
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE user SET username = :username, password = :password WHERE id = :id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", user.getId());
        paramMap.put("username", user.getUsername());
        paramMap.put("password", user.getPassword());

        namedParameterJdbcTemplate.update(sql, paramMap);
    }

    @Override
    public void subscribeToCity(User user, City city) {
        String sql = "INSERT INTO user_cities (user_id, city_id) VALUES (:userId, :cityId)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userId", user.getId());
        paramMap.put("cityId", city.getId());

        namedParameterJdbcTemplate.update(sql, paramMap);
    }

    @Override
    public List<WeatherData> getWeatherForCity(User user, City city) {
        if (!user.getSubscribedCities().contains(city)) {
            throw new IllegalArgumentException("User is not subscribed to this city.");
        }

        return weatherDataRepository.findByCity(city);
    }

}

