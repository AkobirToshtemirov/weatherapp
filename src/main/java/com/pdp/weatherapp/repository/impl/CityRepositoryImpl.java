package com.pdp.weatherapp.repository.impl;

import com.pdp.weatherapp.entity.City;
import com.pdp.weatherapp.mapper.CityRowMapper;
import com.pdp.weatherapp.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CityRepositoryImpl implements CityRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public CityRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public City findById(Long id) {
        String sql = "SELECT * FROM city WHERE id = :id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);

        return namedParameterJdbcTemplate.queryForObject(sql, paramMap, new CityRowMapper());
    }

    @Override
    public List<City> findAll() {
        String sql = "SELECT * FROM city";

        return namedParameterJdbcTemplate.query(sql, new CityRowMapper());
    }

    @Override
    public void save(City city) {
        String sql = "INSERT INTO city (cityName) VALUES (:cityName)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("cityName", city.getCityName());

        namedParameterJdbcTemplate.update(sql, paramMap);
    }

    @Override
    public void delete(City city) {
        String sql = "DELETE FROM city WHERE id = :id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", city.getId());

        namedParameterJdbcTemplate.update(sql, paramMap);
    }
}
