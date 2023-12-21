package com.pdp.weatherapp.repository.impl;

import com.pdp.weatherapp.entity.Role;
import com.pdp.weatherapp.mapper.RoleRowMapper;
import com.pdp.weatherapp.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public RoleRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Role findById(Long id) {
        String sql = "SELECT * FROM role WHERE id = :id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);

        return namedParameterJdbcTemplate.queryForObject(sql, paramMap, new RoleRowMapper());
    }

    @Override
    public List<Role> findAll() {
        String sql = "SELECT * FROM role";

        return namedParameterJdbcTemplate.query(sql, new RoleRowMapper());
    }

    @Override
    public void save(Role role) {
        String sql = "INSERT INTO role (roleName, roleCode) VALUES (:roleName, :roleCode)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("roleName", role.getRoleName());
        paramMap.put("roleCode", role.getRoleCode());

        namedParameterJdbcTemplate.update(sql, paramMap);
    }

    @Override
    public void delete(Role role) {
        String sql = "DELETE FROM role WHERE id = :id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", role.getId());

        namedParameterJdbcTemplate.update(sql, paramMap);
    }

    @Override
    public List<Role> findRolesByUserId(Long userId) {
        String sql = "SELECT r.* FROM role r " +
                "JOIN user_roles ur ON r.id = ur.role_id " +
                "WHERE ur.user_id = :userId";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userId", userId);

        return namedParameterJdbcTemplate.query(sql, paramMap, new RoleRowMapper());
    }

    @Override
    public Role findByName(String roleName) {
        String sql = "SELECT * FROM role WHERE roleName = :roleName";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("roleName", roleName);

        return namedParameterJdbcTemplate.queryForObject(sql, paramMap, new RoleRowMapper());
    }
}
