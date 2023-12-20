package com.pdp.weatherapp.repository;

import com.pdp.weatherapp.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
