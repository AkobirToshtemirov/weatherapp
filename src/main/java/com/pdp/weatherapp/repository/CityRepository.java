package com.pdp.weatherapp.repository;

import com.pdp.weatherapp.entity.City;

public interface CityRepository extends CrudRepository<City, Long> {
    void update(City city);
}
