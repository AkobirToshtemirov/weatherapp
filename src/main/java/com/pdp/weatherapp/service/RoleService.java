package com.pdp.weatherapp.service;

import com.pdp.weatherapp.entity.Role;

import java.util.List;

public interface RoleService extends CrudService<Role, Long>{
    List<Role> findRolesByUserId(Long userId);
    Role findByName(String roleName);
}
