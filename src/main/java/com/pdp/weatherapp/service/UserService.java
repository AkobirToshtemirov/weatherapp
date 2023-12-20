package com.pdp.weatherapp.service;

import com.pdp.weatherapp.entity.User;

public interface UserService extends CrudService<User, Long> {
    User findByUsername(String username);
}