package com.pdp.weatherapp.repository;

import com.pdp.weatherapp.entity.Role;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Long> {
    List<Role> findRolesByUserId(Long userId);
    Role findByName(String roleName);
}
