package com.pdp.weatherapp.repository.impl;

import com.pdp.weatherapp.entity.City;
import com.pdp.weatherapp.entity.WeatherData;
import com.pdp.weatherapp.mapper.WeatherDataRowMapper;
import com.pdp.weatherapp.repository.WeatherDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class WeatherDataRepositoryImpl implements WeatherDataRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public WeatherDataRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public WeatherData findById(Long id) {
        String sql = "SELECT * FROM weather_data WHERE id = :id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);

        return namedParameterJdbcTemplate.queryForObject(sql, paramMap, new WeatherDataRowMapper());
    }

    @Override
    public List<WeatherData> findAll() {
        String sql = "SELECT * FROM weather_data";

        return namedParameterJdbcTemplate.query(sql, new WeatherDataRowMapper());
    }

    @Override
    public void save(WeatherData weatherData) {
        String sql = "INSERT INTO weather_data (city_id, temperature, humidity, date) " +
                "VALUES (:cityId, :temperature, :humidity, :date)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("cityId", weatherData.getCity().getId());
        paramMap.put("temperature", weatherData.getTemperature());
        paramMap.put("humidity", weatherData.getHumidity());
        paramMap.put("date", weatherData.getDate());

        namedParameterJdbcTemplate.update(sql, paramMap);
    }

    @Override
    public void delete(WeatherData weatherData) {
        String sql = "DELETE FROM weather_data WHERE id = :id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", weatherData.getId());

        namedParameterJdbcTemplate.update(sql, paramMap);
    }

    @Override
    public List<WeatherData> findByCity(City city) {
        String sql = "SELECT * FROM weather_data WHERE city_id = :cityId";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("cityId", city.getId());

        return namedParameterJdbcTemplate.query(sql, paramMap, new WeatherDataRowMapper());
    }
}
