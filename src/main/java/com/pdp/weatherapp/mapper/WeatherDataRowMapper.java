package com.pdp.weatherapp.mapper;

import com.pdp.weatherapp.entity.City;
import com.pdp.weatherapp.entity.WeatherData;
import org.springframework.jdbc.core.RowMapper;

public class WeatherDataRowMapper implements RowMapper<WeatherData> {
    @Override
    public WeatherData mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
        City city = new City();
        city.setId(rs.getLong("city_id"));

        return WeatherData.builder()
                .id(rs.getLong("id"))
                .city(city)
                .temperature(rs.getDouble("temperature"))
                .humidity(rs.getDouble("humidity"))
                .date(rs.getDate("date").toLocalDate())
                .build();
    }
}
