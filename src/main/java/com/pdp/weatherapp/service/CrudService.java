package com.pdp.weatherapp.service;

import java.util.List;

public interface CrudService<T, Long> {
    T findById(Long id);

    List<T> findAll();

    void save(T entity);

    void delete(T entity);
}
