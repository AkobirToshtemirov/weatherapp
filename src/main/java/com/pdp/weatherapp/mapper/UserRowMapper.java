package com.pdp.weatherapp.mapper;

import com.pdp.weatherapp.entity.User;
import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
        return User.builder()
                .id(rs.getLong("id"))
                .username(rs.getString("username"))
                .password(rs.getString("password"))
                .build();
    }
}
