package com.pdp.weatherapp.repository;

import com.pdp.weatherapp.entity.City;
import com.pdp.weatherapp.entity.WeatherData;

import java.util.List;

public interface WeatherDataRepository extends CrudRepository<WeatherData, Long> {
    List<WeatherData> findByCity(City city);
}
