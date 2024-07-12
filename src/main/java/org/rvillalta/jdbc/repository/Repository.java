package org.rvillalta.jdbc.repository;

import java.util.List;

public interface Repository<T> {
    List<T> listAll();
    T getById(Long id);
    void save(T t);
    void update(T t);
    void delete(Long id);
}