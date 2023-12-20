package com.pdp.weatherapp.mapper;

import com.pdp.weatherapp.entity.City;
import org.springframework.jdbc.core.RowMapper;

public class CityRowMapper implements RowMapper<City> {
    @Override
    public City mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
        return City.builder()
                .id(rs.getLong("id"))
                .cityName(rs.getString("cityName"))
                .build();
    }
}
