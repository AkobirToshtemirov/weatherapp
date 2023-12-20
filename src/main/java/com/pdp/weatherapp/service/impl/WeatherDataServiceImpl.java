package com.pdp.weatherapp.service.impl;

import com.pdp.weatherapp.entity.WeatherData;
import com.pdp.weatherapp.repository.WeatherDataRepository;
import com.pdp.weatherapp.service.WeatherDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherDataServiceImpl implements WeatherDataService {

    private final WeatherDataRepository weatherDataRepository;

    @Autowired
    public WeatherDataServiceImpl(WeatherDataRepository weatherDataRepository) {
        this.weatherDataRepository = weatherDataRepository;
    }

    @Override
    public WeatherData findById(Long id) {
        return weatherDataRepository.findById(id);
    }

    @Override
    public List<WeatherData> findAll() {
        return weatherDataRepository.findAll();
    }

    @Override
    public void save(WeatherData weatherData) {
        weatherDataRepository.save(weatherData);
    }

    @Override
    public void delete(WeatherData weatherData) {
        weatherDataRepository.delete(weatherData);
    }
}
