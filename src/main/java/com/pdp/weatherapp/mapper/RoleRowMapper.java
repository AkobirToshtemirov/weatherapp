package com.pdp.weatherapp.mapper;

import com.pdp.weatherapp.entity.Role;
import org.springframework.jdbc.core.RowMapper;

public class RoleRowMapper implements RowMapper<Role> {
    @Override
    public Role mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
        return Role.builder()
                .id(rs.getLong("id"))
                .roleName(rs.getString("roleName"))
                .roleCode(rs.getString("roleCode"))
                .build();
    }
}
