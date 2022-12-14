package com.meal_planner.jdbc.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> get(String id);

    Optional<List<T>> get();
//
//    List<T> getAll();
//
//    void save(T t);
//
//    void update(T t, String[] params);
//
//    void delete(T t);
}
