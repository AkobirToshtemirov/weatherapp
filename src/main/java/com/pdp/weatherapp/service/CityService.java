package com.pdp.weatherapp.service;

import com.pdp.weatherapp.entity.City;

public interface CityService extends CrudService<City, Long> {
    void update(City city);
}
