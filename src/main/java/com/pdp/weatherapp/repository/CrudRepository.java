package com.pdp.weatherapp.repository;

import java.util.List;

public interface CrudRepository<T, Long> {
    T findById(Long id);

    List<T> findAll();

    void save(T entity);

    void delete(T entity);
}
