package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<T, ID> {
    List<T> findAll();
    Optional<T> findById(ID id);
    T save(T entity);
    T update(ID id, T entity);
    void delete(ID id);
}
